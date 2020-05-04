package airportrhapsody.serverSide.CollectionPoint;

import airportrhapsody.clientSide.Porter;
import airportrhapsody.comInf.MessageCollectionPoint;
import airportrhapsody.comInf.MessageException;

public class CollectionPointInterface {
    /**
     *  Collection Point
     *  
     *  @serialField collPoint
     */
    private CollectionPoint collPoint;

    /**
     * Instantiate the Collection Point interface
     * @param collPoint Collection Point
     */
    public CollectionPointInterface(CollectionPoint collPoint){
        this.collPoint = collPoint;
    }

    public MessageCollectionPoint processAndReply (MessageCollectionPoint inMessage) throws MessageException
    {
        MessageCollectionPoint outMessage = null;

        /* validate message */
        switch (inMessage.getType()){
            case MessageCollectionPoint.GCAB:
                if(inMessage.getPassengerID() == -1){
                    throw new MessageException("Passenger ID missing", inMessage);
                }
                if(inMessage.getNR() == -1){
                    throw new MessageException("NR missing", inMessage);
                }
                break;

            case MessageCollectionPoint.IB:
                if(inMessage.getBag() == null){
                    throw new MessageException("Bag missing", inMessage);
                }
                break;

            case MessageCollectionPoint.NMBTC:
            case MessageCollectionPoint.LCP:
                if(inMessage.getPassengerID() == -1){
                    throw new MessageException("Passenger ID missing", inMessage);
                }
                break;

            case MessageCollectionPoint.NB:
            case MessageCollectionPoint.GNB:
            case MessageCollectionPoint.S:
            default:    throw new MessageException("Invalid type", inMessage);
        }

        /* process message */
        switch (inMessage.getType()){
            case MessageCollectionPoint.GCAB:
                int pID = inMessage.getPassengerID();
                int nr = inMessage.getNR();
                boolean bag = collPoint.goCollectABag(pID,nr);
                outMessage = new MessageCollectionPoint(MessageCollectionPoint.GCAB,bag);
                break;

            case MessageCollectionPoint.IB:
                collPoint.insertBag(inMessage.getBag());
                outMessage = new MessageCollectionPoint(MessageCollectionPoint.ACK);
                break;    

            case MessageCollectionPoint.NMBTC:
                Porter.InternalState state = collPoint.noMoreBagsToCollect();
                outMessage = new MessageCollectionPoint(MessageCollectionPoint.NMBTC,state);
                break;

            case MessageCollectionPoint.LCP:
                int passID = inMessage.getPassengerID();
                collPoint.leaveCollPoint(passID);
                outMessage = new MessageCollectionPoint(MessageCollectionPoint.ACK);
                break;

            case MessageCollectionPoint.NB:
                collPoint.noBags();
                outMessage = new MessageCollectionPoint(MessageCollectionPoint.ACK);
                break;

            case MessageCollectionPoint.GNB:
                boolean bags = collPoint.getNoBags();
                outMessage = new MessageCollectionPoint(MessageCollectionPoint.GNB,bags);
                break;

            case MessageCollectionPoint.S:
                int size = collPoint.size();
                outMessage = new MessageCollectionPoint(MessageCollectionPoint.S,size);
                break;
        }
        return outMessage;
    }
}