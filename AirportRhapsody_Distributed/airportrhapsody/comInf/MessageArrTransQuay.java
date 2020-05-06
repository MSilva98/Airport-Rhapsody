package airportrhapsody.comInf;

import java.io.Serializable;
import java.util.List;

import airportrhapsody.clientSide.Passenger;

public class MessageArrTransQuay implements Serializable{

    /**
     *  Key
     *    @serialField serialVersionUID
     */
    private static final long serialVersionUID = 1422008771379026967L;

    // Message types

    /**
     * Enter Bus Up
     * 
     * @serialField EBU
     */
    public static final int EBU = 1;

    /**
     * Enter the bus
     * 
     * @serialField ETB
     */
    public static final int ETB = 2;

    /**
     * Park the bus
     * 
     * @serialField PTB
     */
    public static final int PTB = 3;

    /**
     * Take a bus
     * 
     * @serialField TAB
     */
    public static final int TAB = 4;

    /**
     * Announcing bus boarding
     * 
     * @serialField ABB
     */
    public static final int ABB = 5;

    /**
     * Current number of passengers - numPassengers
     * 
     * @serialField NP
     */
    public static final int NP = 6;

    /**
     * Get seats
     * 
     * @serialField GS
     */
    public static final int GS = 7;

    /**
     * Get day end
     * 
     * @serialField GDE
     */
    public static final int GDE = 8;

    /**
     * Set day end
     * 
     * @serialField SDE
     */
    public static final int SDE = 9;

    /**
     * Tranfer Quay Is Empty
     * 
     * @serialField IE
     */
    public static final int IE = 10;

    /**
     * Success
     * 
     * @serialField ACK
     */
    public static final int ACK =  11;
    

    //Message arguments

    /**
     *  Message type
     *    @serialField msgType
     */
    private int msgType = -1;

    /**
     * Passenger ID or numOfPassengers
     *    @serialField genInt
     */
    private int genInt = -1;

    /**
     *  Passenger 
     *    @serialField p
     */
    private Passenger p = null;

    /**
     *  Day state 
     *    @serialField genBool
     */
    private boolean genBool;

    /**
     *  Passengers handler
     *    @serialField p
     */
    private List<Integer> ph = null;


    /**
     *  Instantiating a message (form 1).
     *
     *    @param type message type
     */

    public MessageArrTransQuay (int type)
    {
        msgType = type;
    }

    /**
     *  Instantiating a message (form 2).
     *
     *    @param type message type
     *    @param genI Passenger ID or numOfPassenger
     */

    public MessageArrTransQuay (int type, int genInt)
    {
        msgType = type;
        this.genInt = genInt;
    }

    /**
     *  Instantiating a message (form 3).
     *
     *    @param type message type
     *    @param p Passenger
     */

    public MessageArrTransQuay (int type, Passenger p)
    {
        msgType = type;
        this.p = p;
    }

     /**
     *  Instantiating a message (form 4).
     *
     *    @param type message type
     *    @param b state
     */

    public MessageArrTransQuay  (int type, boolean genBool)
    {
        this.msgType = type;
        this.genBool = genBool;
        
    }

    /**
     *  Instantiating a message (form 5).
     *
     *    @param type message type
     *    @param ph Passengers handler
     */

    public MessageArrTransQuay (int type, List<Integer> ph)
    {
        msgType = type;
        this.ph = ph;
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

	public int getGenInt() {
		return genInt;
    }
    
    public boolean getGenBool() {
        return genBool;
    }

    public List<Integer> getPassengersHandler(){
        return ph;
    }
}