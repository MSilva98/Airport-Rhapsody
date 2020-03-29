package airportrhapsody.sharedRegions;

import airportrhapsody.entities.BusDriver;
import airportrhapsody.entities.Passenger;
import airportrhapsody.commonInfrastructures.*;

/**
 * ArrTransQuay
 */
public class ArrTransQuay extends PassengersHandler {

    private Semaphore parkBusArr;
    private Semaphore busBoard;
    private Semaphore[] passengers;
    private PassengersHandler seats;
    private Logger generalRepo;
    private boolean dayEnd;

    public ArrTransQuay(int n, int nseats, Logger generalRepo){
        super(n);
        this.seats = new PassengersHandler(nseats);
        this.parkBusArr = new Semaphore();
        this.busBoard = new Semaphore();
        this.passengers = new Semaphore[n];
        this.generalRepo = generalRepo;
        for (int i = 0; i < passengers.length; i++) {
            this.passengers[i] = new Semaphore();
            this.generalRepo.setQ(i, "-");
        }
        this.dayEnd = false;
    }

    public void enterBusUp() {
        // int[] ids = this.seats.getIDs();
        Passenger[] p = this.seats.getP();
        for (int i = 0; i < p.length; i++) {
            if(p[i] != null){
                p[i].setPassengerState(Passenger.InternalState.TERMINAL_TRANSFER);
                this.generalRepo.setSt(p[i].getPassengerID(), "TRT");
                this.passengers[p[i].getPassengerID()].up();
            }
            // if(ids[i] != -1){    
            //     //System.out.println("Passenger " + ids[i] + " up");
            //     this.passengers[ids[i]].up();
            // }
        }
        this.generalRepo.write(false);
    }

    public void enterTheBus(int id){
        synchronized (this){
            System.out.println("Passenger "+ id + ": enterTheBus()");
            this.seats.insertPassenger( super.removePassenger(id));
            this.generalRepo.setQ(super.size(), "-");
            this.generalRepo.setS(this.seats.size()-1, ""+id);
            this.generalRepo.write(false);
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
        this.generalRepo.setStatDriver("PKAT");  
        // this.generalRepo.write(false);
        this.parkBusArr.down(2000);
    }

    public void takeABus(Passenger p) {
        synchronized(this){
            p.setPassengerState(Passenger.InternalState.AT_THE_ARRIVAL_TRANSFER_TERMINAL);
            this.generalRepo.setSt(p.getPassengerID(), "ATT");    
            // System.out.println("Passenger "+ p.getPassengerID() +" : takeABus()");
        
            super.insertPassenger(p);
            this.generalRepo.setQ(super.size()-1, ""+p.getPassengerID());
            this.generalRepo.write(false);
            
            if(super.size() == this.seats.maxSize()){
                this.parkBusArr.up();
            }
        }
        this.passengers[p.getPassengerID()].down();
    }

    public void announcingBusBoarding() {
        int passEnter = super.size();
        // System.out.println("BusDriver: announcingBusBoarding: number of passengers in queue: "+ this.numPassengers());
        
        int[] ids = super.getIDs();
        for (int i = 0; i < passEnter; i++) {
            if(i < seats.maxSize()){
                this.passengers[ids[i]].up();
            }
        }
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
        // System.out.println("WORK DONE DRIVER");
        dayEnd = st;
    }
}