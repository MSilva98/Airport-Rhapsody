package airportrhapsody.sharedRegions;

import javax.crypto.SealedObject;

import airportrhapsody.mainProgram.BusDriver;
import airportrhapsody.mainProgram.Passenger;


/**
 * ArrTransQuay
 */
public class ArrTransQuay extends PassengersHandler {

    private Semaphore parkBusArr;
    private Semaphore busBoard;
    private Semaphore takeBus;
    private Semaphore enterBus;
    private Passenger[] seats;
    private int idx;

    public ArrTransQuay(int n, int nseats){
        super(n);
        seats = new Passenger[nseats];
        idx = 0;
        this.parkBusArr = new Semaphore();
        this.busBoard = new Semaphore();
        this.takeBus = new Semaphore();
        this.enterBus = new Semaphore();
    }
    
    public void arrived(Passenger p){
        super.insertPassenger(p);
    }

    public void enterBusUp() {
        enterBus.up();
    }

    public void enterTheBus(int id){
        seats[idx] = super.removePassenger(id);
        idx++;
        this.enterBus.down();
        if(idx == seats.length-1){
            idx = 0;
            this.busBoard.up();
        } 
    }

    public void parkTheBus(BusDriver b){
        System.out.println("BusDriver: parkTheBus");
        b.setBusDriverState(BusDriver.InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
        this.parkBusArr.down();
    }

    public void takeABus(Passenger p) {
        this.arrived(p);
        p.setPassengerState(Passenger.InternalState.AT_THE_ARRIVAL_TRANSFER_TERMINAL);
        System.out.println("Passenger "+ p.getPassengerID() +" : takeABus()");
        this.parkBusArr.up();
        this.takeBus.down();
    }

    public Passenger[] announcingBusBoarding(BusDriver b) {
        System.out.println("BusDriver: announcingBusBoarding: number of passengers in queue: "+ this.numPassengers());
        b.setBusDriverState(BusDriver.InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
        //this.seats = b.getSeats();
        
        this.takeBus.up();
        this.busBoard.down();
        // for(int i = 0; i < seats.length; i++) {
        //     seats[i] = this.enterTheBus(seats.length-1 == i);
        // }
        //goToDepartureTerminal();
        return seats;
    }

    public int numPassengers(){
        return super.size();
    }
}