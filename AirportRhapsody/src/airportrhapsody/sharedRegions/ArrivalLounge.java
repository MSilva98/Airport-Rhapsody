package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.Porter;
import airportrhapsody.mainProgram.Porter.InternalState;;

/**
 * ArrivalLounge
 */
public class ArrivalLounge extends LuggageHandler{

    public ArrivalLounge(int n){
        super(n);
    }

    public void putBag(Luggage l){
        super.addLuggage(l);
    }

    public Luggage tryToCollectABag(Porter p){
        p.setPorterState(InternalState.AT_THE_PLANES_HOLD);
        return super.remLuggage();
    }

    // @Override
    // public String toString() {
    //     return "luggage = " + getLug();
    // }

}