package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.Porter;

/**
 * CollectionPoint
 */
public class CollectionPoint extends LuggageHandler {

    private Semaphore collectBag;
    private int currentPass;

    public CollectionPoint(int n){
        super(n);
        this.collectBag = new Semaphore();
    }

    public boolean goCollectABag(int id){
        collectBag.down();
        this.currentPass = id;
        return(super.remLuggage(id) != null);
    }    

    public void insertBag(Luggage l){
        if(l.getOwner() == this.currentPass){
            collectBag.up();
        }
        super.addLuggage(l);
    }

    public void noMoreBagsToCollect(Porter p) {
        System.out.println("Porter: " + "noMoreBags");
        p.setPorterState(Porter.InternalState.WAITING_FOR_A_PLANE_TO_LAND);
        collectBag.up();
    }
}