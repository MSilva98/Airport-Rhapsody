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
        while (!con.open ()) {}
        outMessage = new MessageArrTermExit(MessageArrTermExit.LAD);
        con.writeObject(outMessage);
        inMessage = (MessageArrTermExit) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTermExit.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    /**
     * Inser passenger in terminal
     * @param p passanger
     */
    public Passenger.InternalState arrivedTerm(int passengerID) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTermExit inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrTermExit.AT, passengerID);
        con.writeObject(outMessage);
        inMessage = (MessageArrTermExit) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTermExit.AT ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return null;
        }else{
            return inMessage.getSt();
        }
    }
    /**
     * Remove passenger from terminal
     * @return passenger
     */
    public Passenger leftTerm() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTermExit inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrTermExit.LT);
        con.writeObject(outMessage);
        inMessage = (MessageArrTermExit) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTermExit.LT ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return null;
        }else{
            return inMessage.getPassenger();
        }
    }


    /**
     * Check if terminal is empty
     * @return true if is empty
     */
    public boolean emptyTerm() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTermExit inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrivalLounge(MessageArrTermExit.EP);
        con.writeObject(outMessage);
        inMessage = (MessageArrTermExit) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTermExit.EP ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return false;
        }else{
            return inMessage.getIsEmpty();
        }
    }
    /**
     * Set the end of the work
     */
    private void endOfWork(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTermExit inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrTermExit(MessageArrTermExit.EOW);
        con.writeObject(outMessage);
        inMessage = (MessageArrTermExit) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTermExit.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }
    /**
     * Go home
     * @param p passenger
     */
    public void goHome() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTermExit inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrTermExit(MessageArrTermExit.GH);
        con.writeObject(outMessage);
        inMessage = (MessageArrTermExit) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTermExit.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }
}