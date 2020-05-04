package airportrhapsody.serverSide.ArrivalLounge;

import airportrhapsody.clientSide.Passenger;
import airportrhapsody.clientSide.Porter;
import airportrhapsody.comInf.*;

/**
 * ArrivalLounge
 */
public class ArrivalLounge extends LuggageHandler {
    /**
     * Semaphore that block porter thread
     * 
     * @serialField rest
     */
    private Semaphore rest;
    /**
     * State of the day
     * 
     * @serialField dayEnd
     */
    private boolean dayEnd;
    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
   // private Logger generalRepo;
    /**
     *  Number of passengers in the arrival lounge
     * 
     *  @serialField numPass
     */
    private int numPass;
    /**
     *  Total number of passengers in each plane
     * 
     *  @serialField numMaxPass
     */
    private int numMaxPass;

    /**
     * Instantiating the arrival lounge.
     * @param maxBags max number of bags
     * @param numPassengers number of passengers
     * @param generalRepo   general repository of information
     */
    public ArrivalLounge(int maxBags, int numPassengers /*, Logger generalRepo*/){
        super(maxBags);
        this.rest = new Semaphore();
        this.dayEnd = false;
        //this.generalRepo = generalRepo;
        this.numPass = 0;
        this.numMaxPass = numPassengers;
    }

    /**
     * Put a bag on the arrival lounge
     * @param l bag
     */
    public void putBag(Luggage l){
        super.addLuggage(l);
    }

    /**
     * Try to collect a bag from the arrival lounge
     * @param p porter
     * @return bag
     */
    public Luggage tryToCollectABag(){
        return super.remLuggage();
    }

    /**
     * block porter
     */
    public void restPorter(){
        rest.down();
    }
    /**
     * wake up porter
     */
    public void wakePorter(){
        rest.up();
    }

    /**
     * Take a rest
     * @param p porter
     * @return <li> true, if day is over
     *         <li> false, if isn't
     */
    public boolean takeARest() {
        return dayEnd;
    }

    /**
     * What should i do
     * @param p passenger
     * @return What passagenger should do according to his situation
     */
    public boolean whatShouldIDo(Passenger.Situation s) {
        synchronized(this){
            this.numPass++;
            if(this.numPass == this.numMaxPass){
                this.wakePorter();
                this.numPass = 0;
            }
        }
        return (s == Passenger.Situation.FDT);
    }

    public boolean getDayEnd(){
        return dayEnd;
    }

    public void setDayEnd(boolean st){
        dayEnd = st;
        this.wakePorter();
    }
}