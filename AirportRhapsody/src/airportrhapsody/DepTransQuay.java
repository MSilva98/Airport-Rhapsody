package airportrhapsody;

import airportrhapsody.Passenger.InternalState;

/**
 * DepTransQuay
 */
public class DepTransQuay extends PassengersHandler {

    public DepTransQuay(int n){
        super(n);
    }

    public void leaveBus(Passenger p){
        p.setPassengerState(InternalState.AT_THE_DEPARTURE_TRANSFER_TERMINAL);
        super.insertPassenger(p);
    }

    public Passenger leaveDepTransQuay(int id){
        return super.removePassenger(id);
    }
}