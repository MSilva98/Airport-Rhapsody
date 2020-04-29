package airportrhapsody.clientSide;

import airportrhapsody.comInf.MessageDepTermEntrance;

/**
 * Departure terminal entrance stub
 */
public class DepTermEntranceStub {
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
     * Instantiating theDeparture terminal entrance stub.
     */
    public DepTermEntranceStub(String serverHostName, int serverPortNumb){
        this.serverHostName = serverHostName;
        this.serverPortNumb = serverPortNumb;
    }
    /**
     * Insert passenger in terminal
     * @param p passenger
     */
    public Passenger.InternalState arrivedTerm(int passengerID){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageDepTermEntrance inMessage, outMessage;
        return null;
    }

    /**
     * Remove passenger from terminal
     * @return passenger
     */
    public Passenger leftTerm(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageDepTermEntrance inMessage, outMessage;
        return null;
    }
    /**
     * Check if terminal is empty
     *  @return <li> true if is empty
     *          <li> else false
     */
    public boolean emptyTerm(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageDepTermEntrance inMessage, outMessage;
        return false;
    }
    /**
     * Prepare next leg
     * @param depTransQuay Departure terminal transfer quay
     * @param p passenger
     */
    public void prepareNextLeg(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageDepTermEntrance inMessage, outMessage;
    }
}