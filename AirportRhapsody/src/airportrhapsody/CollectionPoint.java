package airportrhapsody;

/**
 * CollectionPoint
 */
public class CollectionPoint extends LuggageHandler {

    public CollectionPoint(int n){
        super(n);
    }

    public Luggage collectBag(){
        return super.remLuggage();
    }

    public void insertBag(Luggage l){
        super.addLuggage(l);
    }
}