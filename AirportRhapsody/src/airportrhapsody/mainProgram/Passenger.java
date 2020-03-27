/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * 
 * Passenger bags are randomly attributed (0,1 or 2) and the lost bags are randomly generated
 */

package airportrhapsody.mainProgram;

import java.util.Random;
import airportrhapsody.sharedRegions.*;

public class Passenger extends Thread{
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

    public enum Situation {
        TRT,
        FDT
    }

    private int passengerID;

    private InternalState passengerState;
    private Situation situation;
    private int nr; //number of pieces of luggage the passenger carried at the start of her journey
    private int na; //number of pieces of luggage the passenger she has presently collected

    private ArrivalLounge arrivalLounge;
    private CollectionPoint collPoint;
    private ReclaimOffice reclaimOffice;
    private ArrTransQuay arrTransQuay;
    private DepTransQuay depTransQuay;
    private ArrTermExit arrTermExit;
    private DepTermEntrance depTermEntrance;

    public Passenger(int id, ArrivalLounge arrivalLounge, CollectionPoint collPoint, ReclaimOffice reclaimOffice, ArrTransQuay arrTransQuay, DepTransQuay depTransQuay, ArrTermExit arrTermExit, DepTermEntrance depTermEntrance) {
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
        setupPassenger();

    }

    public Passenger() {
        this.passengerState = InternalState.AT_THE_DISEMBARKING_ZONE;
        setupPassenger();
    }

    public Passenger(int id) {
        this.passengerState = InternalState.AT_THE_DISEMBARKING_ZONE;
        setupPassenger();
        this.passengerID = id;
    }

    @Override
    public void run() {
        System.out.println("Thread Passenger " + passengerID + " - nr: " + nr + " situation: " + situation);
        boolean isFinalDst = arrivalLounge.whatShouldIDo(this);
        if(isFinalDst){
            if(nr == 0){
                arrTermExit.goHome(this);
            }else{
                boolean success = true;
                for (int i = 0; i < nr; i++) {
                    success = collPoint.goCollectABag(this);
                    if (!success){
                        break;
                    }
                    na++;
                }
                if (!success){
                    reclaimOffice.reportMissingBags(nr - na, this);
                }
                arrTermExit.goHome(this);
                System.out.println("FINISH");
            }
        }else{
            arrTransQuay.takeABus(this);
            arrTransQuay.enterTheBus(passengerID);
            depTransQuay.leaveBus(this);
            depTermEntrance.prepareNextLeg(depTransQuay, this);
            System.out.println("FINISH");
        }
    }

    public void setupPassenger() {
        Situation s[] = Situation.values();
        Random rand = new Random(); 
        int rand_int1 = rand.nextInt(2);
        this.situation = s[rand_int1]; // randomize situation
        this.nr = rand.nextInt(3);
        int temp = rand.nextInt(nr+1);
        // System.out.println("BAGS LOST " + (nr - temp) + "SIT : " + situation );
        for (int i = 0; i < nr - temp; i++) {
            arrivalLounge.putBag(new Luggage(passengerID,situation));
        }
    }

    // private void goHome() {
    //     arrTermExit.insertPassenger(this); 
    //     this.setPassengerState(InternalState.EXITING_THE_ARRIVAL_TERMINAL);
    //     System.out.println("Passenger "+ passengerID+" : goHome");
    // }

    // private boolean whatShouldIDo() {
    //     //  Wake up porter
    //     return (situation == Situation.FDT);
    // }

    // private void takeABus() {
    //     arrTransQuay.arrived(this);
    //     this.setPassengerState(InternalState.AT_THE_ARRIVAL_TRANSFER_TERMINAL);
    //     System.out.println("Passenger "+ passengerID+" : takeABus()");
    // }

    // public void prepareNextLeg(){
    //     depTermEntrance.arrivedTerm(depTransQuay.leaveDepTransQuay(this.passengerID));
    //     this.setPassengerState(InternalState.ENTERING_THE_DEPARTURE_TERMINAL);
    //     System.out.println("Passenger "+ passengerID+" : prepareNextLeg");
    // }

    public InternalState getPassengerState() {
        return this.passengerState;
    }

    public int getPassengerID() {
        return this.passengerID;
    }

    public void setPassengerState(InternalState state) {
        this.passengerState = state;
    }

    public Situation getSituation() {
        return this.situation;
    }

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
