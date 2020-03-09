package airportrhapsody;

public class AirportRhapsody {

    
    public static void main(String[] args) {
       
        int nPassengers = 6 ;                               // number of passengers
        Passenger[] passenger = new Passenger[nPassengers]; // Passenger threads array
        Porter porter;                                      // thread porter
        BusDriver busDriver;                                // thread bus driver
        int nPlaneLandings = 5;                             // number of plane landings
        int nSeatingPlaces = 3;                             // bus capacity
        int maxBags = 2;                                    // maximum luggage

        // TempStorageArea tempStorArea;
        // BaggageCollectionPoint bagCollPnt;
        //...

        //Problem config

        //Sharing region

        // LuggageHandler temporaryStorageArea = new LuggageHandler();
        // LuggageHandler baggageCollectionPoint = new LuggageHandler();
        // PassengersHandler baggageReclaimOffice = new PassengersHandler();

        // tempStorArea = new TempStorageArea();
        // bagCollPnt = new BaggageCollectionPoint();
        // ...

        //entities
        porter = new Porter();
        busDriver = new BusDriver();
        for (int i = 0; i < nPassengers; i++){
            passenger[i] = new Passenger(); //passar regioes partilhadas e argumentos necessarios
        }
        

        //Start Simullation

        for (int i = 0; i < nPassengers; i++){
            passenger[i].start ();
        }
        porter.start();
        busDriver.start();

        //End simulation

        
    }
    
}
