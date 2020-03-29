package airportrhapsody.sharedRegions;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import airportrhapsody.Logger;
import airportrhapsody.entities.Passenger;
import airportrhapsody.commonInfrastructures.*;

/**
 * ArrTermExit
 */
public class ArrTermExit extends PassengersHandler {

    private CyclicBarrier newBarrier;
    private ArrivalLounge arrivalLounge;
    private ArrTransQuay arrTransQuay;
    private Logger generalRepo;
    private int totalPassengers;
    private int counter;
    private Semaphore[] test;
    private int blocked;

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
    }

    public void leaveAirpDown() {
        // try {
        //     this.newBarrier.await();
        //     synchronized(this){
        //         this.counter++;
        //         if(this.counter == this.totalPassengers){
        //             this.endOfWork();
        //         }
        //     }
        // } catch (InterruptedException | BrokenBarrierException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        synchronized(this){
            this.blocked++;
            System.out.println(blocked);
            this.counter++;
            if(this.counter == this.totalPassengers){
                this.endOfWork();
            }
        }
        if(this.blocked == test.length){
            for (int i = 0; i < test.length; i++) {
                this.blocked = 0;
                test[i].up();
            }
        }
        else{
            this.test[blocked-1].down();
        } 
    }

    public void arrivedTerm(Passenger p) {
        super.insertPassenger(p);
    }

    public Passenger leftTerm() {
        return super.removePassenger();
    }

    public boolean emptyTerm() {
        return super.isEmpty();
    }

    private void endOfWork(){
        arrivalLounge.setDayEnd(true);
        arrTransQuay.setDayEnd(true);
    }

    public void goHome(Passenger p) {
        synchronized (this){
            this.insertPassenger(p);
            p.setPassengerState(Passenger.InternalState.EXITING_THE_ARRIVAL_TERMINAL);
            this.generalRepo.setSt(p.getPassengerID(), "EAT");
            // this.generalRepo.write(false);
            System.out.println("Passenger " + p.getPassengerID() + " : goHome");
            
        }
        // try {
        //     this.newBarrier.await();
        //     synchronized (this){
        //         this.counter++;
        //         if(this.counter == this.totalPassengers){
        //             System.out.println("END OF WORK");
        //             this.endOfWork();
        //         }
                
        //         this.removePassenger();
        //     }
            
        // } catch (InterruptedException | BrokenBarrierException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        synchronized(this){
            this.blocked++;
            System.out.println(blocked);
            this.counter++;
            if(this.counter == this.totalPassengers){
                this.endOfWork();
            }
        }
        if(this.blocked == test.length){
            for (int i = 0; i < test.length; i++) {
                this.blocked = 0;
                this.removePassenger();
                test[i].up();
            }
        }
        else{
            this.test[blocked-1].down();
        }
    }
}