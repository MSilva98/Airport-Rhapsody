package airportrhapsody.serverSide.DepTransQuay;

import airportrhapsody.clientSide.BusDriver;
import airportrhapsody.clientSide.Passenger;
import airportrhapsody.comInf.MessageDepTransQuay;
import airportrhapsody.comInf.MessageException;

public class DepTransQuayInterface {
    /**
     * Departure Tranfer Quay
     * 
     * @serialField depTransQuay
     */
    private DepTransQuay depTransQuay;

    /**
     *  Instantiate the departure transfer quay interface
     *  @param depTransQuay Departure Transfer Quay
     */
    public DepTransQuayInterface(DepTransQuay depTransQuay){
        this.depTransQuay = depTransQuay;
    }

    public MessageDepTransQuay processAndReply (MessageDepTransQuay inMessage) throws MessageException
    {
        MessageDepTransQuay outMessage = null;

        /* validate message */
        switch (inMessage.getType()){
            case MessageDepTransQuay.LDTQ:
                if(inMessage.getPassengerID() == -1){
                    throw new MessageException("Passenger ID missing", inMessage);
                }
                break;
            
            case MessageDepTransQuay.PTBALPO:
                break;    
            case MessageDepTransQuay.LB:
                if(inMessage.getPassengerID() == -1){
                    throw new MessageException("Passenger ID missing", inMessage);
                }
                break;
            
            default:    throw new MessageException("Invalid Type", inMessage);
        }

        /* process message */
        switch (inMessage.getType()){
            // case MessageDepTransQuay.LDTQ:
            //     int passengerID = inMessage.getPassengerID();
            //     Passenger p = depTransQuay.leaveDepTransQuay(passengerID);
            //     outMessage = new MessageDepTransQuay(MessageDepTransQuay.LDTQ,p);
            //     break;

            case MessageDepTransQuay.PTBALPO:
                System.out.println("PTBALPO INTER - 0");
                BusDriver.InternalState state = depTransQuay.parkTheBusAndLetPassOff();
                System.out.println("PTBALPO INTER - 1");
                outMessage = new MessageDepTransQuay(MessageDepTransQuay.PTBALPO,state);
                break;

            case MessageDepTransQuay.LB:
                System.out.println("LB INTER - 0");
                int pID = inMessage.getPassengerID();
                System.out.println("LB INTER - 1");
                Passenger.InternalState st = depTransQuay.leaveBus(pID);
                System.out.println("LB INTER - 2");
                outMessage = new MessageDepTransQuay(MessageDepTransQuay.LB,st);
                break;
        }

        return outMessage;
    }
}