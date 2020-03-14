package airportrhapsody;

/**
 * CollectionPoint
 */
public class CollectionPoint extends LuggageHandler {

    public CollectionPoint(int n){
        super(n);
    }

    public boolean goCollectABag(int id){
        return(super.remLuggage(id) != null);
    }    

    public void insertBag(Luggage l){
        super.addLuggage(l);
    }
}