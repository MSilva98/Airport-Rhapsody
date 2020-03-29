package airportrhapsody.sharedRegions;

import airportrhapsody.entities.Passenger;
import airportrhapsody.entities.Porter;
import airportrhapsody.commonInfrastructures.*;

/**
 * Baggage Collection Point
 */
public class CollectionPoint extends LuggageHandler {
    /**
     * Array of semaphore that block passenger thread
     * 
     * @serialField collectBag
     */
    private Semaphore collectBag[];
    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
    private Logger generalRepo;

    /**
     * Instantiating the baggage Collection Point.
     * @param nBags max number of bags
     * @param nPass max number of passengers per flight
     * @param generalRepo general repository of information
     */
    public CollectionPoint(int nBags, int nPass, Logger generalRepo){
        super(nBags);
        this.collectBag = new Semaphore[nPass];
        for (int i = 0; i < collectBag.length; i++) {
            this.collectBag[i] = new Semaphore();
        }
        this.generalRepo = generalRepo;
    }

    /**
     * Go collect a bag
     * @param p passenger
     * @return <li> true if collect a bag
     *         <li> false if don't collect a bag
     */
    public boolean goCollectABag(Passenger p){
        synchronized(this){
            p.setPassengerState(Passenger.InternalState.AT_THE_LUGGAGE_COLLECTION_POINT);
            this.generalRepo.setSt(p.getPassengerID(), "LCP");
        }
        this.collectBag[p.getPassengerID()].down();
        synchronized(this){
            // System.out.println(p.getPassengerID());
            // Luggage l = super.remLuggage(p.getPassengerID());
            // System.out.println(l);
            return(super.remLuggage(p.getPassengerID()) != null);
        }
    }    
    /**
     * Insert a bag in collection point
     * @param l bag
     */
    public void insertBag(Luggage l){
        super.addLuggage(l);
        // if(!this.wakenPassengers[l.getOwner()]){
        //     this.wakenPassengers[l.getOwner()] = true;
            // System.out.println("PASSENGER " + l.getOwner() + " TEM MALA À ESPERA");
            this.collectBag[l.getOwner()].up();
        // }
    }

    /**
     * No more bags to collect
     * @param p porter
     */
    public void noMoreBagsToCollect(Porter p) {
        p.setPorterState(Porter.InternalState.WAITING_FOR_A_PLANE_TO_LAND);
        this.generalRepo.setStatPorter("WPTL");
        this.generalRepo.write(false);
        while(!super.isEmpty());  

        for (int i = 0; i < collectBag.length; i++) {
            this.collectBag[i].up();
        }
        resetSemaphores(this.collectBag.length);
    }

    public int size(){
        return super.size();
    }
    /**
     * Reset passenger semaphores
     * @param n number of passengers
     */
    private void resetSemaphores(int n){
        this.collectBag = new Semaphore[n];
        for (int i = 0; i < collectBag.length; i++) {
            collectBag[i] = new Semaphore();
        }
    }
}