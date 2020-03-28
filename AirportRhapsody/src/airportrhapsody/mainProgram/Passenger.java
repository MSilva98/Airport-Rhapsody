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

import airportrhapsody.Logger;
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
    private Logger generalRepo;

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
        this.generalRepo.setSt(id, "ATDZ");
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
            if(this.nr == 0){
                arrTermExit.goHome(this);
            }else{
                // boolean success = true;
                for (int i = 0; i < this.nr; i++) {
                    if(collPoint.goCollectABag(this)){    
                        // System.out.println("Bag collected " + passengerID);
                        this.na++;
                    }
                }
                this.generalRepo.setNa(this.passengerID, this.na);
                if (this.nr != this.na){
                    System.out.println("REPORT " + passengerID + "  NA: " + na + " NR: " + nr);
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

    public void setupPassenger() {
        Situation s[] = Situation.values();
        Random rand = new Random(); 
        int rand_int1 = rand.nextInt(2);
        this.situation = s[rand_int1]; // randomize situation
        this.nr = rand.nextInt(3);
        int temp = rand.nextInt(nr+1);
        System.out.println("BAGS LOST " + temp + " SIT : " + situation + " " + passengerID + " NR: " + nr);
        for (int i = 0; i < nr - temp; i++) {
            this.arrivalLounge.putBag(new Luggage(this.passengerID, this.situation));
        }
        this.generalRepo.setBn(this.arrivalLounge.size());
        this.generalRepo.setSi(this.passengerID, this.situation.name());
        this.generalRepo.setNr(this.passengerID, this.nr);
        this.generalRepo.setNa(this.passengerID, 0);
        this.generalRepo.write(false);
    }

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
