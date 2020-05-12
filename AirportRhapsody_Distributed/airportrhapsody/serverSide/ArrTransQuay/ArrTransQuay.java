package airportrhapsody.serverSide.ArrTransQuay;

import java.util.ArrayList;
import java.util.List;

import airportrhapsody.LoggerStub;
import airportrhapsody.clientSide.BusDriver;
import airportrhapsody.clientSide.Passenger;
import airportrhapsody.comInf.*;
import airportrhapsody.serverSide.Logger.*;

/**
 * Arrival terminal transfer quay
 */
public class ArrTransQuay extends PassengersHandler {
    /**
     * Semaphore that block bus driver thread
     * 
     * @serialField parkBusArr
     */
    private Semaphore parkBusArr;
    /**
     * Semaphore that block bus driver thread
     * 
     * @serialField busBoard
     */
    private Semaphore busBoard;
    /**
     * Array of semaphores that block passengers threads
     * 
     * @serialField busBoard
     */
    private Semaphore[] passengers;
    /**
     * Number of seats that bus can carry
     * 
     * @serialField seats 
     */
    // private PassengersHandler seats;
    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
    private LoggerStub generalRepo;
     /**
     * State of the day
     * 
     * @serialField dayEnd
     */
    private boolean dayEnd;

    private List<Integer> passengersList;
    private List<Integer> seats;
    private int passEnter;
    private int passEnterCounter;
    private int nseats;

    /**
     * Instantiating the arrival terminal transfer quay.
     * @param n number of passengers
     * @param nseats    Number of seats that bus can carry
     * @param generalRepo general repository of information
     */
    public ArrTransQuay(int n, int nseats, LoggerStub generalRepo){
        super(n);
        this.seats = new ArrayList<>();
        this.parkBusArr = new Semaphore();
        this.busBoard = new Semaphore();
        this.passengers = new Semaphore[n];
        this.generalRepo = generalRepo;
        for (int i = 0; i < passengers.length; i++) {
            this.passengers[i] = new Semaphore();
            this.generalRepo.setQ(i, "-");
        }
        this.dayEnd = false;
        this.passengersList = new ArrayList<>();
        this.nseats = nseats;
        passEnter=0;
        passEnterCounter=0;
    }

    public ArrTransQuay(int n, int nseats){
        super(n);
        this.seats = new ArrayList<>();
        this.parkBusArr = new Semaphore();
        this.busBoard = new Semaphore();
        this.passengers = new Semaphore[n];
        for (int i = 0; i < passengers.length; i++) {
            this.passengers[i] = new Semaphore();
        }
        this.dayEnd = false;
        this.passengersList = new ArrayList<>();
        this.nseats = nseats;
        passEnter=0;
        passEnterCounter=0;
    }
    /**
     * Free passengers
     */
    public void enterBusUp() {

        synchronized(this){
            // PassengersHandler tmp = this.seats.copyTo(this.seats.getP());
            List<Integer> tmp = new ArrayList<>(this.seats);
            while(!tmp.isEmpty()) {
                int passengerID = tmp.remove(0);
                // if(passengerID != null){
                    // p.setPassengerState(Passenger.InternalState.TERMINAL_TRANSFER);
                    // this.generalRepo.setSt(p.getPassengerID(), "TRT");
                    this.passengers[passengerID].up();
                // }
            }
            // this.generalRepo.write(false);
        }
    }
    
    /**
     * Enter the bus
     * @param id passenger id
     */
    public void enterTheBus(int passengerID){
        synchronized (this){
            this.seats.add(this.passengersList.get(this.passengersList.indexOf(Integer.valueOf(passengerID))));
            this.passengersList.remove(Integer.valueOf(passengerID));
            this.generalRepo.setSt(passengerID, "TRT");
            this.generalRepo.setQ(super.size(), "-");
            this.generalRepo.setS(this.seats.size()-1, ""+passengerID);
            this.generalRepo.write(false);
            passEnterCounter++;
            if(passEnter == passEnterCounter){
                this.busBoard.up();
                passEnterCounter=0;
            }
        }
        this.passengers[passengerID].down();
    }
    /**
     * Park the bus
     * @param b bus driver
     */
    public void parkTheBus(){  
        // this.generalRepo.setStatDriver("PKAT");  
        this.parkBusArr.down(1);
    }
    /**
     * Take a bus
     * @param p passenger
     */
    public void takeABus(int passengerID) {
        synchronized(this){            
            // super.insertPassenger(p);
            System.out.println("pID take a bus - " + passengerID);
            this.passengersList.add(passengerID);
            this.generalRepo.setSt(passengerID, "ATT");    
            this.generalRepo.setQ(this.passengersList.size()-1, ""+passengerID);
            this.generalRepo.write(false);
            // System.out.println(this.passengersList.size() + "   " + passengerID);
            if(this.passengersList.size() == this.nseats){
                // System.out.println("WAKE UP BUS");
                this.parkBusArr.up();
            }
        }
        this.passengers[passengerID].down();
    }

    /**
     * Announcing bus boarding
     */
    public void announcingBusBoarding() {
        List<Integer> tmpL = new ArrayList<>(this.passengersList);
        passEnter = tmpL.size();
        int[] ids = new int[tmpL.size()];
        System.out.println(tmpL.size() + " tmpL: " + tmpL);
        for(int i = 0; i < ids.length; i++) {
            System.out.println(i + "   ->   " + tmpL);
            ids[i] = tmpL.get(i); 
        }
        if(passEnter > 3){
            passEnter = 3;
        }
        for (int i = 0; i < passEnter; i++) {
            //if(i < seats.maxSize()){
                // System.out.println("i="+i + " id =" + ids[i] + "   alll " + this.passengersList + " size " + passEnter);
                this.passengers[ids[i]].up();
            //}
        }
        this.busBoard.down();
    }
    /**
     * Current number of passengers
     * @return
     */
    public int numPassengers(){
        return super.size();
    }

    // public PassengersHandler getSeats(){
    //     return this.seats;
    // }

    public List<Integer> getSeats(){
        return this.seats;
    }

    public boolean getDayEnd(){
        return dayEnd;
    }

    public void setDayEnd(boolean st){
        dayEnd = st;
        this.parkBusArr.up();
    }

    @Override
    public boolean isEmpty(){
        return this.passengersList.isEmpty();
    }
}