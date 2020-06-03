package airportrhapsody.clientSide;

import java.util.Random;

import airportrhapsody.serverSide.Logger.*;
import airportrhapsody.serverSide.ArrTermExit.*;
import airportrhapsody.serverSide.ArrTransQuay.*;
import airportrhapsody.serverSide.ArrivalLounge.*;
import airportrhapsody.serverSide.CollectionPoint.*;
import airportrhapsody.serverSide.DepTermEntrance.*;
import airportrhapsody.serverSide.DepTransQuay.*;
import airportrhapsody.serverSide.ReclaimOffice.*;  
import airportrhapsody.LoggerStub;
import airportrhapsody.comInf.*;

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
    private ArrivalLoungeStub arrivalLounge;
    /**
     * Baggage collection point
     * 
     * @serialField collPoint
     */
    private CollectionPointStub collPoint;
    /**
     * Baggage reclaim office
     * 
     * @serialField reclaimOffice
     */
    private ReclaimOfficeStub reclaimOffice;
    /**
     * Arrival terminal transfer quay
     * 
     * @serialField arrTransQuay
     */
    private ArrTransQuayStub arrTransQuay;
    /**
     * Departure terminal transfer quay
     * 
     * @serialField arrTransQuay
     */
    private DepTransQuayStub depTransQuay;
    /**
     * Arrival terminal exit
     * 
     * @serialField arrTermExit
     */
    private ArrTermExitStub arrTermExit;
    /**
     * Departure terminal entrance
     * 
     * @serialField depTermEntrance
     */
    private DepTermEntranceStub depTermEntrance;
    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
    private LoggerStub generalRepo;


    /**
     *  Counter number of flights
     * 
     *  @serialField counterFlights
     */
    private static int counterFlights = 0 ;

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
    public Passenger(int id, ArrivalLoungeStub arrivalLounge, CollectionPointStub collPoint, ReclaimOfficeStub reclaimOffice, ArrTransQuayStub arrTransQuay, DepTransQuayStub depTransQuay, ArrTermExitStub arrTermExit, DepTermEntranceStub depTermEntrance, LoggerStub generalRepo) {
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
        System.out.println("RUN 0");
        boolean isFinalDst = arrivalLounge.whatShouldIDo(this.situation);
        System.out.println("RUN 1");
        if(isFinalDst){
            System.out.println("RUN 2");
            if(this.nr == 0){
                System.out.println("RUN 3");
                this.passengerState = arrTermExit.arrivedTerm(this.passengerID);
                System.out.println("RUN 5");
                arrTermExit.goHome();
                System.out.println("RUN 6");
            }else{
                System.out.println("RUN 7");
                for (int i = 0; i < this.nr; i++) {
                    System.out.println("RUN 8");
                    if(!this.collPoint.getNoBags()){    // tentar corrigir para nao usar esta função
                        System.out.println("RUN 9");
                        this.setPassengerState(InternalState.AT_THE_LUGGAGE_COLLECTION_POINT);
                        System.out.println("RUN 10");
                        this.generalRepo.setSt(this.passengerID, "LCP");
                        System.out.println("RUN 11");
                        if(collPoint.goCollectABag(this.passengerID, this.nr)){ 
                            System.out.println("RUN 12");
                            this.na++;
                        }
                    }
                }
                System.out.println("RUN 13");
                collPoint.leaveCollPoint(passengerID);
                System.out.println("RUN 14");
                this.generalRepo.setNa(this.passengerID, this.na);
                System.out.println("RUN 15");
                if (this.nr != this.na){
                    System.out.println("RUN 16");
                    this.setPassengerState(this.reclaimOffice.reportMissingBags(nr - na));
                    System.out.println("RUN 17");
                    this.generalRepo.setSt(this.passengerID, "BRO");
                    System.out.println("RUN 18");
                    this.generalRepo.write(false);
                    System.out.println("RUN 19");
                }
                this.setPassengerState(arrTermExit.arrivedTerm(this.passengerID));
                System.out.println("RUN 20");
                arrTermExit.goHome();
                System.out.println("RUN 21");
            }
        }else{
            System.out.println("RUN 22");
            this.setPassengerState(InternalState.AT_THE_ARRIVAL_TRANSFER_TERMINAL);
            System.out.println("RUN 23");
            arrTransQuay.takeABus(this.passengerID);
            System.out.println("RUN 24");
            arrTransQuay.enterTheBus(this.passengerID);
            System.out.println("RUN 25");
            this.setPassengerState(depTransQuay.leaveBus(this.passengerID));
            System.out.println("RUN 26");
            this.setPassengerState(depTermEntrance.arrivedTerm(this.passengerID));
            System.out.println("RUN 27");
            depTermEntrance.prepareNextLeg();
            System.out.println("RUN 28");
        }

        this.shutdownServers();

    }

     /**
     * Shutdown servers
     */
    private void shutdownServers(){
        if(passengerID == 0 && counterFlights == 5){
            arrTermExit.shutdown();
            depTermEntrance.shutdown();
            generalRepo.setMissing(reclaimOffice.getNumBagsMissing());
            reclaimOffice.shutdown();
        }
    }

    /*
     * Setup passenger situtation and bags. 
     * Bags are randomly attributed (0,1 or 2) and the lost bags are randomly generated
     */
    public void setupPassenger() {
        counterFlights++;
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
