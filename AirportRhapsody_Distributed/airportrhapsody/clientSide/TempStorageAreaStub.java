package airportrhapsody.clientSide;

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
            return -1;
        }else{
            return inMessage.getLugagge();
        }
    }
}