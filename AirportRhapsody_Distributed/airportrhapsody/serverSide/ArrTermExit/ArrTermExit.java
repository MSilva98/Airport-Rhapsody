package airportrhapsody.serverSide.ArrTermExit;

import airportrhapsody.clientSide.Passenger;
import airportrhapsody.comInf.*;
import airportrhapsody.serverSide.ArrivalLounge.*;
import airportrhapsody.serverSide.ArrTransQuay.*;
import airportrhapsody.serverSide.Logger.*;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

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
     * Barrier
     * 
     * @serialField newBarrier
     */
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
        this.arrivalLounge = arrivalLounge;
        this.arrTransQuay = arrTransQuay;
        this.totalPassengers = n * numbOfFlights;
        this.counter = 0;
        this.generalRepo = generalRepo;
        this.newBarrier = new CyclicBarrier(n);
    }

    public ArrTermExit(int n, int numbOfFlights) {
        super(n);
        this.totalPassengers = n * numbOfFlights;
        this.counter = 0;
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
            e.printStackTrace();
        }
    }

    /**
     * Inser passenger in terminal
     * @param p passanger
     */
    public Passenger.InternalState arrivedTerm(int passengerID) {
        // super.insertPassenger(p);
        this.generalRepo.setSt(passengerID, "EAT");
        this.generalRepo.write(false);
        return Passenger.InternalState.EXITING_THE_ARRIVAL_TERMINAL;
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
    public void goHome() {
        // synchronized (this){
        //     this.insertPassenger(p);
        //     p.setPassengerState(Passenger.InternalState.EXITING_THE_ARRIVAL_TERMINAL);
        //     this.generalRepo.setSt(p.getPassengerID(), "EAT");
        // }
        try {
            this.newBarrier.await();
            synchronized (this){
                this.counter++;
                if(this.counter == this.totalPassengers){
                    this.endOfWork();
                }
                
                // this.leftTerm();
            }
            
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}