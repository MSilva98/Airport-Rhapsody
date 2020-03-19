package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.Passenger;

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

    public Passenger enterTheBus(){
        return super.removePassenger();
    }

    public int numPassengers(){
        return super.size();
    }
}