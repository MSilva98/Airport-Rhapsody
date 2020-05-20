package airportrhapsody.clientSide;

import airportrhapsody.serverSide.Logger.*;
import airportrhapsody.serverSide.ArrivalLounge.*;
import airportrhapsody.serverSide.CollectionPoint.*;
import airportrhapsody.serverSide.TempStorageArea.*;
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
    

    /**
     * Instantiating the porter thread.
     * @param id porter identification
     * @param arrivalLounge Arrival Lounge
     * @param tempStorageArea Temporary storage area
     * @param collPoint Baggage collection point
     * @param generalRepo general repository of information
     */
    public Porter(int id, ArrivalLoungeStub arrivalLounge, TempStorageAreaStub tempStorageArea, CollectionPointStub collPoint, LoggerStub generalRepo){
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
        System.out.println("RUN 0");
        //this.arrivalLounge.restPorter();
        System.out.println("RUN 1");
        while (!this.arrivalLounge.takeARest()) {
            System.out.println("RUN 2");
            Luggage l = this.arrivalLounge.tryToCollectABag();
            System.out.println("RUN 3");
            this.setPorterState(InternalState.AT_THE_PLANES_HOLD);
            System.out.println("RUN 4");
            this.generalRepo.setStatPorter("APLH");
            System.out.println("RUN 5");
            this.generalRepo.write(false);
            System.out.println("RUN 6");
            while(l != null){
                System.out.println("RUN 7");
                this.carryItToAppropriateStore(l);
                System.out.println("RUN 8");
                l = this.arrivalLounge.tryToCollectABag();
            }
            System.out.println("RUN 9");
            this.setPorterState(this.collPoint.noMoreBagsToCollect());
            System.out.println("RUN 10");
            this.generalRepo.setStatPorter("WPTL");
            System.out.println("RUN 11");
            this.generalRepo.write(false);
            System.out.println("RUN 12");   
            //this.arrivalLounge.restPorter();
        }
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
