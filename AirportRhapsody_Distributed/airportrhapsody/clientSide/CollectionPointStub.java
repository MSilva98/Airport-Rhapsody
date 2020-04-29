package airportrhapsody.clientSide;

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
        return false;
        
    }    
    /**
     * Insert a bag in collection point
     * @param l bag
     */
    public void insertBag(Luggage l){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageCollectionPoint inMessage, outMessage;
    }

    /**
     * No more bags to collect
     * @param p porter
     */
    public Porter.InternalState noMoreBagsToCollect() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageCollectionPoint inMessage, outMessage;
        return null;
    }

    public void leaveCollPoint(int id){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageCollectionPoint inMessage, outMessage;
    }

    public void noBags(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageCollectionPoint inMessage, outMessage;
    }

    public boolean getNoBags(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageCollectionPoint inMessage, outMessage;
        return false;
    }
    public int size(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageCollectionPoint inMessage, outMessage;
        return -1;
    }
}