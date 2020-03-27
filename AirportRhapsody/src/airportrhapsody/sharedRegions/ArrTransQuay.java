package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.BusDriver;
import airportrhapsody.mainProgram.Passenger;


/**
 * ArrTransQuay
 */
public class ArrTransQuay extends PassengersHandler {

    private Semaphore parkBusArr;
    private Semaphore busBoard;
    private Semaphore[] passengers;
    private PassengersHandler seats;
    private boolean dayEnd;

    public ArrTransQuay(int n, int nseats){
        super(n);
        this.seats = new PassengersHandler(nseats);
        this.parkBusArr = new Semaphore();
        this.busBoard = new Semaphore();
        this.passengers = new Semaphore[n];
        for (int i = 0; i < passengers.length; i++) {
            passengers[i] = new Semaphore(); 
        }
        dayEnd = false;
    }

    public void enterBusUp() {
        int[] ids = seats.getIDs();
        for (int i = 0; i < ids.length; i++) {
            if(ids[i] != -1){    
                //System.out.println("Passenger " + ids[i] + " up");
                this.passengers[ids[i]].up();
            }
        }
    }

    public  void enterTheBus(int id){
        synchronized (this){
            System.out.println("Passenger "+ id + ": enterTheBus()");
            this.seats.insertPassenger( super.removePassenger(id));
            //System.out.println("Bus full? " + this.seats.isFull() + " No Passengers in terminal? " + super.isEmpty());
            if(this.seats.isFull() || super.isEmpty()){
                this.busBoard.up();
                //System.out.println("BusBoard UP");
            }
        }
        this.passengers[id].down();
    }

    public void parkTheBus(BusDriver b){
        System.out.println("BusDriver: parkTheBus");
        b.setBusDriverState(BusDriver.InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);    
        this.parkBusArr.down(5000);
    }

    public void takeABus(Passenger p) {
        synchronized(this){
            p.setPassengerState(Passenger.InternalState.AT_THE_ARRIVAL_TRANSFER_TERMINAL);
            System.out.println("Passenger "+ p.getPassengerID() +" : takeABus()");
            
            super.insertPassenger(p);
            //System.out.println(super.toString());
            
            if(super.size() == this.seats.maxSize()){
                //System.out.println("Wake up bus driver");
                this.parkBusArr.up();
            }
        }
        this.passengers[p.getPassengerID()].down();
    }

    public void announcingBusBoarding(BusDriver b) {
        int passEnter = super.size();
        System.out.println("BusDriver: announcingBusBoarding: number of passengers in queue: "+ this.numPassengers());
        b.setBusDriverState(BusDriver.InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);

        int[] ids = super.getIDs();
        
        for (int i = 0; i < passEnter; i++) {
            if(i < seats.maxSize()){
                //System.out.println("Passenger " + ids[i] + " unblocked");
                this.passengers[ids[i]].up();
            }
        }
        //System.out.println("Bus driver blocked at busBoard");
        this.busBoard.down();
    }

    public int numPassengers(){
        return super.size();
    }

    public PassengersHandler getSeats(){
        return this.seats;
    }

    public boolean getDayEnd(){
        return dayEnd;
    }

    public void setDayEnd(boolean st){
        dayEnd = st;
    }
}