package airportrhapsody.serverSide.ArrivalLounge;

import airportrhapsody.comInf.Luggage;
import airportrhapsody.comInf.MessageArrivalLounge;
import airportrhapsody.comInf.MessageException;

public class ArrivalLoungeInterface {
    /**
     * Arrival lounge
     * 
     * @serialField arrivalLounge
     */
    private ArrivalLounge arrivalLounge;

    /**
     *  Instantiate the arrival lounge interface
     *  @param arrivalLounge Arrival Lounge
     */
    public ArrivalLoungeInterface(ArrivalLounge arrivalLounge){
        this.arrivalLounge = arrivalLounge;
    }

    public MessageArrivalLounge processAndReply (MessageArrivalLounge inMessage) throws MessageException
    {
        MessageArrivalLounge outMessage = null;                           // mensagem de resposta
        
         /* validate message */

        switch (inMessage.getType ()){ 
            case MessageArrivalLounge.PB:  
                if ((inMessage.getLuggage() == null) ){
                    throw new MessageException ("Luggage missing!", inMessage);
                }
                break;
            
            case MessageArrivalLounge.TTCAB:
                break;
            case MessageArrivalLounge.RP:
                break;
            case MessageArrivalLounge.WP:
                break;
            case MessageArrivalLounge.TAR:
                break;
            case MessageArrivalLounge.WSID:
                if(inMessage.getSituation() == null){
                    throw new MessageException("Passenger situation missing!", inMessage);
                }
            case MessageArrivalLounge.GDE:
                break;
            case MessageArrivalLounge.SDE:
                break;
            case MessageArrivalLounge.S:
                break;
            default:    throw new MessageException ("Invalid type", inMessage);
        }

        /* process message */

        switch (inMessage.getType ()){ 
            case MessageArrivalLounge.PB:
                arrivalLounge.putBag(inMessage.getLuggage());
                outMessage = new MessageArrivalLounge (MessageArrivalLounge.ACK);       // gerar resposta
                break;

            case MessageArrivalLounge.TTCAB:
                Luggage l = arrivalLounge.tryToCollectABag();
                outMessage = new MessageArrivalLounge(MessageArrivalLounge.TTCAB, l);
                break;
            
            case MessageArrivalLounge.RP:
                arrivalLounge.restPorter();
                outMessage = new MessageArrivalLounge (MessageArrivalLounge.ACK);       // gerar resposta
                break;

            case MessageArrivalLounge.WP:
                arrivalLounge.wakePorter();
                outMessage = new MessageArrivalLounge (MessageArrivalLounge.ACK);       // gerar resposta
                break;

            case MessageArrivalLounge.TAR:
                boolean b = arrivalLounge.takeARest();
                outMessage = new MessageArrivalLounge (MessageArrivalLounge.TAR, b);       // gerar resposta
                break;

            case MessageArrivalLounge.WSID:
                boolean whatShouldIDo = arrivalLounge.whatShouldIDo(inMessage.getSituation());
                outMessage = new MessageArrivalLounge (MessageArrivalLounge.WSID, whatShouldIDo);       // gerar resposta
                break;

            case MessageArrivalLounge.S: 
                int size = arrivalLounge.size();
                outMessage = new MessageArrivalLounge(MessageArrivalLounge.S, size);
                break;   

        }

        return outMessage;
   }

}