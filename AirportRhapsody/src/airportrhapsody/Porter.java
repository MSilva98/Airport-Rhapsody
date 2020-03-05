package airportrhapsody;

public class Porter {
    enum InternalState {
        WAITING_FOR_A_PLANE_TO_LAND,
        AT_THE_PLANES_HOLD,
        AT_THE_LUGGAGE_BELT_CONVEYOR,
        AT_THE_STOREROOM
    }

    private InternalState state;
    private int bagsCb; // number of pieces of luggage presently on the conveyor belt
    private int bagsSR; // number of pieces of luggage belonging to passengers in transit presently stored at the storeroom

    private void takeARest() {
        
    }

    private void noMoreBagsToCollect() {
        
    }

    private void carryItToAppropriateStore() {
        
    }

    public Porter() {
        this.state = InternalState.WAITING_FOR_A_PLANE_TO_LAND;
    }

    public InternalState getState() {
        return this.state;
    }

    public void setState(InternalState state) {
        this.state = state;
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
