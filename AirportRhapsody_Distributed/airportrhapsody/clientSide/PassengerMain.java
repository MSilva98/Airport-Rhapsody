package airportrhapsody.clientSide;

import airportrhapsody.LoggerStub;

public class PassengerMain {

    public static void main(String[] args) {
        
        int nPassengers = 6 ;                               // number of passengers
        int nPlaneLandings = 5;                             // number of plane landings
        int nSeatingPlaces = 3;                             // bus capacity
        int maxBags = 2;                                    // maximum luggage

        String serverHostName = "localhost";

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


        Passenger passenger = new Passenger(Integer.parseInt(args[0]), arrivalLounge, collPoint, reclaimOffice, arrTransQuay, depTransQuay, arrTermExit, depTermEntrance, generalRepo);

        passenger.start ();

        try
        { 
            passenger.join();
        }
        catch (InterruptedException e) {}
        System.out.println("O passenger terminou.");
    }
}