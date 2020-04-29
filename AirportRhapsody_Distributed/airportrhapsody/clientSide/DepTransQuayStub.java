package airportrhapsody.clientSide;
import airportrhapsody.comInf.MessageDepTransQuay;
/**
 * Departure terminal transfer quay stub
 */
public class DepTransQuayStub  {
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
    public DepTransQuayStub(String serverHostName, int serverPortNumb){
        this.serverHostName = serverHostName;
        this.serverPortNumb = serverPortNumb;
    }

    /**
     * Passenger leave the bus
     * @param p passenger
     */
    public Passenger.InternalState leaveBus(int passengerID){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageDepTransQuay inMessage, outMessage;
        
        return null;
    }

    /**
     * Passenger leave departure terminal transfer quay
     * @param id passenger id
     * @return  passenger
     */
    public Passenger leaveDepTransQuay(int id){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageDepTransQuay inMessage, outMessage;
        return null;
    }
    /**
     * Park the bus and let pass off
     * @param b bus driver
     */
    public BusDriver.InternalState parkTheBusAndLetPassOff(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageDepTransQuay inMessage, outMessage;
        return null;
    }
}