package airportrhapsody.mainProgram;

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

        //Problem config

        //Sharing region

        arrivalLounge = new ArrivalLounge(nPassengers*maxBags);
        collPoint = new CollectionPoint(nPassengers*maxBags);
        reclaimOffice = new ReclaimOffice();
        arrTransQuay = new ArrTransQuay(nPassengers);
        depTransQuay = new DepTransQuay(nPassengers);
        arrTermExit = new ArrTermExit(nPassengers);
        depTermEntrance = new DepTermEntrance(nPassengers);
        tempStorageArea = new TempStorageArea(nPassengers);

        //entities
        porter = new Porter(1, arrivalLounge, tempStorageArea, collPoint);
        busDriver = new BusDriver(1,nSeatingPlaces,arrTransQuay,depTransQuay);
        for (int i = 0; i < nPassengers; i++){
            passenger[i] = new Passenger(i, arrivalLounge, collPoint, reclaimOffice, arrTransQuay, depTransQuay, arrTermExit, depTermEntrance);
        }
        

        //Start Simullation
        porter.start();
        busDriver.start();
        for (int i = 0; i < nPassengers; i++){
            passenger[i].start ();
        }
        

        //End simulation

        
    }
    
}
