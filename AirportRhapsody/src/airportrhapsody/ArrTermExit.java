package airportrhapsody;

/**
 * ArrTermExit
 */
public class ArrTermExit extends PassengersHandler {

    public ArrTermExit(int n){
        super(n);
    }

    public void arrivedTerm(Passenger p){
        super.insertPassenger(p);
    }

    public Passenger leftTerm(){
        return super.removePassenger();
    }

    public boolean emptyTerm(){
        return super.isEmpty();
    }
}