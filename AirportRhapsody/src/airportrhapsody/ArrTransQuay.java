package airportrhapsody;

/**
 * ArrTransQuay
 */
public class ArrTransQuay extends PassengersHandler {

    public ArrTransQuay(int n){
        super(n);
    }
    
    public void arrived(Passenger p){
        super.insertPassenger(p);
    }

    public Passenger enterBus(){
        return super.removePassenger();
    }
}