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
    }

    /**
     * Try to collect a bag from the arrival lounge
     * @param p porter
     * @return bag
     */
    public Luggage tryToCollectABag(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        return null;
    }

    /**
     * block porter
     */
    public void restPorter(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
    }
    /**
     * wake up porter
     */
    public void wakePorter(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
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
        return false;
    }

    /**
     * What should i do
     * @param p passenger
     * @return What passagenger should do according to his situation
     */
    public boolean whatShouldIDo(Passenger.Situation s) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        return false;
    }

    public boolean getDayEnd(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
        return false;
    }

    public void setDayEnd(boolean st){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrivalLounge inMessage, outMessage;
    }
}