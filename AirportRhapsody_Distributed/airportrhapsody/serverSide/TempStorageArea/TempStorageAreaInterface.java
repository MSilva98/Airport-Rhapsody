package airportrhapsody.serverSide.TempStorageArea;

import airportrhapsody.comInf.Luggage;
import airportrhapsody.comInf.MessageException;
import airportrhapsody.comInf.MessageTempStoreArea;

public class TempStorageAreaInterface {
    /**
     * Temporary Storage Area
     * @serialField tempStorageArea
     */
    private TempStorageArea tempStorageArea;

    /**
     * Instantiate the temporary storage area interface
     * @param tempStorageArea Temporary Storage Area
     */
    public TempStorageAreaInterface(TempStorageArea tempStorageArea){
        this.tempStorageArea = tempStorageArea;
    }

    public MessageTempStoreArea processAndReply (MessageTempStoreArea inMessage) throws MessageException
    {
        MessageTempStoreArea outMessage = null;

        /* validate message */
        switch(inMessage.getType()){
            case MessageTempStoreArea.IB:
                if(inMessage.getLugagge() == null){
                    throw new MessageException("Luggage missing", inMessage);
                }
                break;
            case MessageTempStoreArea.CB:
            default:    throw new MessageException("Invalid type", inMessage);
        }

        /* process message */
        switch(inMessage.getType()){
            case MessageTempStoreArea.IB:
                Luggage bag = inMessage.getLugagge();
                tempStorageArea.insertBag(bag);
                outMessage = new MessageTempStoreArea(MessageTempStoreArea.ACK);
                break;

            case MessageTempStoreArea.CB:
                Luggage l = tempStorageArea.collectBag();
                outMessage = new MessageTempStoreArea(MessageTempStoreArea.CB,l);
                break;

        }

        return outMessage;
    }
}