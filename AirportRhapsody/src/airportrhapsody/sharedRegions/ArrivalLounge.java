package airportrhapsody.sharedRegions;

import airportrhapsody.Logger;
import airportrhapsody.mainProgram.Passenger;
import airportrhapsody.mainProgram.Porter;

/**
 * ArrivalLounge
 */
public class ArrivalLounge extends LuggageHandler {

    private Semaphore rest;
    private PassengersHandler airport;
    private boolean dayEnd;
    private Logger generalRepo;

    public ArrivalLounge(int maxBags,int numPassengers, Logger generalRepo){
        super(maxBags);
        this.airport = new PassengersHandler(numPassengers);
        this.rest = new Semaphore();
        this.dayEnd = false;
        this.generalRepo = generalRepo;
    }

    public void putBag(Luggage l){
        super.addLuggage(l);
    }

    public Luggage tryToCollectABag(Porter p){
        p.setPorterState(Porter.InternalState.AT_THE_PLANES_HOLD);
        this.generalRepo.setStatPorter("ATPH");
        this.generalRepo.write(false);
        return super.remLuggage();
    }

    public void restPorter(){
        rest.down();
    }

    public void wakePorter(){
        rest.up();
    }

    public boolean takeARest(Porter p) {
        // p.setPorterState(Porter.InternalState.WAITING_FOR_A_PLANE_TO_LAND);
        // this.generalRepo.setStatPorter("WFPL");
        // this.generalRepo.write(false);
        return dayEnd;
    }

    public boolean whatShouldIDo(Passenger p) {
        this.airport.insertPassenger(p);
        if(this.airport.isFull()){
            this.rest.up();
            this.airport.removeAll();
        }

        return (p.getSituation() == Passenger.Situation.FDT);
    }

    public boolean getDayEnd(){
        return dayEnd;
    }

    public void setDayEnd(boolean st){
        System.out.println("WORK DONE PORTER");
        dayEnd = st;
        rest.up();
    }
}