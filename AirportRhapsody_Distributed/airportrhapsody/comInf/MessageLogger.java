package airportrhapsody.comInf;

import java.io.Serializable;

/**
 * This type of data defines messages that are exchanged between clients and Arrival Lounge server
 */

public class MessageLogger implements Serializable{

    /**
     *  Key
     *    @serialField serialVersionUID
     */
    private static final long serialVersionUID = 5263098866134912580L;

    // Message types

    /**
     * Set flight number
     * 
     * @serialField SFN
     */
    public static final int SFN = 1;

    /**
     * Set number of bags at plane's hold
     * 
     * @serialField SBN
     */
    public static final int SBN = 2;

    /**
     * Set Porter state
     * 
     * @serialField STP
     */
    
     public static final int STP = 3;
    
     /**
     * Set number of bags on the conveyor belt
     * 
     * @serialField SCB
     */
    public static final int SCB = 4;
       
    /**
     * Set number of bags in transit
     * 
     * @serialField SSR
     */
    public static final int SSR = 5;
       
    /**
     * Set Bus Driver state
     * 
     * @serialField SSD
     */
    public static final int SSD = 6;
       
    /**
     * Set occupation in waiting queue
     * 
     * @serialField SQ
     */
    public static final int SQ = 7;
       
    /**
     * Empty waiting queue
     * 
     * @serialField SQE
     */
    public static final int SQE = 8;
       
    /**
     * Set occupation in bus seats
     * 
     * @serialField SS
     */
    public static final int SS = 9;
       
    /**
     * Empty bus seats
     * 
     * @serialField SSE
     */
    public static final int SSE = 10;
       
    /**
     * Set state of passenger
     * 
     * @serialField SST
     */
    public static final int SST = 11;
       
    /**
     * Set situation of passenger
     * 
     * @serialField SSI
     */
    public static final int SSI = 12;
       
    /**
     * Set number of bags a passenger carried in the begin
     * 
     * @serialField SNR
     */
    public static final int SNR = 13;
       
    /**
     * Set number of bags a passenger has collected
     * 
     * @serialField SNA
     */
    public static final int SNA = 14;
       
    /**
     * Set missing bags
     * 
     * @serialField SM
     */
    public static final int SM = 15;
       
    /**
     * Clear all variables
     * 
     * @serialField CLRV
     */
    public static final int CLRV = 16;
       
    /**
     * Write variables to log file
     * 
     * @serialField WRT
     */
    public static final int WRT = 17;

    /**
     * Success
     * 
     * @serialField ACK
     */
    public static final int ACK = 18;   
    
    // Message arguments
    
    /**
     *  Message type
     *    @serialField msgType
     */
    private int msgType = -1;

    /**
     *  Flight number, Bags at plane's hold, Bags at conveyor belt, Bags in transit, Bags a passenger carried, Bags a passenger collected or Bags lost  
     *    @serialField genInt
     */
    private int genInt = -1;

    /**
     *  Passenger ID
     *    @serialField passengerID
     */

    private int passengerID = -1;

    /**
     *  Porter state, Passenger State, Driver State, Waiting Queue state or Seat State
     *    @serialField genStr
     */

    private String genStr = null;

    /**
     * Last write to file
     * @serialField end
     */
    private Boolean end;

    /**
     *  Instantiating a message (form 1).
     *
     *    @param type message type
     */

    public MessageLogger (int type)
    {
        this.msgType = type;
    }

    /**
     *  Instantiating a message (form 2).
     *
     *    @param type message type
     *    @param n    genInt
     */

    public MessageLogger (int type, int n)
    {
        this.msgType = type;
        this.genInt = n;
    }
    
    /**
     *  Instantiating a message (form 3).
     *
     *    @param type message type
     *    @param id   passenger ID
     *    @param n    genInt
     */

    public MessageLogger (int type, int id, int n)
    {
        this.msgType = type;
        this.passengerID = id;
        this.genInt = n;
    }
    
    /**
     *  Instantiating a message (form 4).
     *
     *    @param type  message type
     *    @param state state
     */

    public MessageLogger (int type, String state)
    {
        this.msgType = type;
        this.genStr = state;
    }

    /**
     *  Instantiating a message (form 5).
     *
     *    @param type  message type
     *    @param id    passenger ID
     *    @param state state
     */

    public MessageLogger (int type, int id, String state)
    {
        this.msgType = type;
        this.genInt = id;
        this.genStr = state;
    }

    /**
     *  Instantiating a message (form 6).
     *
     *    @param type message type
     *    @param end  last write to file
     */

    public MessageLogger (int type, boolean end)
    {
        this.msgType = type;
        this.end = end;
    }

    /**
     *  Obtain the value of the message type field.
     *
     *    @return message type
     */

    public int getType ()
    {
        return (msgType);
    }

    public String getGenStr(){
        return genStr;
    }

    public int getGenInt(){
        return genInt;
    }

    public int getPassengerID(){
        return passengerID;
    }

    public boolean getEnd(){
        return end;
    }
}