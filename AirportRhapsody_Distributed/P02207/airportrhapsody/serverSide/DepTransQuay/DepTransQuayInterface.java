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

     /**
   *  Processing of messages by executing the corresponding task.
   *  Generation of a reply message.
   *
   *    @param inMessage message
   *
   *    @return response message
   *
   *    @throws MessageException invalid message
   */
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
            case MessageDepTransQuay.SHUT:
                break;
            default:    throw new MessageException("Invalid Type", inMessage);
        }

        /* process message */
        switch (inMessage.getType()){
          

            case MessageDepTransQuay.PTBALPO:
                BusDriver.InternalState state = depTransQuay.parkTheBusAndLetPassOff();
                outMessage = new MessageDepTransQuay(MessageDepTransQuay.PTBALPO,state);
                break;

            case MessageDepTransQuay.LB:
                int pID = inMessage.getPassengerID();
                Passenger.InternalState st = depTransQuay.leaveBus(pID);
                outMessage = new MessageDepTransQuay(MessageDepTransQuay.LB,st);
                break;
            case MessageDepTransQuay.SHUT:
                ServerDepTransQuay.waitConn = false;
                (((ClientProxyDepTransQuay) (Thread.currentThread ())).getScon ()).setTimeout (10);
                outMessage = new MessageDepTransQuay(MessageDepTransQuay.ACK);
                break;
        }

        return outMessage;
    }
}