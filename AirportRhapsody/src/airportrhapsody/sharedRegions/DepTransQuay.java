package airportrhapsody.sharedRegions;

import airportrhapsody.entities.BusDriver;
import airportrhapsody.entities.Passenger;
import airportrhapsody.commonInfrastructures.*;
/**
 * DepTransQuay
 */
public class DepTransQuay extends PassengersHandler {

    private Semaphore parkBusDep;
    private ArrTransQuay arrTransQuay;
    private Logger generalRepo;
    
    public DepTransQuay(int n, ArrTransQuay arrTransQuay, Logger generalRepo){
        super(n);
        parkBusDep = new Semaphore();
        this.arrTransQuay = arrTransQuay;
        this.generalRepo = generalRepo;
    }

    public void leaveBus(Passenger p){
        synchronized(this){
            System.out.println("Passenger "+ p.getPassengerID() + ": leaveTheBus()");
            p.setPassengerState(Passenger.InternalState.AT_THE_DEPARTURE_TRANSFER_TERMINAL);
            super.insertPassenger(p);
            this.arrTransQuay.getSeats().removePassenger(p.getPassengerID());
            this.generalRepo.setSt(p.getPassengerID(), "DTT");
            this.generalRepo.setS(this.arrTransQuay.getSeats().size(), "-");
            this.generalRepo.write(false);
            
            if(arrTransQuay.getSeats().isEmpty()){
                // System.out.println("Bus empty back to arrival term");   
                parkBusDep.up();
            }
        }
        
    }

    public Passenger leaveDepTransQuay(int id){
        return super.removePassenger(id);
    }

    public void parkTheBusAndLetPassOff(BusDriver b){
        // System.out.println("BusDriver: parkTheBusAndLetPassOff()");
        b.setBusDriverState(BusDriver.InternalState.PARKING_AT_THE_DEPARTURE_TERMINAL);
        this.generalRepo.setStatDriver("PKDT");
        arrTransQuay.enterBusUp();
        this.generalRepo.write(false);
        parkBusDep.down();
    }
}