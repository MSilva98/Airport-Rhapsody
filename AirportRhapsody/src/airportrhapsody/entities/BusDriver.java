package airportrhapsody.entities;

import airportrhapsody.sharedRegions.*;

/**
 * This class implements the bus driver thread
 */

public class BusDriver extends Thread {
    public enum InternalState {
        PARKING_AT_THE_ARRIVAL_TERMINAL, DRIVING_FORWARD, PARKING_AT_THE_DEPARTURE_TERMINAL, DRIVING_BACKWARD
    }
    /**
     *  Bus Driver identification
     * 
     *  @serialField busDriveID
     */
    private int busDriverID;

    /**
     *  Arrival terminal transfer quay
     * 
     *  @serialField arrTransQuay
     */

    private ArrTransQuay arrTransQuay;

    /**
     *  Departure terminal transfer quay
     * 
     *  @serialField depTransQuay
     */
    private DepTransQuay depTransQuay;

    /**
     *  Bus Driver state
     * 
     *  @serialField busDriverState
     */
    private InternalState busDriverState;

    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
    private Logger generalRepo;

    /**
     *  Number of seats
     * 
     *  @serialField seats
     */
    private int seats;


    /**
     * Instantiating the bus driver thread.
     * @param id Bus Driver identification
     * @param numOfSeats Number of seats
     * @param arrTransQuay Arrival terminal transfer quay
     * @param depTransQuay Departure terminal transfer quay
     * @param generalRepo General repository of information
     */

    public BusDriver(int id, int numOfSeats, ArrTransQuay arrTransQuay, DepTransQuay depTransQuay, Logger generalRepo) {
        this.busDriverState = InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL;
        this.seats = numOfSeats;
        this.arrTransQuay = arrTransQuay;
        this.depTransQuay = depTransQuay;
        this.generalRepo = generalRepo;
        this.generalRepo.setStatDriver("PKAT");
        this.generalRepo.setQEmpty();
        this.generalRepo.setSEmpty();
    }

    /**
     * Bus Driver thread life cycle
     */

    @Override
    public void run() {
        while(!hasDaysWorkEnded()){
            arrTransQuay.parkTheBus(this);
            if(!arrTransQuay.isEmpty()){
                arrTransQuay.announcingBusBoarding();
                goToDepartureTerminal();
                depTransQuay.parkTheBusAndLetPassOff(this);
                goToArrivalTerminal();
            }
        }
        generalRepo.write(false);
    }

    /**
     * Go to departure terminal.
     */

    private void goToDepartureTerminal() {
        this.setBusDriverState(InternalState.DRIVING_FORWARD);
        generalRepo.setStatDriver("DRFW");
        generalRepo.write(false);
    }

    /**
     * Go to arrival terminal.
     */

    private void goToArrivalTerminal() {
        this.setBusDriverState(InternalState.DRIVING_BACKWARD);
        generalRepo.setStatDriver("DRBW");
        generalRepo.write(false);
    }

    /**
     * The working days are over
     * @return <li> true, if the work is over
     *         <li> false, if the work isn't over
     */

    private boolean hasDaysWorkEnded() {
        this.setBusDriverState(InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
        generalRepo.setStatDriver("PKAT");
        return arrTransQuay.getDayEnd();
    }

    /**
     * Get the bus driver state
     * @return bus driver state
     */

    public InternalState getBusDriverState() {
        return this.busDriverState;
    }

    /**
     * Set the bus driver state
     * @param state state
     */

    public void setBusDriverState(InternalState state) {
        this.busDriverState = state;
    }

}
