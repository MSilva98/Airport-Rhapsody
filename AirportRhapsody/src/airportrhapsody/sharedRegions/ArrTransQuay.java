package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.BusDriver;
import airportrhapsody.mainProgram.Passenger;


/**
 * ArrTransQuay
 */
public class ArrTransQuay extends PassengersHandler {

    private Semaphore parkBusArr;
    private Semaphore busBoard;
    private Semaphore[] takeBus;
    private Semaphore enterBus;
    private Passenger[] seats;
    private int idx;
    private PassengersHandler queue;

    public ArrTransQuay(int n, int nseats){
        super(n);
        this.seats = new Passenger[nseats];
        this.idx = 0;
        this.queue = new PassengersHandler(n);
        this.parkBusArr = new Semaphore();
        this.busBoard = new Semaphore();
        this.takeBus = new Semaphore[n];
        for (int i = 0; i < takeBus.length; i++) {
            this.takeBus[i] = new Semaphore();
        }
        this.enterBus = new Semaphore();
    }
    
    public void arrived(Passenger p){
        super.insertPassenger(p);
    }

    public void enterBusUp() {
        enterBus.up();
    }

    public void enterTheBus(int id){
        this.seats[idx] = super.removePassenger(id);
        this.idx++;
        if(this.idx == this.seats.length-1){
            this.idx = 0;
            this.busBoard.up();
        }
        this.enterBus.down();
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
        this.queue.insertPassenger(p);
        this.parkBusArr.up();
        this.takeBus[p.getPassengerID()].down();
    }

    public Passenger[] announcingBusBoarding(BusDriver b) {
        System.out.println("BusDriver: announcingBusBoarding: number of passengers in queue: "+ this.numPassengers());
        b.setBusDriverState(BusDriver.InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
        //this.seats = b.getSeats();
        // this.takeBus.up();

        if(this.queue.size() >= 3){
            for(int i = 0; i < 3; i++) {
                int id = this.queue.removePassenger().getPassengerID();
                this.takeBus[id].up();
            }
        }
        this.busBoard.down();
        
        return seats;
    }

    public int numPassengers(){
        return super.size();
    }
}