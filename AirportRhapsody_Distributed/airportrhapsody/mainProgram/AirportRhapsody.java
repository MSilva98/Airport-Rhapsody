// package airportrhapsody.mainProgram;

// import airportrhapsody.serverSide.Logger.*;
// import airportrhapsody.serverSide.ArrTermExit.*;
// import airportrhapsody.serverSide.ArrTransQuay.*;
// import airportrhapsody.serverSide.ArrivalLounge.*;
// import airportrhapsody.serverSide.CollectionPoint.*;
// import airportrhapsody.serverSide.DepTermEntrance.*;
// import airportrhapsody.serverSide.DepTransQuay.*;
// import airportrhapsody.serverSide.ReclaimOffice.*;
// import airportrhapsody.serverSide.TempStorageArea.*;

// import airportrhapsody.clientSide.*;

// public class AirportRhapsody {

//     public static void main(String[] args) {

//         int nPassengers = 6 ;                               // number of passengers
//         Passenger[] passenger = new Passenger[nPassengers]; // Passenger threads array
//         Porter porter;                                      // thread porter
//         BusDriver busDriver;                                // thread bus driver
//         int nPlaneLandings = 5;                             // number of plane landings
//         int nSeatingPlaces = 3;                             // bus capacity
//         int maxBags = 2;                                    // maximum luggage
//         ArrivalLounge arrivalLounge;
//         CollectionPoint collPoint;
//         ReclaimOffice reclaimOffice;
//         ArrTransQuay arrTransQuay;
//         DepTransQuay depTransQuay;
//         ArrTermExit arrTermExit;
//         DepTermEntrance depTermEntrance;
//         TempStorageArea tempStorageArea;
//         Logger generalRepo;

//         //Problem config

//         //Sharing region

//         generalRepo = new Logger(nSeatingPlaces, nPassengers, "log.txt");
//         arrivalLounge = new ArrivalLounge(nPassengers*maxBags, nPassengers/*, generalRepo*/);
//         collPoint = new CollectionPoint(nPassengers*maxBags, nPassengers, generalRepo);
//         reclaimOffice = new ReclaimOffice(generalRepo);
//         arrTransQuay = new ArrTransQuay(nPassengers, nSeatingPlaces, generalRepo);
//         depTransQuay = new DepTransQuay(nPassengers, arrTransQuay, generalRepo);
//         arrTermExit = new ArrTermExit(nPassengers, arrivalLounge, arrTransQuay, nPlaneLandings, generalRepo);
//         depTermEntrance = new DepTermEntrance(nPassengers, arrTermExit, generalRepo);
//         tempStorageArea = new TempStorageArea(nPassengers*maxBags);

//         //entities
//         porter = new Porter(1, arrivalLounge, tempStorageArea, collPoint, generalRepo);
//         busDriver = new BusDriver(1,nSeatingPlaces,arrTransQuay,depTransQuay, generalRepo);
//         // generalRepo.write(false);

//         //Start Simullation
//         porter.start();
//         busDriver.start();

//         for (int j = 0; j < nPlaneLandings; j++) {
//             generalRepo.setFn(0);
//             generalRepo.setBn(0);
//             collPoint.noBags();

//             for (int i = 0; i < nPassengers; i++){
//                 passenger[i] = new Passenger(i, arrivalLounge, collPoint, reclaimOffice, arrTransQuay, depTransQuay, arrTermExit, depTermEntrance, generalRepo);
//             }
//             for (int i = 0; i < nPassengers; i++){
//                 passenger[i].start ();
//             }
            
//             //End simulation
    
//             for (int i = 0; i < nPassengers; i++){
//                 try
//                 { 
//                     passenger[i].join();
//                 }
//                 catch (InterruptedException e) {System.out.println("O passenger "+  i + " exceção.");}
//                 // System.out.println("O passenger "+  i + " terminou.");
//             }

//             generalRepo.clearVars();
//         }
//         try
//         { 
//             busDriver.join();
//         }
//         catch (InterruptedException e) {System.out.println("BusDriver exceção.");}
//         // System.out.println("O busDriver terminou.");
//         try
//         { 
//             porter.join();
//         }
//         catch (InterruptedException e) {System.out.println("Porter exceção.");}
//         System.out.println("O porter terminou.");     

//         generalRepo.setMissing(reclaimOffice.getNumBagsMissing());
//         generalRepo.write(true);   
//     }
    
// }

