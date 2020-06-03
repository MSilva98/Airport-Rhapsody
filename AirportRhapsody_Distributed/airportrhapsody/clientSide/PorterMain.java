package airportrhapsody.clientSide;

import airportrhapsody.LoggerStub;

public class PorterMain {
    public static void main(String[] args) {
        int nPassengers = 6 ;                               // number of passengers
        int nPlaneLandings = 5;                             // number of plane landings
        int nSeatingPlaces = 3;                             // bus capacity
        int maxBags = 2;                                    // maximum luggage

        String serverHostName = "localhost";

        ArrivalLoungeStub arrivalLounge;
        CollectionPointStub collPoint;
        TempStorageAreaStub tempStorageArea;
        LoggerStub generalRepo;


        generalRepo = new LoggerStub(serverHostName, 4008);
        arrivalLounge = new ArrivalLoungeStub(serverHostName, 4000);
        collPoint = new CollectionPointStub(serverHostName, 4003);
        tempStorageArea = new TempStorageAreaStub(serverHostName, 4007);

        Porter porter = new Porter(1, arrivalLounge, tempStorageArea, collPoint, generalRepo);
        porter.start ();

        try
        { 
            porter.join();
        }
        catch (InterruptedException e) {}
        
        System.out.println("O porter terminou.");
    }
}