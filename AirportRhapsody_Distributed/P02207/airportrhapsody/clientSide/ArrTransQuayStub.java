package airportrhapsody.clientSide;

import java.util.List;

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
        while (!con.open ()) {}
        outMessage = new MessageArrTransQuay(MessageArrTransQuay.EBU);
        con.writeObject(outMessage);
        inMessage = (MessageArrTransQuay) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTransQuay.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
        
    }
    
    /**
     * Enter the bus
     * @param id passenger id
     */
    public void enterTheBus(int passengerID){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrTransQuay(MessageArrTransQuay.ETB,passengerID);
        con.writeObject(outMessage);
        inMessage = (MessageArrTransQuay) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTransQuay.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }
    /**
     * Park the bus
     * @param b bus driver
     */
    public void parkTheBus(){  
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrTransQuay(MessageArrTransQuay.PTB);
        con.writeObject(outMessage);
        inMessage = (MessageArrTransQuay) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTransQuay.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }
    /**
     * Take a bus
     * @param passengerID passengerID
     */
    public void takeABus(int passengerID) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrTransQuay(MessageArrTransQuay.TAB,passengerID);
        con.writeObject(outMessage);
        inMessage = (MessageArrTransQuay) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTransQuay.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }
    /**
     * Announcing bus boarding
     */
    public void announcingBusBoarding() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrTransQuay(MessageArrTransQuay.ABB);
        con.writeObject(outMessage);
        inMessage = (MessageArrTransQuay) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTransQuay.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }
    /**
     * Current number of passengers
     * @return
     */
    public int numPassengers(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrTransQuay(MessageArrTransQuay.NP);
        con.writeObject(outMessage);
        inMessage = (MessageArrTransQuay) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTransQuay.NP ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return -1;
        }else{
            return inMessage.getGenInt();
        }
    }

    public List<Integer> getSeats(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrTransQuay(MessageArrTransQuay.GS);
        con.writeObject(outMessage);
        inMessage = (MessageArrTransQuay) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTransQuay.GS ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return null;
        }else{
            return inMessage.getPassengersHandler();
        }
    }

    public boolean getDayEnd(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrTransQuay(MessageArrTransQuay.GDE);
        con.writeObject(outMessage);
        inMessage = (MessageArrTransQuay) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTransQuay.GDE ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return false;
        }else{
            return inMessage.getGenBool();
        }
    }

    public void setDayEnd(boolean st){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrTransQuay(MessageArrTransQuay.SDE,st);
        con.writeObject(outMessage);
        inMessage = (MessageArrTransQuay) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTransQuay.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    public boolean isEmpty(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrTransQuay(MessageArrTransQuay.IE);
        con.writeObject(outMessage);
        inMessage = (MessageArrTransQuay) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTransQuay.IE ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return false;
        }
        else{
            return inMessage.getGenBool();
        }
    }

    public void remove(int id){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageArrTransQuay(MessageArrTransQuay.R,id);
        con.writeObject(outMessage);
        inMessage = (MessageArrTransQuay) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageArrTransQuay.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    public void shutdown ()
    {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageArrTransQuay inMessage, outMessage;
        // while (!con.open ()){}
        System.out.println("BEFORE CON");
        while (!con.open ()){
            System.out.println("WHILE CON");
            try
        { Thread.currentThread ().sleep ((long) (10));
        }
        catch (InterruptedException e) {}
        }
        System.out.println("AFTER CON");
        outMessage = new MessageArrTransQuay(MessageArrTransQuay.SHUT);
        con.writeObject (outMessage);
        System.out.println("SHUT SENT");
        inMessage = (MessageArrTransQuay) con.readObject ();
        con.close ();
        System.out.println("ACK RECEIVED");
        if (inMessage.getType () != MessageArrTransQuay.ACK){ 
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            while(true){}
            // System.exit (1);
        }
    }
}