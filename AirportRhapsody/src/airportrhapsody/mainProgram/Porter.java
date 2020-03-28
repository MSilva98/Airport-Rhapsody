package airportrhapsody.mainProgram;

import airportrhapsody.Logger;
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
    private Logger generalRepo;
    private int porterID;
    private InternalState porterState;
  
    public Porter(int id, ArrivalLounge arrivalLounge, TempStorageArea tempStorageArea, CollectionPoint collPoint, Logger generalRepo){
        this.porterState = InternalState.WAITING_FOR_A_PLANE_TO_LAND;
        this.porterID = id;
        this.arrivalLounge = arrivalLounge;
        this.tempStorageArea = tempStorageArea;
        this.collPoint = collPoint;
        this.generalRepo = generalRepo;
        this.generalRepo.setStatPorter("WFPL");
        this.generalRepo.setCb(this.collPoint.size());
        this.generalRepo.setSr(this.tempStorageArea.size());
     }

    public Porter() {
        this.porterState = InternalState.WAITING_FOR_A_PLANE_TO_LAND;
    }

    @Override
    public void run(){
        System.out.println("Thread Porter");
        while (!this.arrivalLounge.takeARest(this)) {
            this.arrivalLounge.rest(); // começa bloqueado
            Luggage l = this.arrivalLounge.tryToCollectABag(this);
            while(l != null){
                this.carryItToAppropriateStore(l);
                l = this.arrivalLounge.tryToCollectABag(this);
            }
            this.collPoint.noMoreBagsToCollect(this);
        }
        
    }

    private void carryItToAppropriateStore(Luggage l) {
        this.generalRepo.setBn(this.arrivalLounge.size());
        if(l.getSi() == Situation.FDT){
            this.collPoint.insertBag(l);
            this.setPorterState(InternalState.AT_THE_LUGGAGE_BELT_CONVEYOR);
            System.out.println("Porter: " + "carryItToAppropriateStore -> collPoint");
            this.generalRepo.setStatPorter("ALBC");
            this.generalRepo.setCb(this.collPoint.size());
        }
        else{
            this.tempStorageArea.insertBag(l);
            this.setPorterState(InternalState.AT_THE_STOREROOM);
            System.out.println("Porter: " + "carryItToAppropriateStore -> tempStor");
            this.generalRepo.setStatPorter("ATST");
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
