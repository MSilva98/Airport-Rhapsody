package airportrhapsody.sharedRegions;

import airportrhapsody.commonInfrastructures.*;

/**
 * TempStorageArea
 */
public class TempStorageArea extends LuggageHandler {

    public TempStorageArea(int n){
        super(n);
    }

    public void insertBag(Luggage l){
        super.addLuggage(l);
    }

    public Luggage collectBag(){
        return super.remLuggage();
    }
}