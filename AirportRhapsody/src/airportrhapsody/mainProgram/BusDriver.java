package airportrhapsody.mainProgram;

import airportrhapsody.sharedRegions.*;

public class BusDriver extends Thread {
    enum InternalState {
        PARKING_AT_THE_ARRIVAL_TERMINAL, DRIVING_FORWARD, PARKING_AT_THE_DEPARTURE_TERMINAL, DRIVING_BACKWARD
    }

    private int busDriverID;
    private ArrTransQuay arrTransQuay;
    private DepTransQuay depTransQuay;
    private InternalState busDriverState;
    private Passenger[] seats; // occupation state for seat in the bus (passenger id / - (empty))

    public BusDriver() {
        this.busDriverState = InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL;
    }

    public BusDriver(int id, int numOfSeats, ArrTransQuay arrTransQuay, DepTransQuay depTransQuay) {
        this.busDriverState = InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL;
        this.seats = new Passenger[numOfSeats];
        this.arrTransQuay = arrTransQuay;
        this.depTransQuay = depTransQuay;
    }

    @Override
    public void run() {
        System.out.println("Thread BusDriver");
        int schedule = 50;
        // while(schedule > 0){
        //     if(arrTransQuay.numPassengers() >= seats.length){
        //         schedule = 0;
        //     } 
        //     schedule--; 
        // }

        // if(schedule == 0 && arrTransQuay.numPassengers() > 0){
        //     announcingBusBoarding();
        // }
        // else{
        //     schedule = 50;
        // }
        while(arrTransQuay.numPassengers() > 0){
            schedule--;
            if(arrTransQuay.numPassengers() >= seats.length || schedule == 0){
                announcingBusBoarding();
                goToDepartureTerminal();
                parkTheBusAndLetPassOff();
                goToArrivalTerminal();
                parkTheBus();
            }
            if(schedule == 0){
                schedule = 50;
            }
        }
        hasDaysWorkEnded();

    }

    private void goToDepartureTerminal() {
        this.setBusDriverState(InternalState.DRIVING_FORWARD);
        //parkTheBusAndLetPassOff();
    }

    private void parkTheBusAndLetPassOff(){
        this.setBusDriverState(InternalState.PARKING_AT_THE_DEPARTURE_TERMINAL);
        for(int i = 0; i < seats.length; i++) {
           depTransQuay.leaveBus(seats[i]); 
        }
        //goToArrivalTerminal();
    }

    private void goToArrivalTerminal() {
        this.setBusDriverState(InternalState.DRIVING_BACKWARD);
        //parkTheBus();
    }

    private void parkTheBus(){
        System.out.println("BusDriver: parkTheBus");
        this.setBusDriverState(InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
        // Lock the thread
    }

    private void hasDaysWorkEnded() {
        System.out.println("BusDriver: hasDaysWorkEnded");
        this.setBusDriverState(InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
    }

    private void announcingBusBoarding() {
        System.out.println("BusDriver: announcingBusBoarding: number of passengers in queue: "+ arrTransQuay.numPassengers());
        this.setBusDriverState(InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
        for(int i = 0; i < seats.length; i++) {
            seats[i] = arrTransQuay.enterTheBus();
        }
        //goToDepartureTerminal();
    }

    public InternalState getBusDriverState() {
        return this.busDriverState;
    }

    public void setBusDriverState(InternalState state) {
        this.busDriverState = state;
    }

    public Passenger[] getSeats() {
        return this.seats;
    }

    public void setSeats(Passenger[] seats) {
        this.seats = seats;
    }

}
