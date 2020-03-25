package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.Passenger;

/**
 * DepTermExit
 */
public class DepTermEntrance extends PassengersHandler{

    private ArrTermExit arrTermExit;

    public DepTermEntrance(int n, ArrTermExit arrTermExit){
        super(n);
        this.arrTermExit = arrTermExit;
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

    public void prepareNextLeg(DepTransQuay depTransQuay, Passenger p){
        synchronized(this){
            System.out.println("Passenger "+ p.getPassengerID()+" : prepareNextLeg");
            this.arrivedTerm(depTransQuay.leaveDepTransQuay(p.getPassengerID()));
            p.setPassengerState(Passenger.InternalState.ENTERING_THE_DEPARTURE_TERMINAL);
        }
        arrTermExit.leaveAirpDown();
        
    }
}