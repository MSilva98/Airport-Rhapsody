package airportrhapsody.mainProgram;

import airportrhapsody.mainProgram.Passenger.Situation;
import airportrhapsody.sharedRegions.*;

public class Porter extends Thread{
    public enum InternalState {
        WAITING_FOR_A_PLANE_TO_LAND,
        AT_THE_PLANES_HOLD,
        AT_THE_LUGGAGE_BELT_CONVEYOR,
        AT_THE_STOREROOM
    }


    private ArrivalLounge arrivalLounge;
    private TempStorageArea tempStorageArea;
    private CollectionPoint collPoint;
    private int porterID;
    private InternalState porterState;
    private int bagsCb; // number of pieces of luggage presently on the conveyor belt
    private int bagsSR; // number of pieces of luggage belonging to passengers in transit presently stored at the storeroo

    public Porter(int id, ArrivalLounge arrivalLounge, TempStorageArea tempStorageArea, CollectionPoint collPoint){
        this.porterState = InternalState.WAITING_FOR_A_PLANE_TO_LAND;
        this.porterID = id;
        this.arrivalLounge = arrivalLounge;
        this.tempStorageArea = tempStorageArea;
        this.collPoint = collPoint;
    }

    public Porter() {
        this.porterState = InternalState.WAITING_FOR_A_PLANE_TO_LAND;
    }

    @Override
    public void run(){
        System.out.println("Thread Porter");
        takeARest();
        Luggage l = arrivalLounge.tryToCollectABag(this);
        while(l != null){
            carryItToAppropriateStore(l);
            l = arrivalLounge.tryToCollectABag(this);
        }
        noMoreBagsToCollect();
    }

    private void takeARest() {
        this.setPorterState(InternalState.WAITING_FOR_A_PLANE_TO_LAND);
        // block porter
    }

    private void noMoreBagsToCollect() {
        System.out.println("Porter: " + "noMoreBags");
        this.setPorterState(InternalState.WAITING_FOR_A_PLANE_TO_LAND);
        takeARest();
    }

    private void carryItToAppropriateStore(Luggage l) {
        if(l.getSi() == Situation.FDT){
            collPoint.insertBag(l);
            this.setPorterState(InternalState.AT_THE_LUGGAGE_BELT_CONVEYOR);
            System.out.println("Porter: " + "carryItToAppropriateStore -> collPoint");
        }
        else{
            tempStorageArea.insertBag(l);
            this.setPorterState(InternalState.AT_THE_STOREROOM);
            System.out.println("Porter: " + "carryItToAppropriateStore -> tempStor");
        }
        
    }

    public InternalState getPorterState() {
        return this.porterState;
    }

    public void setPorterState(InternalState state) {
        this.porterState = state;
    }

    public int getBagsCb() {
        return this.bagsCb;
    }

    public void setBagsCb(int bagsCb) {
        this.bagsCb = bagsCb;
    }

    public int getBagsSR() {
        return this.bagsSR;
    }

    public void setBagsSR(int bagsSR) {
        this.bagsSR = bagsSR;
    }

}
