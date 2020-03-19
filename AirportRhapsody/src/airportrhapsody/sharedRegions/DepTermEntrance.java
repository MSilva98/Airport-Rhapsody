package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.Passenger;

/**
 * DepTermExit
 */
public class DepTermEntrance extends PassengersHandler{

    public DepTermEntrance(int n){
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