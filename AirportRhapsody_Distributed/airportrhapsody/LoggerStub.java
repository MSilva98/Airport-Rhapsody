package airportrhapsody;

import java.io.FileWriter;

import airportrhapsody.clientSide.ClientCom;
import airportrhapsody.comInf.MessageLogger;

public class LoggerStub {
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
    public LoggerStub(String serverHostName, int serverPortNumb){
        this.serverHostName = serverHostName;
        this.serverPortNumb = serverPortNumb;
    }

    // Flight number
    public void setFn(int fn) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.SFN,fn);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    // Number of pieces fo luggage presently at the plane's hold
    public void setBn(int bn) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.SBN,bn);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    // State of the porter
    public void setStatPorter(String statPorter) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.STP,statPorter);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    // Number of pieces of luggage presently on the conveyor belt
    public void setCb(int cb) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.SCB,cb);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    // Number of pieces of luggage belonging to passengers in transit presently stored at the storeroom
    public void setSr(int sr) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.SSR,sr);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    // State of the bus driver
    public void setStatDriver(String statDriver) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.SSD,statDriver);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    // Occupation state for the waiting queue
    public void setQ(int id, String state) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.SQ,id,state);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    // Set all queue empty
    public void setQEmpty() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.SQE);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    // Occupation state for the seat in the bus
    public void setS(int id, String state) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.SS,id,state);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    // Set all seats empty
    public void setSEmpty() {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.SSE);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    // State of the passengers
    public void setSt(int id, String state) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.SST,id,state);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    // Situation of the passengers
    public void setSi(int id, String state) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.SSI,id,state);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    // Number of pieces of luggage each passenger carried at the start ot his journey
    public void setNr(int id, int n) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.SNR,id,n);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    // Number of pieces of luggage each passenger has collected
    public void setNa(int id, int n) {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.SNA,id,n);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    // Number of pieces of luggage all passengers lost
    public void setMissing(int n){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.SM,n);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    public void clearVars(){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.CLRV);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    public void write(boolean end){
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()) {}
        outMessage = new MessageLogger(MessageLogger.WRT,end);
        con.writeObject(outMessage);
        inMessage = (MessageLogger) con.readObject();
        con.close();
        if(inMessage.getType() != MessageLogger.ACK ){
            System.out.println("Tipo inválido");
            System.exit (1);
        }
    }

    public void shutdown ()
    {
        ClientCom con = new ClientCom (serverHostName, serverPortNumb);
        MessageLogger inMessage, outMessage;
        while (!con.open ()){}
        outMessage = new MessageLogger(MessageLogger.SHUT);
        con.writeObject (outMessage);
        inMessage = (MessageLogger) con.readObject ();
        if (inMessage.getType () != MessageLogger.ACK){ 
            System.out.println ("Thread " + Thread.currentThread ().getName () + ": Tipo inválido!");
            System.out.println (inMessage.toString ());
            System.exit (1);
        }
        con.close ();
    }
}