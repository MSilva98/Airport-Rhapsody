package airportrhapsody;

public class Porter extends Thread{
    enum InternalState {
        WAITING_FOR_A_PLANE_TO_LAND,
        AT_THE_PLANES_HOLD,
        AT_THE_LUGGAGE_BELT_CONVEYOR,
        AT_THE_STOREROOM
    }

    private InternalState porterState;
    private int bagsCb; // number of pieces of luggage presently on the conveyor belt
    private int bagsSR; // number of pieces of luggage belonging to passengers in transit presently stored at the storeroom


    @Override
    public void run(){
        System.out.println("Thread Porter");
    }

    private void takeARest() {
        
    }

    private void noMoreBagsToCollect() {
        
    }

    private void carryItToAppropriateStore() {
        
    }

    public Porter() {
        this.porterState = InternalState.WAITING_FOR_A_PLANE_TO_LAND;
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
