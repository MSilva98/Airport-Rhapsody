package airportrhapsody.sharedRegions;

import airportrhapsody.entities.Passenger;
import airportrhapsody.commonInfrastructures.*;

/**
 * Departure terminal entrance
 */
public class DepTermEntrance extends PassengersHandler{
    /**
     * Arrival Terminal Exit
     * 
     * @serialField arrTermExit
     */
    private ArrTermExit arrTermExit;
    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
    private Logger generalRepo;


    /**
     * Instantiating the departure terminal entrance.
     * @param n number of passengers per flight 
     * @param arrTermExit Arrival Terminal Exit
     * @param generalRepo general repository of information
     */
    public DepTermEntrance(int n, ArrTermExit arrTermExit, Logger generalRepo){
        super(n);
        this.arrTermExit = arrTermExit;
        this.generalRepo = generalRepo;
    }
    /**
     * Insert passenger in terminal
     * @param p passenger
     */
    public void arrivedTerm(Passenger p){
        super.insertPassenger(p);
    }

    /**
     * Remove passenger from terminal
     * @return passenger
     */
    public Passenger leftTerm(){
        return super.removePassenger();
    }
    /**
     * Check if terminal is empty
     *  @return <li> true if is empty
     *          <li> else false
     */
    public boolean emptyTerm(){
        return super.isEmpty();
    }
    /**
     * Prepare next leg
     * @param depTransQuay Departure terminal transfer quay
     * @param p passenger
     */
    public void prepareNextLeg(DepTransQuay depTransQuay, Passenger p){
        synchronized(this){
            this.arrivedTerm(depTransQuay.leaveDepTransQuay(p.getPassengerID()));
            p.setPassengerState(Passenger.InternalState.ENTERING_THE_DEPARTURE_TERMINAL);
            this.generalRepo.setSt(p.getPassengerID(), "EDT");

            // System.out.println("PREP NEXT LEG " + p.getPassengerID());
        }
        arrTermExit.leaveAirpDown();
        synchronized(this){
            super.removePassenger();
        }
        
    }
}