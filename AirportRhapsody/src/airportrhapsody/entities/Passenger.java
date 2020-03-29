package airportrhapsody.entities;

import java.util.Random;

import airportrhapsody.sharedRegions.*;
import airportrhapsody.commonInfrastructures.*;

/**
 * This class implements the passenger thread
 */

public class Passenger extends Thread{

    /**
     * Passenger internal state enumerate
     */
    public enum InternalState {
        AT_THE_DISEMBARKING_ZONE,
        AT_THE_LUGGAGE_COLLECTION_POINT,
        AT_THE_BAGGAGE_RECLAIM_OFFICE,
        EXITING_THE_ARRIVAL_TERMINAL,
        AT_THE_ARRIVAL_TRANSFER_TERMINAL,
        TERMINAL_TRANSFER,
        AT_THE_DEPARTURE_TRANSFER_TERMINAL,
        ENTERING_THE_DEPARTURE_TERMINAL
    }

    /**
     * Passenger situation enumerate
     */

    public enum Situation {
        TRT,
        FDT
    }


    /**
     *  Passenger identification
     * 
     *  @serialField busDriveID
     */
    private int passengerID;

    /**
     *  Passenger state
     * 
     *  @serialField passengerState
     */
    private InternalState passengerState;
    /**
     *  Passenger situation
     * 
     *  @serialField situation
     */
    private Situation situation;
    /**
     * Number of pieces of luggage the passenger carried at the start of her journey
     * 
     * @serialField nr
     */
    private int nr;
    /**
     * Number of pieces of luggage the passenger she has presently collected
     * 
     * @serialField na
     */
    private int na; 
    /**
     * Arrival lounge
     * 
     * @serialField arrivalLounge
     */
    private ArrivalLounge arrivalLounge;
    /**
     * Baggage collection point
     * 
     * @serialField collPoint
     */
    private CollectionPoint collPoint;
    /**
     * Baggage reclaim office
     * 
     * @serialField reclaimOffice
     */
    private ReclaimOffice reclaimOffice;
    /**
     * Arrival terminal transfer quay
     * 
     * @serialField arrTransQuay
     */
    private ArrTransQuay arrTransQuay;
    /**
     * Departure terminal transfer quay
     * 
     * @serialField arrTransQuay
     */
    private DepTransQuay depTransQuay;
    /**
     * Arrival terminal exit
     * 
     * @serialField arrTermExit
     */
    private ArrTermExit arrTermExit;
    /**
     * Departure terminal entrance
     * 
     * @serialField depTermEntrance
     */
    private DepTermEntrance depTermEntrance;
    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
    private Logger generalRepo;

    /**
     * Instantiating the passenger thread.
     * @param id passanger identification
     * @param arrivalLounge Arrival Lounge
     * @param collPoint Baggage colletion point
     * @param reclaimOffice Baggage reclaim office
     * @param arrTransQuay  Arrival terminal transfer quay
     * @param depTransQuay  Departure terminal transfer quay
     * @param arrTermExit   Arrival terminal exit
     * @param depTermEntrance   Departure terminal entrance
     * @param generalRepo   General repository of information
     */
    public Passenger(int id, ArrivalLounge arrivalLounge, CollectionPoint collPoint, ReclaimOffice reclaimOffice, ArrTransQuay arrTransQuay, DepTransQuay depTransQuay, ArrTermExit arrTermExit, DepTermEntrance depTermEntrance, Logger generalRepo) {
        this.passengerID = id;
        this.passengerState = InternalState.AT_THE_DISEMBARKING_ZONE;
        this.na = 0;
        this.arrivalLounge = arrivalLounge;
        this.collPoint = collPoint;
        this.reclaimOffice = reclaimOffice;
        this.arrTransQuay = arrTransQuay;
        this.depTransQuay = depTransQuay;
        this.arrTermExit = arrTermExit;
        this.depTermEntrance = depTermEntrance;
        this.generalRepo = generalRepo;
        setupPassenger();
    }

    /**
     * Passenger thread life cycle
     */

    @Override
    public void run() {
        boolean isFinalDst = arrivalLounge.whatShouldIDo(this);
        if(isFinalDst){
            if(this.nr == 0){
                arrTermExit.goHome(this);
            }else{
                for (int i = 0; i < this.nr; i++) {
                    if(collPoint.goCollectABag(this)){ 
                        this.na++;
                    }
                    else{
                        break;
                    }
                }
                this.generalRepo.setNa(this.passengerID, this.na);
                if (this.nr != this.na){
                    reclaimOffice.reportMissingBags(nr - na, this);
                }
                arrTermExit.goHome(this);
            }
        }else{
            arrTransQuay.takeABus(this);
            arrTransQuay.enterTheBus(passengerID);
            depTransQuay.leaveBus(this);
            depTermEntrance.prepareNextLeg(depTransQuay, this);
        }
    }
    /*
     * Setup passenger situtation and bags. 
     * Bags are randomly attributed (0,1 or 2) and the lost bags are randomly generated
     */
    public void setupPassenger() {
        Situation s[] = Situation.values();
        Random rand = new Random(); 
        int rand_int1 = rand.nextInt(2);
        this.situation = s[rand_int1]; // randomize situation
        this.nr = rand.nextInt(3);
        int temp = rand.nextInt(nr+1);
        for (int i = 0; i < nr - temp; i++) {
            this.arrivalLounge.putBag(new Luggage(this.passengerID, this.situation));
        }
        this.generalRepo.setSt(passengerID, "WSD");
        this.generalRepo.setBn(this.arrivalLounge.size());
        this.generalRepo.setSi(this.passengerID, this.situation.name());
        this.generalRepo.setNr(this.passengerID, this.nr);
        this.generalRepo.setNa(this.passengerID, 0);
        this.generalRepo.write(false);
    }
    /**
     * Get the passenger state
     * @return passenger state
     */
    public InternalState getPassengerState() {
        return this.passengerState;
    }
    /**
     * Get passenger ID
     * @return passenger ID
     */
    public int getPassengerID() {
        return this.passengerID;
    }
    /**
     * Get the passenger state
     * @param state passenger state
     */
    public void setPassengerState(InternalState state) {
        this.passengerState = state;
    }

    /**
     * Get situation
     * @return
     */
    public Situation getSituation() {
        return this.situation;
    }
    /**
     * Set situation
     * @param situation passenger situation
     */
    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    public int getNr() {
        return this.nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getNa() {
        return this.na;
    }

    public void setNa(int na) {
        this.na = na;
    }

    @Override
    public String toString(){
        return "{" +
            "passenger ID: " + this.getPassengerID()
            + "}";
    }
}
