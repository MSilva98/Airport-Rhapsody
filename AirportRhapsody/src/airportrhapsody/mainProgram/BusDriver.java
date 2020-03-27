package airportrhapsody.mainProgram;

import airportrhapsody.sharedRegions.*;

public class BusDriver extends Thread {
    public enum InternalState {
        PARKING_AT_THE_ARRIVAL_TERMINAL, DRIVING_FORWARD, PARKING_AT_THE_DEPARTURE_TERMINAL, DRIVING_BACKWARD
    }

    private int busDriverID;
    private ArrTransQuay arrTransQuay;
    private DepTransQuay depTransQuay;
    private InternalState busDriverState;
    // private PassengersHandler seats; // occupation state for seat in the bus (passenger id / - (empty))
    private int seats;
    private boolean schd = false;   // true when bus leaves in the scheduled time

    public BusDriver() {
        this.busDriverState = InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL;
    }

    public BusDriver(int id, int numOfSeats, ArrTransQuay arrTransQuay, DepTransQuay depTransQuay) {
        this.busDriverState = InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL;
        this.seats = numOfSeats;
        this.arrTransQuay = arrTransQuay;
        this.depTransQuay = depTransQuay;
    }

    @Override
    public void run() {
        System.out.println("Thread BusDriver");
        while(true){
            arrTransQuay.parkTheBus(this);
            if(!arrTransQuay.isEmpty()){
                arrTransQuay.announcingBusBoarding(this);
                goToDepartureTerminal();
                depTransQuay.parkTheBusAndLetPassOff(this);
                goToArrivalTerminal();
            }
        }
    }

    private void goToDepartureTerminal() {
        System.out.println("BusDriver: goToDepartureTerminal");
        this.setBusDriverState(InternalState.DRIVING_FORWARD);
    }

    private void goToArrivalTerminal() {
        System.out.println("BusDriver: goToArrivalTerminal");
        this.setBusDriverState(InternalState.DRIVING_BACKWARD);
    }

    private void hasDaysWorkEnded() {
        System.out.println("BusDriver: hasDaysWorkEnded");
        this.setBusDriverState(InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
    }

    public InternalState getBusDriverState() {
        return this.busDriverState;
    }

    public void setBusDriverState(InternalState state) {
        this.busDriverState = state;
    }

    public boolean leaveTime(){
        return this.schd;
    }

}
