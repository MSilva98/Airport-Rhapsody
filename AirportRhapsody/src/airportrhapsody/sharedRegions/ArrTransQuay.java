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
    private Semaphore[] enterBus;
    private int enterBusIdx;
    private PassengersHandler seats;
    private int idx;
    private PassengersHandler queue;

    public ArrTransQuay(int n, int nseats){
        super(n);
        this.seats = new PassengersHandler(nseats);
        this.idx = 0;
        this.queue = new PassengersHandler(n);
        this.parkBusArr = new Semaphore();
        this.busBoard = new Semaphore();
        this.takeBus = new Semaphore[n];
        for (int i = 0; i < takeBus.length; i++) {
            this.takeBus[i] = new Semaphore();
        }
        this.enterBus = new Semaphore[nseats];
        for (int i = 0; i < enterBus.length; i++) {
            this.enterBus[i] = new Semaphore();
        }
        enterBusIdx = 0;
    }
    
    public void arrived(Passenger p){
        super.insertPassenger(p);
    }

    public void enterBusUp() {
        if(enterBusIdx >= 0){
            enterBus[enterBusIdx].up();
        }
        if(enterBusIdx > 0){
            enterBusIdx--;
        }
        
    }

    public  void enterTheBus(int id){
        synchronized (this){
            System.out.println("Passenger "+ id + ": enterTheBus()");
            this.seats.insertPassenger( super.removePassenger(id));
            this.idx++;
            if(this.idx == this.seats.size()){
                this.idx = 0;
                this.busBoard.up();
                System.out.println("BusBoard UP");
            }
        }
        
        this.enterBus[enterBusIdx].down();
        enterBusIdx++;
    }

    public void parkTheBus(BusDriver b){
        System.out.println("BusDriver: parkTheBus");
        b.setBusDriverState(BusDriver.InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
        this.parkBusArr.down();
        //dar reset
        for (int i = 0; i < takeBus.length; i++) {
            this.takeBus[i] = new Semaphore();
        }
        for (int i = 0; i < enterBus.length; i++) {
            this.enterBus[i] = new Semaphore();
        }
        //rst
        enterBusIdx = 0;
        this.idx = 0;
        this.parkBusArr = new Semaphore(); //retirar isto e ver qual é o primeiro passenger para dar up
        this.seats = new PassengersHandler(seats.maxSize());
    }

    public void takeABus(Passenger p) {
        synchronized(this){
            this.arrived(p);
            p.setPassengerState(Passenger.InternalState.AT_THE_ARRIVAL_TRANSFER_TERMINAL);
            System.out.println("Passenger "+ p.getPassengerID() +" : takeABus()");
            if(this.queue.isEmpty()){
                this.parkBusArr.up();
            }
            this.queue.insertPassenger(p);
        }
        this.takeBus[p.getPassengerID()].down();
    }

    public PassengersHandler announcingBusBoarding(BusDriver b) {
        System.out.println("BusDriver: announcingBusBoarding: number of passengers in queue: "+ this.numPassengers());
        b.setBusDriverState(BusDriver.InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);
        //this.seats = b.getSeats();
        // this.takeBus.up();

        //if(this.queue.size() >= 3){
            for(int i = 0; i < 3; i++) {
                try {
                    int id = this.queue.removePassenger().getPassengerID();
                    this.takeBus[id].up();
                }catch (Exception e) {
                    //TODO: handle exception
                }
            }
        //}
        this.busBoard.down();
        
        return seats;
    }

    public int numPassengers(){
        return super.size();
    }

    public int seatsSize(){
        return seats.size();
    }
}