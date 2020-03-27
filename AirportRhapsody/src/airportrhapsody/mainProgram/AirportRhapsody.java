package airportrhapsody.mainProgram;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import airportrhapsody.Logger;
import airportrhapsody.sharedRegions.*;

public class AirportRhapsody {

    public static void main(String[] args) {

        int nPassengers = 6 ;                               // number of passengers
        Passenger[] passenger = new Passenger[nPassengers]; // Passenger threads array
        Porter porter;                                      // thread porter
        BusDriver busDriver;                                // thread bus driver
        int nPlaneLandings = 5;                             // number of plane landings
        int nSeatingPlaces = 3;                             // bus capacity
        int maxBags = 2;                                    // maximum luggage
        ArrivalLounge arrivalLounge;
        CollectionPoint collPoint;
        ReclaimOffice reclaimOffice;
        ArrTransQuay arrTransQuay;
        DepTransQuay depTransQuay;
        ArrTermExit arrTermExit;
        DepTermEntrance depTermEntrance;
        TempStorageArea tempStorageArea;
        Logger generalRepo;

        //Problem config

        //Sharing region

        arrivalLounge = new ArrivalLounge(nPassengers*maxBags,nPassengers);
        collPoint = new CollectionPoint(nPassengers*maxBags, nPassengers);
        reclaimOffice = new ReclaimOffice();
        arrTransQuay = new ArrTransQuay(nPassengers, nSeatingPlaces);
        depTransQuay = new DepTransQuay(nPassengers, arrTransQuay);
        arrTermExit = new ArrTermExit(nPassengers, arrivalLounge, arrTransQuay, nPlaneLandings);
        depTermEntrance = new DepTermEntrance(nPassengers, arrTermExit);
        tempStorageArea = new TempStorageArea(nPassengers);
        generalRepo = new Logger(nSeatingPlaces, nPassengers, "log.txt");

        //entities
        porter = new Porter(1, arrivalLounge, tempStorageArea, collPoint);
        
        busDriver = new BusDriver(1,nSeatingPlaces,arrTransQuay,depTransQuay);
        

        //Start Simullation
        porter.start();
        busDriver.start();

        for (int j = 0; j < nPlaneLandings; j++) {
            for (int i = 0; i < nPassengers; i++){
                passenger[i] = new Passenger(i, arrivalLounge, collPoint, reclaimOffice, arrTransQuay, depTransQuay, arrTermExit, depTermEntrance);
            }
            for (int i = 0; i < nPassengers; i++){
                passenger[i].start ();
            }
    
            //End simulation
    
            for (int i = 0; i < nPassengers; i++){
                try
                { 
                    passenger[i].join();
                }
                catch (InterruptedException e) {System.out.println("O passenger "+  i + " exceção.");}
                System.out.println("O passenger "+  i + " terminou.");
            }
        }
        try
        { 
            busDriver.join();
        }
        catch (InterruptedException e) {System.out.println("BusDriver exceção.");}
        System.out.println("O busDriver terminou.");
        try
        { 
            porter.join();
        }
        catch (InterruptedException e) {System.out.println("Porter exceção.");}
        System.out.println("O porter terminou.");     
    }
    
}

