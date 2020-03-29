package airportrhapsody.sharedRegions;

import airportrhapsody.Logger;
import airportrhapsody.mainProgram.Passenger;
import airportrhapsody.mainProgram.Porter;
import jdk.nashorn.internal.runtime.linker.BoundCallable;

/**
 * CollectionPoint
 */
public class CollectionPoint extends LuggageHandler {

    private Semaphore collectBag[];
    private Logger generalRepo;
    private boolean[] wakenPassengers;      // true if passenger is awake, false if he isn't
    private boolean noBags;

    public CollectionPoint(int nBags, int nPass, Logger generalRepo){
        super(nBags);
        this.collectBag = new Semaphore[nPass];
        for (int i = 0; i < collectBag.length; i++) {
            this.collectBag[i] = new Semaphore();
        }
        this.wakenPassengers = new boolean[nPass];
        this.noBags = false;
        this.generalRepo = generalRepo;
    }

    public boolean goCollectABag(Passenger p){
        if(super.isEmpty()){
            synchronized(this){
                p.setPassengerState(Passenger.InternalState.AT_THE_LUGGAGE_COLLECTION_POINT);
                this.generalRepo.setSt(p.getPassengerID(), "ATCP");
                this.wakenPassengers[p.getPassengerID()] = false;
                System.out.println("Passenger " + p.getPassengerID() + " BLOQUEADO");
            }
            this.collectBag[p.getPassengerID()].down();
            synchronized(this){
                // System.out.println(p.getPassengerID());
                // Luggage l = super.remLuggage(p.getPassengerID());
                // System.out.println(l);
                return(super.remLuggage(p.getPassengerID()) != null);
            }
        }else{
            return false;
        }
        
    }    

    public void insertBag(Luggage l){
        super.addLuggage(l);
        this.noBags = false;
        if(!this.wakenPassengers[l.getOwner()]){
            this.wakenPassengers[l.getOwner()] = true;
            System.out.println("PASSENGER " + l.getOwner() + " TEM MALA À ESPERA");
            this.collectBag[l.getOwner()].up();
        }
    }

    public void noMoreBagsToCollect(Porter p) {
        p.setPorterState(Porter.InternalState.WAITING_FOR_A_PLANE_TO_LAND);
        this.generalRepo.setStatPorter("WFPL");
        this.generalRepo.write(false);
        System.out.println("No more bags");
        this.noBags = true;
        for (int i = 0; i < collectBag.length; i++) {
            if(!this.wakenPassengers[i]){    
                System.out.println("PASSENGER " + i + " DESBLOQUEADO");
                this.collectBag[i].up();
            }
        }
        this.wakenPassengers = new boolean[this.wakenPassengers.length];
    }

    public int size(){
        return super.size();
    }
}