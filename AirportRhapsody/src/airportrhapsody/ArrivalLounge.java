package airportrhapsody;

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

    public boolean goCollectABag(int id){
         return(super.remLuggage(id) != null);
    }

    // @Override
    // public String toString() {
    //     return "luggage = " + getLug();
    // }

}