package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.Passenger;
import airportrhapsody.mainProgram.Porter;

/**
 * CollectionPoint
 */
public class CollectionPoint extends LuggageHandler {

    private Semaphore collectBag[];

    public CollectionPoint(int nBags, int nPass){
        super(nBags);
        collectBag = new Semaphore[nPass];
        for (int i = 0; i < collectBag.length; i++) {
            collectBag[i] = new Semaphore();
        }
    }

    public boolean goCollectABag(Passenger p){
        p.setPassengerState(Passenger.InternalState.AT_THE_LUGGAGE_COLLECTION_POINT);
        collectBag[p.getPassengerID()].down();
        return(super.remLuggage(p.getPassengerID()) != null);
    }    

    public void insertBag(Luggage l){           // a mala L é de certeza de um dos que está a espera pq a distinção é feita no carryItToAppropriateStore
        collectBag[l.getOwner()].up();
        super.addLuggage(l);
    }

    public void noMoreBagsToCollect(Porter p) {         // para já fica mas acho que deviamos fazer up só aos que estão à espera (não deve aleijar ninguém assim XD)
        p.setPorterState(Porter.InternalState.WAITING_FOR_A_PLANE_TO_LAND);
        for (int i = 0; i < collectBag.length; i++) {
            collectBag[i].up();
        }
        
    }
}