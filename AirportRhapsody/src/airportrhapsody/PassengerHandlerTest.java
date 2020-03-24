package airportrhapsody;

import airportrhapsody.sharedRegions.PassengersHandler;
import airportrhapsody.mainProgram.Passenger;
/**
 * PassengerHandlerTest
 */
public class PassengerHandlerTest {


    public static void main(String[] args) {
        System.out.println("Passengers handler test");
        PassengersHandler p = new PassengersHandler(6);    

        if(p.insertPassenger(new Passenger(1))){
            System.out.println("Passenger 1");
            System.out.println("Size: " + p.size());
        }

        if(p.insertPassenger(new Passenger(2))){
            System.out.println("Passenger 2");
            System.out.println("Size: " + p.size());
        }
        
        if(p.insertPassenger(new Passenger(3))){
            System.out.println("Passenger 3");
            System.out.println("Size: " + p.size());
        }
        
        if(p.insertPassenger(new Passenger(4))){
            System.out.println("Passenger 4");
            System.out.println("Size: " + p.size());
        }
        
        if(p.insertPassenger(new Passenger(5))){
            System.out.println("Passenger 5");
            System.out.println("Size: " + p.size());
        }
        
        if(p.insertPassenger(new Passenger(6))){
            System.out.println("Passenger 6");
            System.out.println("Size: " + p.size());
        }
        
        if(p.insertPassenger(new Passenger(7))){     // This isn't inserted because size is 6
            System.out.println("Passenger 7");
            System.out.println("Size: " + p.size());
        }
        

        System.out.println(p.removePassenger(3));
        System.out.println("Size: " + p.size());

        System.out.println(p.toString());

        // System.out.println(p.removePassenger(3));
        // System.out.println("Size: " + p.size());

        if(p.insertPassenger(new Passenger(7))){     // Inserted in the last position
            System.out.println("Passenger 7");
            System.out.println("Size: " + p.size());
        }

        System.out.println(p.toString());

        System.out.println();

        System.out.println(p.removePassenger());
        System.out.println("Size: " + p.size());

        System.out.println(p.removePassenger());
        System.out.println("Size: " + p.size());

        System.out.println(p.removePassenger());
        System.out.println("Size: " + p.size());

        System.out.println(p.removePassenger());
        System.out.println("Size: " + p.size());

        System.out.println(p.removePassenger());
        System.out.println("Size: " + p.size());

        System.out.println(p.removePassenger());
        System.out.println("Size: " + p.size());

        System.out.println(p.removePassenger());
        System.out.println("Size: " + p.size());
    }
}