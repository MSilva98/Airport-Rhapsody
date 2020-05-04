package airportrhapsody.serverSide.ArrTransQuay;

import java.util.List;

import airportrhapsody.comInf.MessageArrTransQuay;
import airportrhapsody.comInf.MessageException;

public class ArrTransQuayInterface {
    /**
     *  Arrival Transfer Quay
     *  
     *  @serialField arrTransQuay
     */
    private ArrTransQuay arrTransQuay;

    /**
     *  Instantiate the arrival transfer quay interface
     *  @param arrTransQuay Arrival Transfer Quay
     */
    public ArrTransQuayInterface(ArrTransQuay arrTransQuay){
        this.arrTransQuay = arrTransQuay;
    }

    public MessageArrTransQuay processAndReply (MessageArrTransQuay inMessage) throws MessageException
    {
        MessageArrTransQuay outMessage = null;

        /* validate message */
        switch (inMessage.getType()){
            case MessageArrTransQuay.EBU:
            case MessageArrTransQuay.ETB:
                if(inMessage.getGenInt() == -1){
                    throw new MessageException("Passenger ID missing",inMessage);
                }
                break;

            case MessageArrTransQuay.PTB:
            case MessageArrTransQuay.TAB:
                if(inMessage.getGenInt() == -1){
                    throw new MessageException("Passenger ID missing",inMessage);
                }
                break;

            case MessageArrTransQuay.ABB:
            case MessageArrTransQuay.NP:
            case MessageArrTransQuay.GS:
            case MessageArrTransQuay.GDE:
            case MessageArrTransQuay.SDE:
            case MessageArrTransQuay.IE:
            
            default:    throw new MessageException("Invalid type", inMessage);
        }
        
        /* process message */
        switch (inMessage.getType()){
            case MessageArrTransQuay.EBU:
                arrTransQuay.enterBusUp();
                outMessage = new MessageArrTransQuay(MessageArrTransQuay.ACK);
                break;

            case MessageArrTransQuay.ETB:
                arrTransQuay.enterTheBus(inMessage.getGenInt());
                outMessage = new MessageArrTransQuay(MessageArrTransQuay.ACK);
                break;

            case MessageArrTransQuay.PTB:
                arrTransQuay.parkTheBus();
                outMessage = new MessageArrTransQuay(MessageArrTransQuay.ACK);
                break;

            case MessageArrTransQuay.TAB:
                arrTransQuay.takeABus(inMessage.getGenInt());
                outMessage = new MessageArrTransQuay(MessageArrTransQuay.ACK);
                break;

            case MessageArrTransQuay.ABB:
                arrTransQuay.announcingBusBoarding();
                outMessage = new MessageArrTransQuay(MessageArrTransQuay.ACK);
                break;

            case MessageArrTransQuay.NP:
                int x = arrTransQuay.numPassengers();
                outMessage = new MessageArrTransQuay(MessageArrTransQuay.NP,x);
                break;

            case MessageArrTransQuay.GS:
                List<Integer> lst = arrTransQuay.getSeats();
                outMessage = new MessageArrTransQuay(MessageArrTransQuay.GS,lst);
                break;    

            case MessageArrTransQuay.GDE:
                boolean end = arrTransQuay.getDayEnd();
                outMessage = new MessageArrTransQuay(MessageArrTransQuay.GDE,end);
                break;

            case MessageArrTransQuay.SDE:
                arrTransQuay.setDayEnd(inMessage.getGenBool());
                outMessage = new MessageArrTransQuay(MessageArrTransQuay.ACK);
                break;

            case MessageArrTransQuay.IE:
                boolean empty = arrTransQuay.isEmpty();
                outMessage = new MessageArrTransQuay(MessageArrTransQuay.IE,empty);
                break;    
        }

        return outMessage;
    }
}