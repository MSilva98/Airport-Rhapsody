package airportrhapsody.clientSide;

import airportrhapsody.comInf.MessageArrTransQuay;
import airportrhapsody.comInf.PassengersHandler;

/**
 * Arrival terminal transfer quay stub
 */
public class ArrTransQuayStub {
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
     * Instantiating the Arrival terminal transfer quay stub.
     */
    public ArrTransQuayStub(String serverHostName, int serverPortNumb){
        this.serverHostName = serverHostName;
        this.serverPortNumb = serverPortNumb;
    }
    /**
     * Free passengers
     */
    public void enterBusUp() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
        
    }
    
    /**
     * Enter the bus
     * @param id passenger id
     */
    public void enterTheBus(int passengerID){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
    }
    /**
     * Park the bus
     * @param b bus driver
     */
    public void parkTheBus(){  
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
    }
    /**
     * Take a bus
     * @param p passenger
     */
    public void takeABus(Passenger p) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
    }
    /**
     * Announcing bus boarding
     */
    public void announcingBusBoarding() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
    }
    /**
     * Current number of passengers
     * @return
     */
    public int numPassengers(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
        return -1;
    }

    public PassengersHandler getSeats(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
       return null;
    }

    public boolean getDayEnd(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
        return false;
    }

    public void setDayEnd(boolean st){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
    }
}