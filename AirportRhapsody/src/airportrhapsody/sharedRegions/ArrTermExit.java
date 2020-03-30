package airportrhapsody.sharedRegions;

import airportrhapsody.entities.Passenger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import airportrhapsody.commonInfrastructures.*;

/**
 * Arrival terminal exit
 */
public class ArrTermExit extends PassengersHandler {
    /**
     * Arrival Lounge
     * 
     * @serialField arrivalLounge
     */
    private ArrivalLounge arrivalLounge;
    /**
     * Arrival terminal transfer quay
     * 
     * @serialField arrTransQuay
     */
    private ArrTransQuay arrTransQuay;
    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
    private Logger generalRepo;
    /**
     * total number of passengers
     * 
     * @serialField totalPassengers
     */
    private int totalPassengers;
    /**
     * Counter of passengers
     * 
     * @serialField counter
     */
    private int counter;
    /**
     * Array of semaphore that block passengers threads
     * 
     * @serialField test
     */
    private Semaphore[] test;
    private int blocked;

    private CyclicBarrier newBarrier;
    /**
     * Instantiating the arrival terminal exit.
     * @param n number of passenger per flight
     * @param arrivalLounge Arrival lounge
     * @param arrTransQuay Arrival terminal tranfer quay
     * @param numbOfFlights number of flights
     * @param generalRepo   general repository of information
     */
    public ArrTermExit(int n, ArrivalLounge arrivalLounge, ArrTransQuay arrTransQuay, int numbOfFlights, Logger generalRepo) {
        super(n);
        // this.newBarrier = new CyclicBarrier(n);
        this.test = new Semaphore[n];
        for (int i = 0; i < test.length; i++) {
            test[i] = new Semaphore();
        }
        this.blocked = 0;
        this.arrivalLounge = arrivalLounge;
        this.arrTransQuay = arrTransQuay;
        this.totalPassengers = n * numbOfFlights;
        this.counter = 0;
        this.generalRepo = generalRepo;
        this.newBarrier = new CyclicBarrier(n);
    }

    /**
     * Simulate barrier
     */
    public void leaveAirpDown() {
        try {
            this.newBarrier.await();
            synchronized(this){
                this.counter++;
                if(this.counter == this.totalPassengers){
                    this.endOfWork();
                }
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // synchronized(this){
        //     this.blocked++;
        //     System.out.println("BLOCKED " + blocked);
        //     this.counter++;
        // }
        // if(this.blocked == test.length){
        //     if(this.counter == this.totalPassengers){
        //         System.out.println("END WORK ALL OF THEM");
        //         this.endOfWork();
        //     }
        //     for (int i = 0; i < test.length; i++) {
        //         this.blocked = 0;
        //         test[i].up();
        //     }
        // }
        // else{
        //     this.test[blocked-1].down();
        // } 
    }

    /**
     * Inser passenger in terminal
     * @param p passanger
     */
    public void arrivedTerm(Passenger p) {
        super.insertPassenger(p);
    }
    /**
     * Remove passenger from terminal
     * @return passenger
     */
    public Passenger leftTerm() {
        return super.removePassenger();
    }


    /**
     * Check if terminal is empty
     * @return true if is empty
     */
    public boolean emptyTerm() {
        return super.isEmpty();
    }
    /**
     * Set the end of the work
     */
    private void endOfWork(){
        arrivalLounge.setDayEnd(true);
        arrTransQuay.setDayEnd(true);
    }
    /**
     * Go home
     * @param p passenger
     */
    public void goHome(Passenger p) {
        synchronized (this){
            this.insertPassenger(p);
            p.setPassengerState(Passenger.InternalState.EXITING_THE_ARRIVAL_TERMINAL);
            this.generalRepo.setSt(p.getPassengerID(), "EAT");
            System.out.println("GO HOME " + p.getPassengerID());
            
        }
        try {
            this.newBarrier.await();
            synchronized (this){
                this.counter++;
                if(this.counter == this.totalPassengers){
                    System.out.println("END OF WORK");
                    this.endOfWork();
                }
                
                this.removePassenger();
            }
            
        } catch (InterruptedException | BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // synchronized(this){
        //     this.blocked++;
        // System.out.println("GO HOME " + p.getPassengerID());
        // System.out.println("BLOCKED " + blocked);
        //     this.counter++;
        // }
        // if(this.blocked == test.length){
        //     if(this.counter == this.totalPassengers){
        //         System.out.println("END WORK ALL OF THEM");
        //         this.endOfWork();
        //     }
        //     for (int i = 0; i < test.length; i++) {
        //         this.blocked = 0;
        //         this.removePassenger();
        //         test[i].up();
        //     }
        // }
        // else{
        //     this.test[blocked-1].down();
        // }
    }
}