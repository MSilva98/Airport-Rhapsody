package airportrhapsody.sharedRegions;

import airportrhapsody.entities.BusDriver;
import airportrhapsody.entities.Passenger;
import airportrhapsody.commonInfrastructures.*;

/**
 * Arrival terminal transfer quay
 */
public class ArrTransQuay extends PassengersHandler {
    /**
     * Semaphore that block bus driver thread
     * 
     * @serialField parkBusArr
     */
    private Semaphore parkBusArr;
    /**
     * Semaphore that block bus driver thread
     * 
     * @serialField busBoard
     */
    private Semaphore busBoard;
    /**
     * Array of semaphores that block passengers threads
     * 
     * @serialField busBoard
     */
    private Semaphore[] passengers;
    /**
     * Number of seats that bus can carry
     * 
     * @serialField seats 
     */
    private PassengersHandler seats;
    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
    private Logger generalRepo;
     /**
     * State of the day
     * 
     * @serialField dayEnd
     */
    private boolean dayEnd;


    /**
     * Instantiating the arrival terminal transfer quay.
     * @param n number of passengers
     * @param nseats    Number of seats that bus can carry
     * @param generalRepo general repository of information
     */
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
    /**
     * Free passengers
     */
    public void enterBusUp() {

        synchronized(this){
            PassengersHandler tmp = this.seats.copyTo(this.seats.getP());
            while(!tmp.isEmpty()) {
                Passenger p = tmp.removePassenger();
                            // System.out.println("UM GAJO SAIUUUUUUUUUUUUUUU\n" + this.seats.toString());
                if(p != null){
                    // System.out.println("THIS IS " + p.getPassengerID());
                    p.setPassengerState(Passenger.InternalState.TERMINAL_TRANSFER);
                    this.generalRepo.setSt(p.getPassengerID(), "TRT");
                    this.passengers[p.getPassengerID()].up();
                }
            }
            this.generalRepo.write(false);
        }
    }
    
    /**
     * Enter the bus
     * @param id passenger id
     */
    public void enterTheBus(int id){
        synchronized (this){

        // System.out.println("ENTER BUS " + id);
            this.seats.insertPassenger( super.removePassenger(id));
            this.generalRepo.setQ(super.size(), "-");
            this.generalRepo.setS(this.seats.size()-1, ""+id);
            this.generalRepo.write(false);
            //System.out.println("Bus full? " + this.seats.isFull() + " No Passengers in terminal? " + super.isEmpty());
            System.out.println("SEATS ENTER " + this.seats.toString());
            if(this.seats.isFull() || super.isEmpty()){
                this.busBoard.up();
                //System.out.println("BusBoard UP");
            }
        }
        this.passengers[id].down();
    }
    /**
     * Park the bus
     * @param b bus driver
     */
    public void parkTheBus(BusDriver b){
        b.setBusDriverState(BusDriver.InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL);  
        this.generalRepo.setStatDriver("PKAT");  
        // this.generalRepo.write(false);

        System.out.println("PPARK BUS");
        this.parkBusArr.down(2000);
    }
    /**
     * Take a bus
     * @param p passenger
     */
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
    /**
     * Announcing bus boarding
     */
    public void announcingBusBoarding() {
        int passEnter = super.size();
        
        int[] ids = super.getIDs();
        for (int i = 0; i < passEnter; i++) {
            if(i < seats.maxSize()){
                this.passengers[ids[i]].up();
            }
        }
        this.busBoard.down();
    }
    /**
     * Current number of passengers
     * @return
     */
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
        System.out.println("WORK DONE DRIVER");
        this.parkBusArr.up();
    }
}