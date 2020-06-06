package airportrhapsody.serverSide.DepTermEntrance;

import airportrhapsody.clientSide.Passenger;
import airportrhapsody.comInf.MessageDepTermEntrance;
import airportrhapsody.comInf.MessageException;

public class DepTermEntranceInterface {
    /**
     * Departure Terminal Entrance
     * 
     * @serialField depTermEnt
     */
    private DepTermEntrance depTermEnt;

    /**
     * Instantiate the departure terminal entrance interface
     * @param depTermEnt Departure Terminal Entrance 
     */
    public DepTermEntranceInterface(DepTermEntrance depTermEnt){
        this.depTermEnt = depTermEnt;
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
    public MessageDepTermEntrance processAndReply (MessageDepTermEntrance inMessage) throws MessageException
    {
        MessageDepTermEntrance outMessage = null;

        /* validate message */
        switch(inMessage.getType()){
            case MessageDepTermEntrance.AT:
                if(inMessage.getPassengerID() == -1){
                    throw new MessageException("Passenger ID missing", inMessage);
                }
                break;

            case MessageDepTermEntrance.PNL:
                break;

            case MessageDepTermEntrance.SHUT:
                break;
            default:    throw new MessageException("Invalid type", inMessage);
        }

        /* process message */
        switch(inMessage.getType()){
            case MessageDepTermEntrance.AT:
                int passengerID = inMessage.getPassengerID();
                Passenger.InternalState state = depTermEnt.arrivedTerm(passengerID);
                outMessage = new MessageDepTermEntrance(MessageDepTermEntrance.AT,state);
                break;

            case MessageDepTermEntrance.PNL:
                depTermEnt.prepareNextLeg();
                outMessage = new MessageDepTermEntrance(MessageDepTermEntrance.ACK);
                break;

            case MessageDepTermEntrance.SHUT:
                ServerDepTermEntrance.waitConn = false;
                System.out.println("DEP TERM SHUT MESSAGE");
                (((ClientProxyDepTermEntrance) (Thread.currentThread ())).getScon ()).setTimeout (10);
                System.out.println("DEPT TERM SHUT MESSAGE 1");
                outMessage = new MessageDepTermEntrance(MessageDepTermEntrance.ACK);
                System.out.println("SHUT COMPLETE");
                break;

        }
        return outMessage;
    }
}