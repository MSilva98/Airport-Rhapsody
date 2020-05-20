package airportrhapsody.serverSide.DepTransQuay;

import airportrhapsody.LoggerStub;
import airportrhapsody.clientSide.ArrTransQuayStub;
import airportrhapsody.clientSide.BusDriver;
import airportrhapsody.clientSide.Passenger;
import airportrhapsody.comInf.*;
import airportrhapsody.serverSide.Logger.*;
import airportrhapsody.serverSide.ArrTransQuay.*;
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
    private ArrTransQuayStub arrTransQuay;
    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
    private LoggerStub generalRepo;
    
    /**
     * Instantiating the departure terminal transfer quay.
     * @param n number of passengers per flight
     * @param arrTransQuay Arrival terminal transfer quay
     * @param generalRepo general repository of information
     */
    public DepTransQuay(int n, ArrTransQuayStub arrTransQuay, LoggerStub generalRepo){
        super(n);
        parkBusDep = new Semaphore();
        this.arrTransQuay = arrTransQuay;
        this.generalRepo = generalRepo;
    }


    public DepTransQuay(int n){
        super(n);
        parkBusDep = new Semaphore();
    }

    /**
     * Passenger leave the bus
     * @param p passenger
     */
    public Passenger.InternalState leaveBus(int passengerID){
        synchronized(this){
            // super.insertPassenger(p);
            this.arrTransQuay.remove(passengerID);
            this.generalRepo.setSt(passengerID, "DTT");
            this.generalRepo.setS(this.arrTransQuay.getSeats().size(), "-");
            this.generalRepo.write(false);
            System.out.println("LV - 0");
            System.out.println("SIZE= " + arrTransQuay.getSeats().size());
            if(arrTransQuay.getSeats().isEmpty()){
                System.out.println("LV - 1 UP BUSDRIVER");
                parkBusDep.up();
            }
            System.out.println("LV - 2");
        }
        
        return Passenger.InternalState.AT_THE_DEPARTURE_TRANSFER_TERMINAL;
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
    public BusDriver.InternalState parkTheBusAndLetPassOff(){
        System.out.println("PTBALPO - 0");
        arrTransQuay.enterBusUp();
        System.out.println("PTBALPO - 1");
        parkBusDep.down();
        System.out.println("PTBALPO - 2");
        this.generalRepo.setStatDriver("PKDT");
        this.generalRepo.write(false);
        return BusDriver.InternalState.PARKING_AT_THE_DEPARTURE_TERMINAL;
    }
}