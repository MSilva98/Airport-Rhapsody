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

    public Luggage getBag(){
        return super.remLuggage();
    }

    // @Override
    // public String toString() {
    //     return "luggage = " + getLug();
    // }

}