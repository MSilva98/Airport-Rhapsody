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

    public DepTransQuay(int n, ArrTransQuay arrTransQuay){
        super(n);
        parkBusDep = new Semaphore();
        idx = 0;
        this.arrTransQuay = arrTransQuay;
    }

    public void leaveBus(Passenger p){
        p.setPassengerState(Passenger.InternalState.AT_THE_DEPARTURE_TRANSFER_TERMINAL);
        super.insertPassenger(p);
        idx++;
        if(idx == 3){
            parkBusDep.up();
        }
    }

    public Passenger leaveDepTransQuay(int id){
        return super.removePassenger(id);
    }

    public void parkTheBusAndLetPassOff(BusDriver b){
        b.setBusDriverState(BusDriver.InternalState.PARKING_AT_THE_DEPARTURE_TERMINAL);
        arrTransQuay.enterBusUp();
        // Passenger[] seats = b.getSeats();
        // for(int i = 0; i < seats.length; i++) {
        //    this.leaveBus(seats[i], seats.length-1 == i); 
        // }
        parkBusDep.down();
        //goToArrivalTerminal();
    }
}