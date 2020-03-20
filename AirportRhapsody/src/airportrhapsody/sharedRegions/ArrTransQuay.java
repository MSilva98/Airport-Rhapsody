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

    public ArrTransQuay(int n){
        super(n);
        this.parkBusArr = new Semaphore();
        this.busBoard = new Semaphore();
        this.takeBus = new Semaphore();
        this.enterBus = new Semaphore();
    }
    
    public void arrived(Passenger p){
        super.insertPassenger(p);
    }

    public void enterTheBus(int id){
        seats[id] = super.removePassenger(id);
        if(id == seats.length){
            this.busBoard.up();
        }
        else{
            this.enterBus.down();
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
        this.seats = b.getSeats();
        
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