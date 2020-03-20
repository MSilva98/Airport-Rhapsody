package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.Passenger;

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

    public void goHome(Passenger p) {
        this.insertPassenger(p); 
        p.setPassengerState(Passenger.InternalState.EXITING_THE_ARRIVAL_TERMINAL);
        System.out.println("Passenger "+ p.getPassengerID() +" : goHome");
    }
}