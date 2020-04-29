package airportrhapsody.clientSide;

import airportrhapsody.comInf.MessageReclaimOffice;

/**
 * Baggage reclaim office stub
 */
public class ReclaimOfficeStub {
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
    public ReclaimOfficeStub(String serverHostName, int serverPortNumb){
        this.serverHostName = serverHostName;
        this.serverPortNumb = serverPortNumb;
    }

    /**
     * Report missing bags
     * @param numBags number of bags missing
     * @param p passenger
     */
    public Passenger.InternalState reportMissingBags(int numBags) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageReclaimOffice inMessage, outMessage;
        return null;
    }

    public int getNumBagsMissing() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageReclaimOffice inMessage, outMessage;
        return -1;
    }

    public int getReclaims() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageReclaimOffice inMessage, outMessage;
        return -1;
    }

    public void setReclaims(int reclaims) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageReclaimOffice inMessage, outMessage;
    }



}