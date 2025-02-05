package airportrhapsody.serverSide.DepTermEntrance;

import airportrhapsody.LoggerStub;
import airportrhapsody.clientSide.ArrTermExitStub;
import airportrhapsody.clientSide.Passenger;
import airportrhapsody.comInf.*;
import airportrhapsody.serverSide.Logger.*;
import airportrhapsody.serverSide.ArrTermExit.*;

/**
 * Departure terminal entrance
 */
public class DepTermEntrance extends PassengersHandler{
    /**
     * Arrival Terminal Exit
     * 
     * @serialField arrTermExit
     */
    private ArrTermExitStub arrTermExit;
    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
    private LoggerStub generalRepo;


    /**
     * Instantiating the departure terminal entrance.
     * @param n number of passengers per flight 
     * @param arrTermExit Arrival Terminal Exit
     * @param generalRepo general repository of information
     */
    public DepTermEntrance(int n, ArrTermExitStub arrTermExit, LoggerStub generalRepo){
        super(n);
        this.arrTermExit = arrTermExit;
        this.generalRepo = generalRepo;
    }

    public DepTermEntrance(int n){
        super(n);
    }
    /**
     * Insert passenger in terminal
     * @param p passenger
     */
    public Passenger.InternalState arrivedTerm(int passengerID){
        this.generalRepo.setSt(passengerID, "EDT");
        this.generalRepo.write(false);
        return Passenger.InternalState.ENTERING_THE_DEPARTURE_TERMINAL;
    }

  
    
    /**
     * Prepare next leg
     * @param depTransQuay Departure terminal transfer quay
     * @param p passenger
     */
    public void prepareNextLeg(){
    
        arrTermExit.leaveAirpDown();
     
    }
}