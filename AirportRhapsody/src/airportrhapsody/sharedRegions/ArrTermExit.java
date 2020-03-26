package airportrhapsody.sharedRegions;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import airportrhapsody.mainProgram.Passenger;

/**
 * ArrTermExit
 */
public class ArrTermExit extends PassengersHandler {

    private Barrier leaveAirp;
    private CyclicBarrier newBarrier;

    public ArrTermExit(int n) {
        super(n);
        leaveAirp = new Barrier(n);
        newBarrier = new CyclicBarrier(n);
    }

    public void leaveAirpDown() {
        // leaveAirp.down();
        try {
            newBarrier.await();
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

    public void goHome(Passenger p) {
        synchronized (this){
            this.insertPassenger(p);
            p.setPassengerState(Passenger.InternalState.EXITING_THE_ARRIVAL_TERMINAL);
            System.out.println("Passenger " + p.getPassengerID() + " : goHome");
            
        }
        //leaveAirp.down();
        try {
            newBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}