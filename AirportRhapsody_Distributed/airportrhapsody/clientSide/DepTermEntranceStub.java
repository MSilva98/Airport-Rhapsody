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
        while (!con.open ()) {}
        outMessage = new MessageDepTermEntrance(MessageDepTermEntrance.AT, passengerID);
        con.writeObject(outMessage);
        inMessage = (MessageDepTermEntrance) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageDepTermEntrance.AT ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return null;
        }else{
            return inMessage.getPassengerSt();
        }
    }

    // /**
    //  * Remove passenger from terminal
    //  * @return passenger
    //  */
    // public Passenger leftTerm(){
    //     ClientCom con = new ClientCom (serverHostName, serverPortNumb);
    //     MessageDepTermEntrance inMessage, outMessage;
    //     while (!con.open ()) {}
    //     outMessage = new MessageDepTermEntrance(MessageDepTermEntrance.LT);
    //     con.writeObject(outMessage);
    //     inMessage = (MessageDepTermEntrance) con.readObject ();
    //     con.close ();
    //     if(inMessage.getType() != MessageDepTermEntrance.LT ){
    //         System.out.println("Tipo inválido");
    //         System.exit (1);
    //         return null;
    //     }else{
    //         return inMessage.getPassenger();
    //     }
    // }
    // /**
    //  * Check if terminal is empty
    //  *  @return <li> true if is empty
    //  *          <li> else false
    //  */
    // public boolean emptyTerm(){
    //     ClientCom con = new ClientCom (serverHostName, serverPortNumb);
    //     MessageDepTermEntrance inMessage, outMessage;
    //     while (!con.open ()) {}
    //     outMessage = new MessageDepTermEntrance(MessageDepTermEntrance.EP);
    //     con.writeObject(outMessage);
    //     inMessage = (MessageDepTermEntrance) con.readObject ();
    //     con.close ();
    //     if(inMessage.getType() != MessageDepTermEntrance.EP ){
    //         System.out.println("Tipo inválido");
    //         System.exit (1);
    //         return false;
    //     }else{
    //         return inMessage.getGenBool();
    //     }
    // }

    /**
     * Prepare next leg
     * @param depTransQuay Departure terminal transfer quay
     * @param p passenger
     */
    public void prepareNextLeg(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageDepTermEntrance inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageDepTermEntrance(MessageDepTermEntrance.PNL);
        con.writeObject(outMessage);
        inMessage = (MessageDepTermEntrance) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageDepTermEntrance.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }
}