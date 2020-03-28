package airportrhapsody.sharedRegions;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import airportrhapsody.Logger;
import airportrhapsody.mainProgram.Passenger;

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

    public ArrTermExit(int n, ArrivalLounge arrivalLounge, ArrTransQuay arrTransQuay, int numbOfFlights, Logger generalRepo) {
        super(n);
        this.newBarrier = new CyclicBarrier(n);
        this.arrivalLounge = arrivalLounge;
        this.arrTransQuay = arrTransQuay;
        this.totalPassengers = n * numbOfFlights;
        this.counter = 0;
        this.generalRepo = generalRepo;
    }

    public void leaveAirpDown() {
        try {
            this.newBarrier.await();
            this.counter++;
            if(this.counter == this.totalPassengers){
                this.endOfWork();
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
        arrivalLounge.restUp();
    }

    public void goHome(Passenger p) {
        synchronized (this){
            this.insertPassenger(p);
            p.setPassengerState(Passenger.InternalState.EXITING_THE_ARRIVAL_TERMINAL);
            this.generalRepo.setSt(p.getPassengerID(), "EAT ");
            // this.generalRepo.write(false);
            System.out.println("Passenger " + p.getPassengerID() + " : goHome");
            
        }
        try {
            this.newBarrier.await();
            synchronized (this){
                this.counter++;
                if(this.counter == this.totalPassengers){
                    this.endOfWork();
                }
                
                this.removePassenger();
            }
            
        } catch (InterruptedException | BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}