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

    public ArrivalLounge(int n,int numPassengers){
        super(n);
        this.numPassengers = numPassengers;
        this.airport = new PassengersHandler(numPassengers);
        this.rest = new Semaphore();
    }

    public void putBag(Luggage l){
        super.addLuggage(l);
    }

    public Luggage tryToCollectABag(Porter p){
        p.setPorterState(Porter.InternalState.AT_THE_PLANES_HOLD);
        return super.remLuggage();
    }


    public void takeARest(Porter p) {
        p.setPorterState(Porter.InternalState.WAITING_FOR_A_PLANE_TO_LAND);
        // block porter
        rest.down();
    }

    public boolean whatShouldIDo(Passenger p) {
        //  Wake up porter
        airport.insertPassenger(p);
        if(this.airport.size() == this.numPassengers){
            this.rest.up();
        }

        return (p.getSituation() == Passenger.Situation.FDT);
    }

    // @Override
    // public String toString() {
    //     return "luggage = " + getLug();
    // }

}