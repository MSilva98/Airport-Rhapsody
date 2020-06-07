package airportrhapsody.clientSide;

import airportrhapsody.serverSide.Logger.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

    private ArrivalLoungeStub arrivalLounge;
    /**
     * Instantiating the bus driver thread.
     * @param id Bus Driver identification
     * @param numOfSeats Number of seats
     * @param arrTransQuay Arrival terminal transfer quay
     * @param depTransQuay Departure terminal transfer quay
     * @param generalRepo General repository of information
     */

    public BusDriver(int id, int numOfSeats, ArrTransQuayStub arrTransQuay, DepTransQuayStub depTransQuay, ArrivalLoungeStub arrivalLounge,  LoggerStub generalRepo) {
        this.busDriverState = InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL;
        this.seats = numOfSeats;
        this.arrTransQuay = arrTransQuay;
        this.depTransQuay = depTransQuay;
        this.arrivalLounge = arrivalLounge;
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
        this.shutdownServers();
        try{
            String s = " Bus Driver End\n";
            Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Shutdown servers
     */
    private void shutdownServers(){
        generalRepo.write(true); 
        try{
            String s = "\nWAIT FOR PORTER";
            Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        while(!this.arrivalLounge.getPrtEnd());
        arrivalLounge.shutdown();
        arrTransQuay.shutdown();
        depTransQuay.shutdown();
        generalRepo.shutdown();
        try{
            String s = "\nArrival Lounge end, ArrTransQuay End, DepTransQuay End, Logger End";
            Files.write(Paths.get("shutdown.txt"), s.getBytes(), StandardOpenOption.APPEND);
        }
        catch(IOException e){
            e.printStackTrace();
        }
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
        // System.out.println("HSWE 0");
        this.setBusDriverState(InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
        // System.out.println("HSWE 1");
        generalRepo.setStatDriver("PKAT");
        // System.out.println("HSWE 2");
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
