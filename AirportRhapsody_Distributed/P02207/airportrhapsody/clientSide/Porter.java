package airportrhapsody.clientSide;

import airportrhapsody.serverSide.Logger.*;
import airportrhapsody.serverSide.ArrivalLounge.*;
import airportrhapsody.serverSide.CollectionPoint.*;
import airportrhapsody.serverSide.TempStorageArea.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import airportrhapsody.LoggerStub;
import airportrhapsody.comInf.*;

public class Porter extends Thread{
    /**
     * Porter internal state enumerate
     */
    public enum InternalState {
        WAITING_FOR_A_PLANE_TO_LAND,
        AT_THE_PLANES_HOLD,
        AT_THE_LUGGAGE_BELT_CONVEYOR,
        AT_THE_STOREROOM
    }

    /**
     * Arrival lounge
     * 
     * @serialField arrivalLounge
     */
    private ArrivalLoungeStub arrivalLounge;
    /**
     * Temporary storage area
     * 
     * @serialField tempStorageArea
     */
    private TempStorageAreaStub tempStorageArea;
    /**
     * Baggage collection point
     * 
     * @serialField collPoint
     */
    private CollectionPointStub collPoint;
    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
    private LoggerStub generalRepo;
    /**
     *  Porter identification
     * 
     *  @serialField porterID
     */
    private int porterID;
     /**
     *  Porter state
     * 
     *  @serialField passengerState
     */
    private InternalState porterState;
    
    private ArrTransQuayStub arrTransQuay;

    /**
     * Instantiating the porter thread.
     * @param id porter identification
     * @param arrivalLounge Arrival Lounge
     * @param tempStorageArea Temporary storage area
     * @param collPoint Baggage collection point
     * @param generalRepo general repository of information
     */
    public Porter(int id, ArrivalLoungeStub arrivalLounge, TempStorageAreaStub tempStorageArea, CollectionPointStub collPoint, ArrTransQuayStub arrTransQuay, LoggerStub generalRepo){
        this.porterState = InternalState.WAITING_FOR_A_PLANE_TO_LAND;
        this.porterID = id;
        this.arrivalLounge = arrivalLounge;
        this.tempStorageArea = tempStorageArea;
        this.collPoint = collPoint;
        this.arrTransQuay = arrTransQuay;
        this.generalRepo = generalRepo;
        this.generalRepo.setStatPorter("WPTL");
        this.generalRepo.setCb(this.collPoint.size());
        this.generalRepo.setSr(this.tempStorageArea.size());
     }

     /**
     * Porter thread life cycle
     */
    @Override
    public void run(){
        while (!this.arrivalLounge.takeARest()) {
            Luggage l = this.arrivalLounge.tryToCollectABag();
            this.setPorterState(InternalState.AT_THE_PLANES_HOLD);
            this.generalRepo.setStatPorter("APLH");
            this.generalRepo.write(false);
            while(l != null){
                this.carryItToAppropriateStore(l);
                l = this.arrivalLounge.tryToCollectABag();
            }
            this.setPorterState(this.collPoint.noMoreBagsToCollect());
            this.generalRepo.setStatPorter("WPTL");
            this.generalRepo.write(false);
        }
        this.shutdownServers();
    }

     /**
     * Shutdown servers
     */
    private void shutdownServers(){
        while(!this.arrivalLounge.getPassEnd());
        collPoint.shutdown();
        tempStorageArea.shutdown();
        arrivalLounge.setPrtEnd(true);
    }

    /**
     * Carry luggage to the approriate store
     * @param l bag to carry
     */
    private void carryItToAppropriateStore(Luggage l) {
        this.generalRepo.setBn(this.arrivalLounge.size());
        if(l.getSi() == Passenger.Situation.FDT){
            this.collPoint.insertBag(l);
            this.setPorterState(InternalState.AT_THE_LUGGAGE_BELT_CONVEYOR);
            this.generalRepo.setStatPorter("ALCB");
            this.generalRepo.setCb(this.collPoint.size());
        }
        else{
            this.tempStorageArea.insertBag(l);
            this.setPorterState(InternalState.AT_THE_STOREROOM);
            this.generalRepo.setStatPorter("ASTR");
            this.generalRepo.setSr(this.tempStorageArea.size());
        }
        this.generalRepo.write(false);
    }

    public InternalState getPorterState() {
        return this.porterState;
    }

    public void setPorterState(InternalState state) {
        this.porterState = state;
    }

}
