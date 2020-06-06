package airportrhapsody.clientSide;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import airportrhapsody.comInf.Luggage;
import airportrhapsody.comInf.MessageTempStoreArea;

/**
 * Temporary Storage Area
 */
public class TempStorageAreaStub  {
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
     * Instantiating the Departure terminal transfer quay stub.
     */
    public TempStorageAreaStub(String serverHostName, int serverPortNumb){
        this.serverHostName = serverHostName;
        this.serverPortNumb = serverPortNumb;
    }
    /**
     * Insert bag in temporary storage area
     * @param l bag
     */
    public void insertBag(Luggage l){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageTempStoreArea inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageTempStoreArea(MessageTempStoreArea.IB,l);
        con.writeObject(outMessage);
        inMessage = (MessageTempStoreArea) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageTempStoreArea.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }
    /**
     * Collect a bag from Temporary Storage Area
     * @return bag
     */
    public Luggage collectBag(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageTempStoreArea inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageTempStoreArea(MessageTempStoreArea.CB);
        con.writeObject(outMessage);
        inMessage = (MessageTempStoreArea) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageTempStoreArea.CB ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return null;
        }else{
            return inMessage.getLugagge();
        }
    }

    public int size(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageTempStoreArea inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageTempStoreArea(MessageTempStoreArea.S);
        con.writeObject(outMessage);
        inMessage = (MessageTempStoreArea) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageTempStoreArea.S ){
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
        MessageTempStoreArea inMessage, outMessage;
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
        outMessage = new MessageTempStoreArea(MessageTempStoreArea.SHUT);
        con.writeObject (outMessage);
        System.out.println("SHUT SENT");
        inMessage = (MessageTempStoreArea) con.readObject ();
        con.close ();
        System.out.println("ACK RECEIVED");
        if (inMessage.getType () != MessageTempStoreArea.ACK){ 
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            while(true);
            // System.exit (1);
        }
    }
}