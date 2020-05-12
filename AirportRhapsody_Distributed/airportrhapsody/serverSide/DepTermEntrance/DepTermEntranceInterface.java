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

        }
        return outMessage;
    }
}