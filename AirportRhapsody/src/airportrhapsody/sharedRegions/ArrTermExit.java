package airportrhapsody.sharedRegions;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import airportrhapsody.mainProgram.Passenger;

/**
 * ArrTermExit
 */
public class ArrTermExit extends PassengersHandler {

    private CyclicBarrier newBarrier;
    private ArrivalLounge arrivalLounge;
    private ArrTransQuay arrTransQuay;
    private int totalPassengers;
    private int counter;

    public ArrTermExit(int n, ArrivalLounge arrivalLounge, ArrTransQuay arrTransQuay, int numbOfFlights) {
        super(n);
        newBarrier = new CyclicBarrier(n);
        this.arrivalLounge = arrivalLounge;
        this.arrTransQuay = arrTransQuay;
        totalPassengers = n * numbOfFlights;
        counter = 0;
    }

    public void leaveAirpDown() {
        try {
            newBarrier.await();
            counter++;
            if(counter == totalPassengers){
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
    }

    public void goHome(Passenger p) {
        synchronized (this){
            this.insertPassenger(p);
            p.setPassengerState(Passenger.InternalState.EXITING_THE_ARRIVAL_TERMINAL);
            System.out.println("Passenger " + p.getPassengerID() + " : goHome");
            
        }
        try {
            newBarrier.await();
            synchronized (this){
                counter++;
                if(counter == totalPassengers){
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