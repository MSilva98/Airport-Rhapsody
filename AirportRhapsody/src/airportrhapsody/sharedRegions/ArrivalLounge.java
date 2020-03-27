package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.Passenger;
import airportrhapsody.mainProgram.Porter;

/**
 * ArrivalLounge
 */
public class ArrivalLounge extends LuggageHandler {

    private Semaphore rest;
    private PassengersHandler airport;
    private int numPassengers;
    private boolean dayEnd;

    public ArrivalLounge(int n,int numPassengers){
        super(n);
        this.numPassengers = numPassengers;
        this.airport = new PassengersHandler(numPassengers);
        this.rest = new Semaphore();
        dayEnd = false;
    }

    public void putBag(Luggage l){
        super.addLuggage(l);
    }

    public Luggage tryToCollectABag(Porter p){
        p.setPorterState(Porter.InternalState.AT_THE_PLANES_HOLD);
        return super.remLuggage();
    }

    public void rest(){
        rest.down();
    }

    public boolean takeARest(Porter p) {
        p.setPorterState(Porter.InternalState.WAITING_FOR_A_PLANE_TO_LAND);
        return dayEnd;
    }

    public boolean whatShouldIDo(Passenger p) {
        airport.insertPassenger(p);
        if(this.airport.size() == this.numPassengers){
            this.rest.up();
        }

        return (p.getSituation() == Passenger.Situation.FDT);
    }

    public boolean getDayEnd(){
        return dayEnd;
    }

    public void setDayEnd(boolean st){
        dayEnd = st;
    }


}