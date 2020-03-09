/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * 
 * Passenger bags are randomly attributed (0,1 or 2) and the lost bags are randomly generated
 */

package airportrhapsody;

import java.util.Random;

public class Passenger extends Thread{
    enum InternalState {
        AT_THE_DISEMBARKING_ZONE,
        AT_THE_LUGGAGE_COLLECTION_POINT,
        AT_THE_BAGGAGE_RECLAIM_OFFICE,
        EXITING_THE_ARRIVAL_TERMINAL,
        AT_THE_ARRIVAL_TRANSFER_TERMINAL,
        TERMINAL_TRANSFER,
        AT_THE_DEPARTURE_TRANSFER_TERMINAL,
        ENTERING_THE_DEPARTURE_TERMINAL
    }

    enum Situation {
        TRT,
        FDT
    }

    private InternalState passengerState;
    private Situation situation;
    private int nr; //number of pieces of luggage the passenger carried at the start of her journey
    private int na; //number of pieces of luggage the passenger she has presently collected

    public Passenger(Situation situation, int nr) {
        this.passengerState = InternalState.AT_THE_DISEMBARKING_ZONE;
        this.situation = situation;
        this.nr = nr;
    }

    public Passenger() {
        this.passengerState = InternalState.AT_THE_DISEMBARKING_ZONE;
        setupPassenger();
    }

    @Override
    public void run() {
        System.out.println("Thread Passenger");
        // boolean isFinalDst = whatShouldIDo();
        // if(isFinalDst){
        //     if(nr == 0){
        //         goHome();
        //     }else{
        //         boolean success;
        //         for (int i = 0; i < nr; i++) {
        //             success = goCollectABag();
        //             if (!success){
        //                 break;
        //             }
        //         }
        //         if (!success){
        //             reportMissingBags();
        //         }
        //         goHome();
        //     }
        // }else{
        //     takeABus();
        //     enterTheBus();
        //     prepareNextLeg();
        // }
    }

    public void setupPassenger() {
        Situation s[] = Situation.values();
        Random rand = new Random(); 
        int rand_int1 = rand.nextInt(2);
        this.situation = s[rand_int1]; // randomize situation
        this.nr = rand.nextInt(3); 
    }

    private void goHome() {
        
    }

    private boolean whatShouldIDo() {
        return true;
    }

    private void takeABus() {
        
    }

    private void prepareNextLeg() {
        
    }

    public InternalState getPassengerState() {
        return this.passengerState;
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


}
