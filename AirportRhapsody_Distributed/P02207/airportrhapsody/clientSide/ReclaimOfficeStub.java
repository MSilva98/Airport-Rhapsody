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
        while (!con.open ()) {}
        outMessage = new MessageReclaimOffice(MessageReclaimOffice.RMB, numBags);
        con.writeObject(outMessage);
        inMessage = (MessageReclaimOffice) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageReclaimOffice.RMB ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return null;
        }else{
            return inMessage.getPassengerSt();
        }
    }

    public int getNumBagsMissing() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageReclaimOffice inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageReclaimOffice(MessageReclaimOffice.GNBM);
        con.writeObject(outMessage);
        inMessage = (MessageReclaimOffice) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageReclaimOffice.GNBM ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return -1;
        }else{
            return inMessage.getGenInt();
        }
    }

    public int getReclaims() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageReclaimOffice inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageReclaimOffice(MessageReclaimOffice.GR);
        con.writeObject(outMessage);
        inMessage = (MessageReclaimOffice) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageReclaimOffice.GR ){
            System.out.println("Tipo inválido");
            System.exit (1);
            return -1;
        }else{
            return inMessage.getGenInt();
        }
    }

    public void setReclaims(int reclaims) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageReclaimOffice inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageReclaimOffice(MessageReclaimOffice.SR,reclaims);
        con.writeObject(outMessage);
        inMessage = (MessageReclaimOffice) con.readObject ();
        con.close ();
        if(inMessage.getType() != MessageReclaimOffice.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    public void shutdown ()
    {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageReclaimOffice inMessage, outMessage;
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
        outMessage = new MessageReclaimOffice(MessageReclaimOffice.SHUT);
        con.writeObject (outMessage);
        System.out.println("SHUT SENT");
        inMessage = (MessageReclaimOffice) con.readObject ();
        con.close ();
        System.out.println("ACK RECEIVED");
        if (inMessage.getType () != MessageReclaimOffice.ACK){ 
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            while(true);
            // System.exit (1);
        }
    }

}