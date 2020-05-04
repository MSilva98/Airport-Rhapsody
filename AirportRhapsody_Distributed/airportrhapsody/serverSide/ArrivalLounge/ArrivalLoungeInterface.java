package airportrhapsody.serverSide.ArrivalLounge;

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
     *  Instantiating the arrival lounge interface
     *  @param arrivalLounge Arrival Lounge
     */
    public ArrivalLoungeInterface(ArrivalLounge arrivalLounge){
        this.arrivalLounge = arrivalLounge;
    }

    public MessageArrivalLounge processAndReply (MessageArrivalLounge inMessage) throws MessageException
   {
        MessageArrivalLounge outMessage = null;                           // mensagem de resposta
        
         /* validação da mensagem recebida */
          /* seu processamento */

        return outMessage;
   }

}