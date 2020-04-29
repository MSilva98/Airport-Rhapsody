package airportrhapsody.clientSide;

import airportrhapsody.comInf.MessageArrTermExit;

/**
 * Arrival terminal exit stub
 */
public class ArrTermExitStub  {
    

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
     * Instantiating the arrival terminal exit stub.
     */
    public ArrTermExitStub(String serverHostName, int serverPortNumb){
        this.serverHostName = serverHostName;
        this.serverPortNumb = serverPortNumb;
    }

    /**
     * Simulate barrier
     */
    public void leaveAirpDown() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTermExit inMessage, outMessage;
    }

    /**
     * Inser passenger in terminal
     * @param p passanger
     */
    public Passenger.InternalState arrivedTerm(int passengerID) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTermExit inMessage, outMessage;
        return null;
    }
    /**
     * Remove passenger from terminal
     * @return passenger
     */
    public Passenger leftTerm() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTermExit inMessage, outMessage;
        return null;
    }


    /**
     * Check if terminal is empty
     * @return true if is empty
     */
    public boolean emptyTerm() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTermExit inMessage, outMessage;
        return false;
    }
    /**
     * Set the end of the work
     */
    private void endOfWork(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTermExit inMessage, outMessage;
    }
    /**
     * Go home
     * @param p passenger
     */
    public void goHome() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTermExit inMessage, outMessage;
    }
}