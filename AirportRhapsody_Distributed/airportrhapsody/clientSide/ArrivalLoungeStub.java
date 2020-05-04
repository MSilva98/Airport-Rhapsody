package airportrhapsody.clientSide;

import airportrhapsody.comInf.Luggage;
import airportrhapsody.comInf.MessageArrivalLounge;

/**
 * ArrivalLoungeStub
 */
public class ArrivalLoungeStub {

     /**
   *  Server host name
   *    @serialField serverHostName
   */

   private String serverHostName = null;

   /**
    *  Server port number
    *    @serialField serverPortNumb
    */
 
    private int serverPortNumb;
    
    /**
     * Instantiating the arrival lounge stub .
     */
    public ArrivalLoungeStub(String serverHostName, int serverPortNumb){
        this.serverHostName = serverHostName;
        this.serverPortNumb = serverPortNumb;
    }

    /**
     * Put a bag on the arrival lounge
     * @param l bag
     */
    public void putBag(Luggage l){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrivalLounge.PB, l);
        con.writeObject(outMessage);
        inMessage = (MessageArrivalLounge) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrivalLounge.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    /**
     * Try to collect a bag from the arrival lounge
     * @param p porter
     * @return bag
     */
    public Luggage tryToCollectABag(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrivalLounge.TTCAB);
        con.writeObject(outMessage);
        inMessage = (MessageArrivalLounge) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrivalLounge.TTCAB ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return null;
        }else{
            return inMessage.getLuggage();
        }
    }

    /**
     * block porter
     */
    public void restPorter(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrivalLounge.RP);
        con.writeObject(outMessage);
        inMessage = (MessageArrivalLounge) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrivalLounge.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }
    /**
     * wake up porter
     */
    public void wakePorter(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrivalLounge.WP);
        con.writeObject(outMessage);
        inMessage = (MessageArrivalLounge) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrivalLounge.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    /**
     * Take a rest
     * @param p porter
     * @return <li> true, if day is over
     *         <li> false, if isn't
     */
    public boolean takeARest() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrivalLounge.TAR);
        con.writeObject(outMessage);
        inMessage = (MessageArrivalLounge) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrivalLounge.TAR ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return false;
        }else{
            return inMessage.getGenBool();
        }
    }

    /**
     * What should i do
     * @param p passenger
     * @return What passagenger should do according to his situation
     */
    public boolean whatShouldIDo(Passenger.Situation s) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrivalLounge.WSID, s);
        con.writeObject(outMessage);
        inMessage = (MessageArrivalLounge) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrivalLounge.WSID ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return false;
        }else{
            return inMessage.getGenBool();
        }
    }

    public boolean getDayEnd(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrivalLounge.GDE);
        con.writeObject(outMessage);
        inMessage = (MessageArrivalLounge) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrivalLounge.GDE ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return false;
        }else{
            return inMessage.getGenBool();
        }
    }

    public void setDayEnd(boolean st){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrivalLounge.SDE,st);
        con.writeObject(outMessage);
        inMessage = (MessageArrivalLounge) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrivalLounge.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }
}