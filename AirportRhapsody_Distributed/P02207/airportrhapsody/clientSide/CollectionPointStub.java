package airportrhapsody.clientSide;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import airportrhapsody.comInf.Luggage;
import airportrhapsody.comInf.MessageCollectionPoint;

/**
 * Baggage Collection Point
 */
public class CollectionPointStub  {
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
     * Instantiating the Baggage Collection Point stub.
     */
    public CollectionPointStub(String serverHostName, int serverPortNumb){
        this.serverHostName = serverHostName;
        this.serverPortNumb = serverPortNumb;
    }

    /**
     * Go collect a bag
     * @param p passenger
     * @return <li> true if collect a bag
     *         <li> false if don't collect a bag
     */
    public boolean goCollectABag(int passengerID, int nr){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageCollectionPoint inMessage, outMessage;
        while (!con.open ()) {                                    // aguarda ligação
            // { try
            // { //sleep((long) (10));
            // }
            // catch (InterruptedException e) {}
        }
        outMessage = new MessageCollectionPoint(MessageCollectionPoint.GCAB, passengerID, nr);
        con.writeObject(outMessage);
        inMessage = (MessageCollectionPoint) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageCollectionPoint.GCAB ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return false;
        }else{
            return inMessage.getB();
        }
        
        
    }    
    /**
     * Insert a bag in collection point
     * @param l bag
     */
    public void insertBag(Luggage l){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageCollectionPoint inMessage, outMessage;
        while (!con.open ()) {                                    // aguarda ligação
            // try
            // { //sleep((long) (10));
            // }
            // catch (InterruptedException e) {}
        }
        outMessage = new MessageCollectionPoint(MessageCollectionPoint.IB, l);
        con.writeObject(outMessage);
        inMessage = (MessageCollectionPoint) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageCollectionPoint.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }

    }

    /**
     * No more bags to collect
     * @param p porter
     */
    public Porter.InternalState noMoreBagsToCollect() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageCollectionPoint inMessage, outMessage;
        while (!con.open ()) {                                    // aguarda ligação
            // try
            // { //sleep((long) (10));
            // }
            // catch (InterruptedException e) {}
        }
        outMessage = new MessageCollectionPoint(MessageCollectionPoint.NMBTC);
        con.writeObject(outMessage);
        inMessage = (MessageCollectionPoint) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageCollectionPoint.NMBTC ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return null;
        }else{
            return inMessage.getSt();
        }
    }

    public void leaveCollPoint(int id){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageCollectionPoint inMessage, outMessage;
        while (!con.open ()) {                                    // aguarda ligação
            // try
            // { //sleep((long) (10));
            // }
            // catch (InterruptedException e) {}
        }
        outMessage = new MessageCollectionPoint(MessageCollectionPoint.LCP,id);
        con.writeObject(outMessage);
        inMessage = (MessageCollectionPoint) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageCollectionPoint.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    public void noBags(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageCollectionPoint inMessage, outMessage;
        while (!con.open ()) {                                    // aguarda ligação
            // try
            // { //sleep((long) (10));
            // }
            // catch (InterruptedException e) {}
        }
        outMessage = new MessageCollectionPoint(MessageCollectionPoint.NB);
        con.writeObject(outMessage);
        inMessage = (MessageCollectionPoint) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageCollectionPoint.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    public boolean getNoBags(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageCollectionPoint inMessage, outMessage;
        while (!con.open ()) {                                    // aguarda ligação
            // { try
            // { //sleep((long) (10));
            // }
            // catch (IpassengerIDct(outMessage);
        }
        outMessage = new MessageCollectionPoint(MessageCollectionPoint.GNB);
        con.writeObject(outMessage);
        inMessage = (MessageCollectionPoint) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageCollectionPoint.GNB ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return false;
        }else{
            return inMessage.getB();
        }
    }
    public int size(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageCollectionPoint inMessage, outMessage;
        while (!con.open ()) {                                    // aguarda ligação
            // { try
            // { //sleep((long) (10));
            // }
            // catch (InterruptedException e) {}
        }
        outMessage = new MessageCollectionPoint(MessageCollectionPoint.S);
        con.writeObject(outMessage);
        inMessage = (MessageCollectionPoint) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageCollectionPoint.S ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return -1;
        }else{
            return inMessage.getNR();
        }
    }

    public void shutdown ()
    { 
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageCollectionPoint inMessage, outMessage;
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
        outMessage = new MessageCollectionPoint(MessageCollectionPoint.SHUT);
        con.writeObject (outMessage);
        System.out.println("SHUT SENT");
        inMessage = (MessageCollectionPoint) con.readObject ();
        con.close ();
        System.out.println("ACK RECEIVED");
        if (inMessage.getType () != MessageCollectionPoint.ACK){ 
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            while(true){}
            // System.exit (1);
        }
    }
}