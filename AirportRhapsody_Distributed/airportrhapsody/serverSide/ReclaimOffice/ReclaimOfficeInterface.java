package airportrhapsody.serverSide.ReclaimOffice;

import airportrhapsody.clientSide.Passenger;
import airportrhapsody.comInf.MessageException;
import airportrhapsody.comInf.MessageReclaimOffice;

public class ReclaimOfficeInterface {
    /**
     * Reclaim Office
     * @serialField reclaimOffice
     */
    private ReclaimOffice reclaimOffice;

    /**
     * Instantiate Reclaim Office Interface
     * @param reclaimOffice Reclaim Office
     */
    public ReclaimOfficeInterface(ReclaimOffice reclaimOffice){
        this.reclaimOffice = reclaimOffice;
    }

    public MessageReclaimOffice processAndReply (MessageReclaimOffice inMessage) throws MessageException
    {
        MessageReclaimOffice outMessage = null;

        /* validate message */
        switch(inMessage.getType()){
            case MessageReclaimOffice.RMB:
                if(inMessage.getGenInt() == -1){
                    throw new MessageException("Num of bags missing", inMessage);
                }
                break;
            case MessageReclaimOffice.GNBM:
                break;
            case MessageReclaimOffice.GR:
                break;    
            case MessageReclaimOffice.SR:
                if(inMessage.getGenInt() == -1){
                    throw new MessageException("Num of reclaims missing", inMessage);
                }
                break;
            default:    throw new MessageException("Invalid type", inMessage);
        }

        /* process message */
        switch(inMessage.getType()){
            case MessageReclaimOffice.RMB:
                int bags = inMessage.getGenInt();
                Passenger.InternalState state = reclaimOffice.reportMissingBags(bags);
                outMessage = new MessageReclaimOffice(MessageReclaimOffice.RMB,state);
                break;

            case MessageReclaimOffice.GNBM:
                int mBags = reclaimOffice.getNumBagsMissing();
                outMessage = new MessageReclaimOffice(MessageReclaimOffice.GNBM,mBags);
                break;

            case MessageReclaimOffice.GR:
                int recs = reclaimOffice.getReclaims();
                outMessage = new MessageReclaimOffice(MessageReclaimOffice.GR,recs);
                break;

            case MessageReclaimOffice.SR:
                reclaimOffice.setReclaims(inMessage.getGenInt());
                outMessage = new MessageReclaimOffice(MessageReclaimOffice.ACK);
                break;
        }

        return outMessage;
    }
}