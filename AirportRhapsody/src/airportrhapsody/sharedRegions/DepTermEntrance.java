package airportrhapsody.sharedRegions;

import airportrhapsody.Logger;
import airportrhapsody.mainProgram.Passenger;

/**
 * DepTermExit
 */
public class DepTermEntrance extends PassengersHandler{

    private ArrTermExit arrTermExit;
    private Logger generalRepo;

    public DepTermEntrance(int n, ArrTermExit arrTermExit, Logger generalRepo){
        super(n);
        this.arrTermExit = arrTermExit;
        this.generalRepo = generalRepo;
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
            this.generalRepo.setSt(p.getPassengerID(), "EDT ");
        }
        arrTermExit.leaveAirpDown();
        synchronized(this){
            super.removePassenger();
        }
        
    }
}