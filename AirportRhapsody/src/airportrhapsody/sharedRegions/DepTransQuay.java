package airportrhapsody.sharedRegions;

import airportrhapsody.entities.BusDriver;
import airportrhapsody.entities.Passenger;
import airportrhapsody.commonInfrastructures.*;
/**
 * Departure terminal transfer quay
 */
public class DepTransQuay extends PassengersHandler {
    /**
     * Semaphore that block busDriver thread
     * 
     * @serialField parkBusDep
     */
    private Semaphore parkBusDep;
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
     * Instantiating the departure terminal transfer quay.
     * @param n number of passengers per flight
     * @param arrTransQuay Arrival terminal transfer quay
     * @param generalRepo general repository of information
     */
    public DepTransQuay(int n, ArrTransQuay arrTransQuay, Logger generalRepo){
        super(n);
        parkBusDep = new Semaphore();
        this.arrTransQuay = arrTransQuay;
        this.generalRepo = generalRepo;
    }

    /**
     * Passenger leave the bus
     * @param p passenger
     */
    public void leaveBus(Passenger p){
        synchronized(this){
            p.setPassengerState(Passenger.InternalState.AT_THE_DEPARTURE_TRANSFER_TERMINAL);
            super.insertPassenger(p);
            this.arrTransQuay.getSeats().removePassenger(p.getPassengerID());
            this.generalRepo.setSt(p.getPassengerID(), "DTT");
            this.generalRepo.setS(this.arrTransQuay.getSeats().size(), "-");
            this.generalRepo.write(false);
            
            if(arrTransQuay.getSeats().isEmpty()){
                parkBusDep.up();
            }
        }
        
    }

    /**
     * Passenger leave departure terminal transfer quay
     * @param id passenger id
     * @return  passenger
     */
    public Passenger leaveDepTransQuay(int id){
        return super.removePassenger(id);
    }
    /**
     * Park the bus and let pass off
     * @param b bus driver
     */
    public void parkTheBusAndLetPassOff(BusDriver b){
        b.setBusDriverState(BusDriver.InternalState.PARKING_AT_THE_DEPARTURE_TERMINAL);
        this.generalRepo.setStatDriver("PKDT");
        arrTransQuay.enterBusUp();
        this.generalRepo.write(false);
        parkBusDep.down();
    }
}