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
        System.out.println("RUN 0");
        while(!hasDaysWorkEnded()){
            System.out.println("RUN 1");
            arrTransQuay.parkTheBus();
            System.out.println("RUN 2");
            this.setBusDriverState(InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
            System.out.println("RUN 3");
            this.generalRepo.setStatDriver("PKAT");
            System.out.println("RUN 4");  
            this.generalRepo.write(false);
            System.out.println("RUN 5");
            if(!arrTransQuay.isEmpty()){
                System.out.println("RUN 6");
                arrTransQuay.announcingBusBoarding();
                System.out.println("RUN 7");
                goToDepartureTerminal();
                System.out.println("RUN 8");
                this.setBusDriverState(depTransQuay.parkTheBusAndLetPassOff());
                System.out.println("RUN 9");
                goToArrivalTerminal();
            }
            System.out.println("RUN 10");
        }
        generalRepo.write(false);
        //this.shutdownServers();
        
    }
    /**
     * Shutdown servers
     */
    private void shutdownServers(){
        arrTransQuay.shutdown();
        depTransQuay.shutdown();
        generalRepo.write(true); 
        generalRepo.shutdown();
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
        System.out.println("HSWE 0");
        this.setBusDriverState(InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
        System.out.println("HSWE 1");
        generalRepo.setStatDriver("PKAT");
        System.out.println("HSWE 2");
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
