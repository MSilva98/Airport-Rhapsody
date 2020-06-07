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
            case MessageArrivalLounge.GPE:
                break;
            case MessageArrivalLounge.SPE:
                break;
            case MessageArrivalLounge.GPAE:
                break;
            case MessageArrivalLounge.SPAE:
                break;
            case MessageArrivalLounge.S:
                break;
            case MessageArrivalLounge.SHUT:
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

            case MessageArrivalLounge.GDE:
                boolean st = arrivalLounge.getDayEnd();
                outMessage = new MessageArrivalLounge (MessageArrivalLounge.GDE, st);  
                break;
            case MessageArrivalLounge.SDE:
                arrivalLounge.setDayEnd(inMessage.getGenBool());
                outMessage = new MessageArrivalLounge (MessageArrivalLounge.ACK); 
                break;

            case MessageArrivalLounge.GPE:
                boolean s = arrivalLounge.getPrtEnd();
                outMessage = new MessageArrivalLounge (MessageArrivalLounge.GPE, s);  
                break;
            case MessageArrivalLounge.SPE:
                arrivalLounge.setPrtEnd(inMessage.getGenBool());
                outMessage = new MessageArrivalLounge (MessageArrivalLounge.ACK); 
                break;

            case MessageArrivalLounge.GPAE:
                boolean t = arrivalLounge.getPassEnd();
                outMessage = new MessageArrivalLounge (MessageArrivalLounge.GPAE, t);  
                break;
            case MessageArrivalLounge.SPAE:
                arrivalLounge.setPassEnd(inMessage.getGenBool());
                outMessage = new MessageArrivalLounge (MessageArrivalLounge.ACK); 
                break;

            case MessageArrivalLounge.S: 
                int size = arrivalLounge.size();
                outMessage = new MessageArrivalLounge(MessageArrivalLounge.S, size);
                break; 
                
            case MessageArrivalLounge.SHUT:
                ServerArrivalLounge.waitConn = false;
                (((ClientProxyArrivalLounge) (Thread.currentThread ())).getScon ()).setTimeout (10);
                outMessage = new MessageArrivalLounge(MessageArrivalLounge.ACK);
                System.out.println("SHUT COMPLETE");
                break;
        }

        return outMessage;
   }

}