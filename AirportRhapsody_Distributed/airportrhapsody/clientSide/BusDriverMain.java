package airportrhapsody.clientSide;

import airportrhapsody.LoggerStub;

public class BusDriverMain {
    public static void main(String[] args) {
        int nPassengers = 6 ;                               // number of passengers
        int nPlaneLandings = 5;                             // number of plane landings
        int nSeatingPlaces = 3;                             // bus capacity
        int maxBags = 2;                                    // maximum luggage

        String serverHostName = "localhost";

        // ArrTransQuayStub arrTransQuay;
        // DepTransQuayStub depTransQuay;
        // LoggerStub generalRepo;


        // generalRepo = new LoggerStub(serverHostName, 4008);
        // arrTransQuay = new ArrTransQuayStub(serverHostName, 4002);
        // depTransQuay = new DepTransQuayStub(serverHostName, 4005);


        ArrivalLoungeStub arrivalLounge;
        ArrTermExitStub arrTermExit;
        ArrTransQuayStub arrTransQuay;
        CollectionPointStub collPoint;
        DepTermEntranceStub depTermEntrance;
        DepTransQuayStub depTransQuay;
        LoggerStub generalRepo;
        ReclaimOfficeStub reclaimOffice;
        TempStorageAreaStub tempStorageArea;


        generalRepo = new LoggerStub(serverHostName, 4008);
        arrivalLounge = new ArrivalLoungeStub(serverHostName, 4000);
        collPoint = new CollectionPointStub(serverHostName, 4003);
        reclaimOffice = new ReclaimOfficeStub(serverHostName, 4006);
        arrTransQuay = new ArrTransQuayStub(serverHostName, 4002);
        depTransQuay = new DepTransQuayStub(serverHostName, 4005);
        arrTermExit = new ArrTermExitStub(serverHostName, 4001);
        depTermEntrance = new DepTermEntranceStub(serverHostName, 4004);
        tempStorageArea = new TempStorageAreaStub(serverHostName, 4007);

        BusDriver busDriver = new BusDriver(1,nSeatingPlaces,arrTransQuay,depTransQuay, arrivalLounge, generalRepo);

        busDriver.start ();

        try
        { 
            busDriver.join();
        }
        catch (InterruptedException e) {}
        
        System.out.println("O bus driver terminou.");
        // generalRepo.setMissing(reclaimOffice.getNumBagsMissing());

        // boolean end = false;
        // end = arrivalLounge.shutdown();
        // while(!end);

        // end = false;
        // end = collPoint.shutdown();
        // while(!end);

        // end = false;
        // end = arrTransQuay.shutdown();
        // while(!end);
        
        // end = false;
        // end = depTransQuay.shutdown();
        // while(!end);
        
        // end = false;
        // end = arrTermExit.shutdown();
        // while(!end);
        
        // end = false;
        // end = reclaimOffice.shutdown();
        // while(!end);
        
        // end = false;
        // end = depTermEntrance.shutdown();
        // while(!end);

        // end = false;
        // end = tempStorageArea.shutdown();
        // while(!end);
        
        // generalRepo.write(true); 
        // end = generalRepo.shutdown();


    }
}