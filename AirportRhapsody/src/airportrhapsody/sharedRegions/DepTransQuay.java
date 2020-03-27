package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.BusDriver;
import airportrhapsody.mainProgram.Passenger;

/**
 * DepTransQuay
 */
public class DepTransQuay extends PassengersHandler {

    private Semaphore parkBusDep;
    private ArrTransQuay arrTransQuay;
    
    public DepTransQuay(int n, ArrTransQuay arrTransQuay){
        super(n);
        parkBusDep = new Semaphore();
        this.arrTransQuay = arrTransQuay;
    }

    public void leaveBus(Passenger p){
        synchronized(this){
            System.out.println("Passenger "+ p.getPassengerID() + ": leaveTheBus()");
            p.setPassengerState(Passenger.InternalState.AT_THE_DEPARTURE_TRANSFER_TERMINAL);
            super.insertPassenger(p);
            arrTransQuay.getSeats().removePassenger(p.getPassengerID());

            if(arrTransQuay.getSeats().isEmpty()){
                System.out.println("Bus empty back to arrival term");   
                parkBusDep.up();
            }
        }
        
    }

    public Passenger leaveDepTransQuay(int id){
        return super.removePassenger(id);
    }

    public void parkTheBusAndLetPassOff(BusDriver b){
        System.out.println("BusDriver: parkTheBusAndLetPassOff()");
        b.setBusDriverState(BusDriver.InternalState.PARKING_AT_THE_DEPARTURE_TERMINAL);
        arrTransQuay.enterBusUp();
        parkBusDep.down();
    }
}