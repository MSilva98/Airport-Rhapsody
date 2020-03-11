package airportrhapsody;


/**
 * DepTransQuay
 */
public class DepTransQuay extends PassengersHandler {

    public DepTransQuay(int n){
        super(n);
    }

    public void leaveBus(Passenger p){
        super.insertPassenger(p);
    }

    public Passenger prepareNextLeg(){
        return super.removePassenger();
    }
}