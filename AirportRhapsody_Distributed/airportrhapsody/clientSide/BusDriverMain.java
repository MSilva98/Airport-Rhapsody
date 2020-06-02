package airportrhapsody.clientSide;

import airportrhapsody.LoggerStub;

public class BusDriverMain {
    public static void main(String[] args) {
        int nPassengers = 6 ;                               // number of passengers
        int nPlaneLandings = 5;                             // number of plane landings
        int nSeatingPlaces = 3;                             // bus capacity
        int maxBags = 2;                                    // maximum luggage

        String serverHostName = "localhost";

        ArrTransQuayStub arrTransQuay;
        DepTransQuayStub depTransQuay;
        LoggerStub generalRepo;


        generalRepo = new LoggerStub(serverHostName, 4008);
        arrTransQuay = new ArrTransQuayStub(serverHostName, 4002);
        depTransQuay = new DepTransQuayStub(serverHostName, 4005);


        BusDriver busDriver = new BusDriver(1,nSeatingPlaces,arrTransQuay,depTransQuay, generalRepo);

        busDriver.start ();

        try
        { 
            busDriver.join();
        }
        catch (InterruptedException e) {}
        arrTransQuay.shutdown();
        depTransQuay.shutdown();
        generalRepo.write(true); 
        generalRepo.shutdown();
        System.out.println("O bus driver terminou.");
    }
}