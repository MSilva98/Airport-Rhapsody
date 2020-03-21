package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.Porter;

/**
 * CollectionPoint
 */
public class CollectionPoint extends LuggageHandler {

    private Semaphore collectBag[];
    private int currentPass;

    public CollectionPoint(int n){
        super(n);
        collectBag = new Semaphore[n];
        for (int i = 0; i < collectBag.length; i++) {
            collectBag[i] = new Semaphore();
        }
       
    }

    public boolean goCollectABag(int id){
        collectBag[id].down();
        this.currentPass = id;
        return(super.remLuggage(id) != null);
    }    

    public void insertBag(Luggage l){
        if(l.getOwner() == this.currentPass){
            collectBag[l.getOwner()].up();
        }
        super.addLuggage(l);
    }

    public void noMoreBagsToCollect(Porter p) {
        System.out.println("Porter: " + "noMoreBags");
        p.setPorterState(Porter.InternalState.WAITING_FOR_A_PLANE_TO_LAND);
        for (int i = 0; i < collectBag.length; i++) {
            collectBag[i].up();
        }
        
    }
}