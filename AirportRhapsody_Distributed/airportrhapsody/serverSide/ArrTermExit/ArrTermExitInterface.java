package airportrhapsody.serverSide.ArrTermExit;

import airportrhapsody.clientSide.Passenger;
import airportrhapsody.comInf.MessageArrTermExit;
import airportrhapsody.comInf.MessageException;

public class ArrTermExitInterface {
    /**
     * Arrival Terminal Exit
     * 
     * @serialField arrTermExit
     */
    private ArrTermExit arrTermExit;

    /**
     *  Instantiate the arrival terminal exit interface
     *  @param arrTermExit Arrival Terminal Exit
     */
    public ArrTermExitInterface(ArrTermExit arrTermExit){
        this.arrTermExit = arrTermExit;
    }

    public MessageArrTermExit processAndReply (MessageArrTermExit inMessage) throws MessageException
    {
        MessageArrTermExit outMessage = null;

        /* validate message */
        switch (inMessage.getType()){
            case MessageArrTermExit.LAD:
                break;
            case MessageArrTermExit.AT:
                if(inMessage.getPassengerID() == -1){
                    throw new MessageException("Passenger ID missing", inMessage);
                }
                break;
            case MessageArrTermExit.EP:
                break;
            case MessageArrTermExit.GH:
                break;    
            default:    throw new MessageException("Invalid type", inMessage);
        }

        /* process message */

        switch (inMessage.getType()){
            case MessageArrTermExit.LAD:
                arrTermExit.leaveAirpDown();
                outMessage = new MessageArrTermExit(MessageArrTermExit.ACK);
                break;

            case MessageArrTermExit.AT:
                Passenger.InternalState state = arrTermExit.arrivedTerm(inMessage.getPassengerID());
                outMessage = new MessageArrTermExit(MessageArrTermExit.AT,state);
                break;

            case MessageArrTermExit.EP:
                boolean empty = arrTermExit.emptyTerm();
                outMessage = new MessageArrTermExit(MessageArrTermExit.EP,empty);
                break;

            case MessageArrTermExit.GH:
                arrTermExit.goHome();
                outMessage = new MessageArrTermExit(MessageArrTermExit.ACK);
                break;
        }

        return outMessage;
    }
}