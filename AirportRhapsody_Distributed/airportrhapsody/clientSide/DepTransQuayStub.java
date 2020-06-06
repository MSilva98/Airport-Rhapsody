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
        while (!con.open ()) {}
        outMessage = new MessageDepTransQuay(MessageDepTransQuay.LB, passengerID);
        con.writeObject(outMessage);
        inMessage = (MessageDepTransQuay) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageDepTransQuay.LB ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return null;
        }else{
            return inMessage.getPassengerSt();
        }
    }

    /**
     * Passenger leave departure terminal transfer quay
     * @param id passenger id
     * @return  passenger
     */
    // public Passenger leaveDepTransQuay(int id){
    //     ClientCom con = new ClientCom (serverHostName, serverPortNumb);
    //     MessageDepTransQuay inMessage, outMessage;
    //     while (!con.open ()) {}
    //     outMessage = new MessageDepTransQuay(MessageDepTransQuay.LDTQ, id);
    //     con.writeObject(outMessage);
    //     inMessage = (MessageDepTransQuay) con.readObject ();
    //     con.close ();
    //     if(inMessage.getType() != MessageDepTransQuay.LDTQ ){
    //         System.out.println("Tipo inválido");
    //         System.exit (1);
    //         return null;
    //     }else{
    //         return inMessage.getPassenger();
    //     }
    // }
    /**
     * Park the bus and let pass off
     * @param b bus driver
     */
    public BusDriver.InternalState parkTheBusAndLetPassOff(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageDepTransQuay inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageDepTransQuay(MessageDepTransQuay.PTBALPO);
        con.writeObject(outMessage);
        inMessage = (MessageDepTransQuay) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageDepTransQuay.PTBALPO ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return null;
        }else{
            return inMessage.getBusDriverSt();
        }
    }

    public void shutdown ()
    {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageDepTransQuay inMessage, outMessage;
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
        outMessage = new MessageDepTransQuay(MessageDepTransQuay.SHUT);
        con.writeObject (outMessage);
        System.out.println("SHUT SENT");
        inMessage = (MessageDepTransQuay) con.readObject ();
        con.close ();
        System.out.println("ACK RECEIVED");
        if (inMessage.getType () != MessageDepTransQuay.ACK){ 
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            while(true);
            // System.exit (1);
        }
    }
}