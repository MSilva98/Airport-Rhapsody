package airportrhapsody.sharedRegions;

import airportrhapsody.Logger;
import airportrhapsody.mainProgram.Passenger;
import airportrhapsody.mainProgram.Porter;

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
        p.setPassengerState(Passenger.InternalState.AT_THE_LUGGAGE_COLLECTION_POINT);
        this.generalRepo.setSt(p.getPassengerID(), "ATCP");
        this.collectBag[p.getPassengerID()].down();
        synchronized(this){
            // System.out.println(p.getPassengerID());
            // Luggage l = super.remLuggage(p.getPassengerID());
            // System.out.println(l);
        return(super.remLuggage(p.getPassengerID()) != null);
        }
    }    

    public void insertBag(Luggage l){           // a mala L é de certeza de um dos que está a espera pq a distinção é feita no carryItToAppropriateStore
        collectBag[l.getOwner()].up();
        super.addLuggage(l);
    }

    public void noMoreBagsToCollect(Porter p) {
        p.setPorterState(Porter.InternalState.WAITING_FOR_A_PLANE_TO_LAND);
        this.generalRepo.setStatPorter("WFPL");
        this.generalRepo.write(false);
        for (int i = 0; i < collectBag.length; i++) {
            collectBag[i].up();
        }
    }

    public int size(){
        return super.size();
    }
}