package airportrhapsody.clientSide;

import airportrhapsody.serverSide.Logger.*;
import airportrhapsody.LoggerStub;
import airportrhapsody.serverSide.ArrTransQuay.*;
import airportrhapsody.serverSide.DepTransQuay.*;

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

    private ArrTransQuayStub arrTransQuay;

    /**
     *  Departure terminal transfer quay
     * 
     *  @serialField depTransQuay
     */
    private DepTransQuayStub depTransQuay;

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
    private LoggerStub generalRepo;

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

    public BusDriver(int id, int numOfSeats, ArrTransQuayStub arrTransQuay, DepTransQuayStub depTransQuay, LoggerStub generalRepo) {
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
            arrTransQuay.parkTheBus();
            this.setBusDriverState(InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
            this.generalRepo.setStatDriver("PKAT");  
            this.generalRepo.write(false);
            if(!arrTransQuay.isEmpty()){
                arrTransQuay.announcingBusBoarding();
                goToDepartureTerminal();
                this.setBusDriverState(depTransQuay.parkTheBusAndLetPassOff());
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
