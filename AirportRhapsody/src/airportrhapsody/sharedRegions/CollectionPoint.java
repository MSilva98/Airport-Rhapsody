package airportrhapsody.sharedRegions;

import airportrhapsody.Logger;
import airportrhapsody.entities.Passenger;
import airportrhapsody.entities.Porter;
import airportrhapsody.commonInfrastructures.*;

/**
 * CollectionPoint
 */
public class CollectionPoint extends LuggageHandler {

    private Semaphore collectBag[];
    private Logger generalRepo;

    public CollectionPoint(int nBags, int nPass, Logger generalRepo){
        super(nBags);
        this.collectBag = new Semaphore[nPass];
        for (int i = 0; i < collectBag.length; i++) {
            this.collectBag[i] = new Semaphore();
        }
        this.generalRepo = generalRepo;
    }

    public boolean goCollectABag(Passenger p){
        synchronized(this){
            p.setPassengerState(Passenger.InternalState.AT_THE_LUGGAGE_COLLECTION_POINT);
            this.generalRepo.setSt(p.getPassengerID(), "LCP");
            // this.wakenPassengers[p.getPassengerID()] = false;
            System.out.println("Passenger " + p.getPassengerID() + " BLOQUEADO");
        }
        this.collectBag[p.getPassengerID()].down();
        synchronized(this){
            // System.out.println(p.getPassengerID());
            // Luggage l = super.remLuggage(p.getPassengerID());
            // System.out.println(l);
            return(super.remLuggage(p.getPassengerID()) != null);
        }
    }    

    public void insertBag(Luggage l){
        super.addLuggage(l);
        // if(!this.wakenPassengers[l.getOwner()]){
        //     this.wakenPassengers[l.getOwner()] = true;
            System.out.println("PASSENGER " + l.getOwner() + " TEM MALA À ESPERA");
            this.collectBag[l.getOwner()].up();
        // }
    }

    public void noMoreBagsToCollect(Porter p) {
        p.setPorterState(Porter.InternalState.WAITING_FOR_A_PLANE_TO_LAND);
        this.generalRepo.setStatPorter("WPTL");
        this.generalRepo.write(false);
        System.out.println("No more bags");
        // for (int i = 0; i < collectBag.length; i++) {
        //     if(!this.wakenPassengers[i]){    
        //         System.out.println("PASSENGER " + i + " DESBLOQUEADO");
        //         this.collectBag[i].up();
        //     }
        // }
        // this.wakenPassengers = new boolean[this.wakenPassengers.length];
        
        while(!super.isEmpty());    // fica aqui enquanto houver malas no belt

        for (int i = 0; i < collectBag.length; i++) {
            System.out.println("PASSENGER " + i + " DESBLOQUEADO");
            this.collectBag[i].up();
        }
        // System.out.println("TODOS DESBLOQUEADOS");
        resetSemaphores(this.collectBag.length);
    }

    public int size(){
        return super.size();
    }

    private void resetSemaphores(int n){
        this.collectBag = new Semaphore[n];
        for (int i = 0; i < collectBag.length; i++) {
            collectBag[i] = new Semaphore();
        }
    }
}