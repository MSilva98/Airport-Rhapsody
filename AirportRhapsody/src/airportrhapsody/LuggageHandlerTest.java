package airportrhapsody;

import airportrhapsody.sharedRegions.Luggage;
import airportrhapsody.sharedRegions.LuggageHandler;

/**
 * LuggageHandlerTest
 */
public class LuggageHandlerTest {

    public static void main(String[] args) {
        System.out.println("Luggage Handler Test");
        LuggageHandler l = new LuggageHandler(5);

        if(l.addLuggage(new Luggage(1))){
            System.out.println("Luggage 1");
            System.out.println("Size: " + l.size());
        }
        
        if(l.addLuggage(new Luggage(2))){
            System.out.println("Luggage 2");
            System.out.println("Size: " + l.size());
        }

        if(l.addLuggage(new Luggage(3))){
            System.out.println("Luggage 3");
            System.out.println("Size: " + l.size());
        }

        if(l.addLuggage(new Luggage(4))){
            System.out.println("Luggage 4");
            System.out.println("Size: " + l.size());
        }

        if(l.addLuggage(new Luggage(3))){
            System.out.println("Luggage 5");
            System.out.println("Size: " + l.size());
        }

        System.out.println(l.toString());

        // System.out.println("Remove one bag");
        // System.out.println(l.remLuggage().toString());
        // System.out.println("Bag removed");
        // System.out.println(l.size());

        // if(l.addLuggage(new Luggage(1))){
        //     System.out.println("Luggage 1");
        //     System.out.println("Size: " + l.size());
        // }
        // System.out.println(l.toString());
        

        System.out.println("Remove one bag");
        System.out.println(l.remLuggage(3).toString());
        System.out.println("Bag removed");
        System.out.println(l.size());
        System.out.println();

        System.out.println(l.toString());
        System.out.println();


        System.out.println("Remove one bag");
        System.out.println(l.remLuggage(3).toString());
        System.out.println("Bag removed");
        System.out.println(l.size());
        System.out.println();

        System.out.println(l.toString());
        System.out.println();


        System.out.println("Remove one bag");
        System.out.println(l.remLuggage(2).toString());
        System.out.println("Bag removed");
        System.out.println(l.size());
        System.out.println();

        System.out.println(l.toString());
        System.out.println();


        // System.out.println("Remove one bag");
        // System.out.println(l.remLuggage().toString());
        // System.out.println("Bag removed");
        // System.out.println(l.size());

        // System.out.println("Remove one bag");
        // System.out.println(l.remLuggage().toString());
        // System.out.println("Bag removed");
        // System.out.println(l.size());

        // System.out.println("Remove one bag");
        // System.out.println(l.remLuggage().toString());
        // System.out.println("Bag removed");
        // System.out.println(l.size());
        
        // System.out.println("Remove one bag");
        // System.out.println(l.remLuggage().toString());
        // System.out.println("Bag removed");
        // System.out.println(l.size());

        // System.out.println(l.isEmpty());
        // System.out.println("Remove one bag");
        // System.out.println(l.remLuggage());
        // System.out.println("Bag removed");
        // System.out.println(l.size());

    }
}