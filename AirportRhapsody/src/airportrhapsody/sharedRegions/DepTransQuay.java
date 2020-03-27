package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.BusDriver;
import airportrhapsody.mainProgram.Passenger;

/**
 * DepTransQuay
 */
public class DepTransQuay extends PassengersHandler {

    private Semaphore parkBusDep;
    private ArrTransQuay arrTransQuay;
    private int idx;
    private int passengersInBus;

    public DepTransQuay(int n, ArrTransQuay arrTransQuay){
        super(n);
        parkBusDep = new Semaphore();
        idx = 0;
        this.arrTransQuay = arrTransQuay;
    }

    public void leaveBus(Passenger p){
        synchronized(this){
            System.out.println("Passenger "+ p.getPassengerID() + ": leaveTheBus()");
            p.setPassengerState(Passenger.InternalState.AT_THE_DEPARTURE_TRANSFER_TERMINAL);
            super.insertPassenger(p);
            idx++;
            // if(idx == arrTransQuay.seatsSize()){
            arrTransQuay.getSeats().removePassenger(p.getPassengerID());
            System.out.println("iddx= " + idx + " seatsSize= " + arrTransQuay.seatsSize());

            // System.out.println("Bus empty " + arrTransQuay.getSeats().isEmpty());
            if(arrTransQuay.getSeats().isEmpty()){
                System.out.println("Bus empty back to arrival term");   
                parkBusDep.up();
                idx = 0;
            }
        }
        
    }

    public Passenger leaveDepTransQuay(int id){
        return super.removePassenger(id);
    }

    public void parkTheBusAndLetPassOff(BusDriver b){
        System.out.println("BusDriver: parkTheBusAndLetPassOff()");
        b.setBusDriverState(BusDriver.InternalState.PARKING_AT_THE_DEPARTURE_TERMINAL);
        // for (int i = 0; i < b.getSeats().size(); i++) {
        //     arrTransQuay.enterBusUp();
        //     System.out.println("Passageiro libertado");
        // }
        arrTransQuay.enterBusUp();
        // Passenger[] seats = b.getSeats();
        // for(int i = 0; i < seats.length; i++) {
        //    this.leaveBus(seats[i], seats.length-1 == i); 
        // }
        parkBusDep.down();
        //passengersInBus = arrTransQuay.seatsSize();
        idx = 0;

    }
}