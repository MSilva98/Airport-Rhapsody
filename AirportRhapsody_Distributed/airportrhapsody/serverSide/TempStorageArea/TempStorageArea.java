package airportrhapsody.serverSide.TempStorageArea;

import airportrhapsody.comInf.*;

/**
 * Temporary Storage Area
 */
public class TempStorageArea extends LuggageHandler {
    /**
     * Instantiating the temporary Storage Area.
     * @param n number of max bags
     */
    public TempStorageArea(int n){
        super(n);
    }
    /**
     * Insert bag in temporary storage area
     * @param l bag
     */
    public void insertBag(Luggage l){
        super.addLuggage(l);
    }
    /**
     * Collect a bag from Temporary Storage Area
     * @return bag
     */
    public Luggage collectBag(){
        return super.remLuggage();
    }


    @Override
    public int size(){
        return super.size();
}