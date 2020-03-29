package airportrhapsody.entities;

import airportrhapsody.Logger;
import airportrhapsody.sharedRegions.*;

public class BusDriver extends Thread {
    public enum InternalState {
        PARKING_AT_THE_ARRIVAL_TERMINAL, DRIVING_FORWARD, PARKING_AT_THE_DEPARTURE_TERMINAL, DRIVING_BACKWARD
    }

    private int busDriverID;
    private ArrTransQuay arrTransQuay;
    private DepTransQuay depTransQuay;
    private InternalState busDriverState;
    private Logger generalRepo;
    private int seats;

    public BusDriver() {
        this.busDriverState = InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL;
    }

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

    @Override
    public void run() {
        System.out.println("Thread BusDriver");
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

    private void goToDepartureTerminal() {
        System.out.println("BusDriver: goToDepartureTerminal");
        this.setBusDriverState(InternalState.DRIVING_FORWARD);
        generalRepo.setStatDriver("DRFW");
        generalRepo.write(false);
    }

    private void goToArrivalTerminal() {
        System.out.println("BusDriver: goToArrivalTerminal");
        this.setBusDriverState(InternalState.DRIVING_BACKWARD);
        generalRepo.setStatDriver("DRBW");
        generalRepo.write(false);
    }

    private boolean hasDaysWorkEnded() {
        System.out.println("BusDriver: hasDaysWorkEnded");
        this.setBusDriverState(InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
        generalRepo.setStatDriver("PKAT");
        return arrTransQuay.getDayEnd();
    }

    public InternalState getBusDriverState() {
        return this.busDriverState;
    }

    public void setBusDriverState(InternalState state) {
        this.busDriverState = state;
    }

}
