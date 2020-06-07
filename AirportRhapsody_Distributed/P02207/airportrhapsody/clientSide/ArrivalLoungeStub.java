package airportrhapsody.clientSide;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

    public boolean getPrtEnd(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrivalLounge.GPE);
        con.writeObject(outMessage);
        inMessage = (MessageArrivalLounge) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrivalLounge.GPE ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return false;
        }else{
            return inMessage.getGenBool();
        }
    }

    public void setPrtEnd(boolean st){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrivalLounge.SPE,st);
        con.writeObject(outMessage);
        inMessage = (MessageArrivalLounge) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrivalLounge.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    public boolean getPassEnd(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrivalLounge.GPAE);
        con.writeObject(outMessage);
        inMessage = (MessageArrivalLounge) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrivalLounge.GPAE ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return false;
        }else{
            return inMessage.getGenBool();
        }
    }

    public void setPassEnd(boolean st){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrivalLounge.SPAE,st);
        con.writeObject(outMessage);
        inMessage = (MessageArrivalLounge) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrivalLounge.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }


    public int size(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrivalLounge.S);
        con.writeObject(outMessage);
        inMessage = (MessageArrivalLounge) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrivalLounge.S ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return -1;
        }else{
            return inMessage.getSize();
        }
    }

    public void shutdown ()
    {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        // while (!con.open ()){}
        System.out.println("BEFORE CON");
        while (!con.open ()){
            System.out.println("WHILE CON");
            try
        { Thread.currentThread ().sleep ((long) (10));
        }
        catch (InterruptedException e) {}
        }
        System.out.println("AFTER CON");
        outMessage = new MessageArrivalLounge(MessageArrivalLounge.SHUT);
        con.writeObject (outMessage);
        System.out.println("SHUT SENT");
        inMessage = (MessageArrivalLounge) con.readObject ();
        con.close ();
        System.out.println("ACK RECEIVED");
        if (inMessage.getType () != MessageArrivalLounge.ACK){ 
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            while(true){}
            // System.exit (1);
        }
    }
}