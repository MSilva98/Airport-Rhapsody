package airportrhapsody.entities;

import airportrhapsody.entities.Passenger.Situation;
import airportrhapsody.sharedRegions.*;
import airportrhapsody.commonInfrastructures.*;

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
    private ArrivalLounge arrivalLounge;
    /**
     * Temporary storage area
     * 
     * @serialField tempStorageArea
     */
    private TempStorageArea tempStorageArea;
    /**
     * Baggage collection point
     * 
     * @serialField collPoint
     */
    private CollectionPoint collPoint;
    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
    private Logger generalRepo;
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
    

    /**
     * Instantiating the porter thread.
     * @param id porter identification
     * @param arrivalLounge Arrival Lounge
     * @param tempStorageArea Temporary storage area
     * @param collPoint Baggage collection point
     * @param generalRepo general repository of information
     */
    public Porter(int id, ArrivalLounge arrivalLounge, TempStorageArea tempStorageArea, CollectionPoint collPoint, Logger generalRepo){
        this.porterState = InternalState.WAITING_FOR_A_PLANE_TO_LAND;
        this.porterID = id;
        this.arrivalLounge = arrivalLounge;
        this.tempStorageArea = tempStorageArea;
        this.collPoint = collPoint;
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
        this.arrivalLounge.restPorter();
        while (!this.arrivalLounge.takeARest()) {
            Luggage l = this.arrivalLounge.tryToCollectABag();
            this.porterState = Porter.InternalState.AT_THE_PLANES_HOLD;
            this.generalRepo.setStatPorter("APLH");
            this.generalRepo.write(false);
            while(l != null){
                this.carryItToAppropriateStore(l);
                l = this.arrivalLounge.tryToCollectABag();
            }
            this.porterState = this.collPoint.noMoreBagsToCollect();
            this.generalRepo.setStatPorter("WPTL");
            this.generalRepo.write(false);    
            this.arrivalLounge.restPorter();
        }
    }
    /**
     * Carry luggage to the approriate store
     * @param l bag to carry
     */
    private void carryItToAppropriateStore(Luggage l) {
        this.generalRepo.setBn(this.arrivalLounge.size());
        if(l.getSi() == Situation.FDT){
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
