package airportrhapsody;

/**
 * DepTermExit
 */
public class DepTermExit extends PassengersHandler{

    public DepTermExit(int n){
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