package airportrhapsody.comInf;

import java.io.Serializable;

import airportrhapsody.clientSide.Passenger;

/**
 * This type of data defines messages that are exchanged between clients and Arrival terminal exit server
 */

public class MessageArrTermExit implements Serializable{

    /**
     *  Key
     *    @serialField serialVersionUID
     */
    private static final long serialVersionUID = 637939385960773749L;

    // Message types

    /**
     * Simulate barrier
     * 
     * @serialField LAD
     */
    public static final int LAD = 1;

    /**
     * Insert passenger in terminal
     * 
     * @serialField AR
     */
    public static final int AR = 2;

    /**
     * Remove passenger from terminal
     * 
     * @serialField LT
     */
    public static final int LT = 3;

    /**
     * Check if terminal is empty
     * 
     * @serialField EP
     */
    public static final int EP = 4;

    /**
     * Set the end of the work
     * 
     * @serialField EOW
     */
    public static final int EOW = 5;

    /**
     * Go home
     * 
     * @serialField GH
     */
    public static final int GH = 6;
    

    //Message arguments

    /**
     *  Message type
     *    @serialField msgType
     */

    private int msgType = -1;

    /**
     *  Passenger internal state
     *    @serialField st
     */
    private Passenger.InternalState st = null;


    /**
     *  Passenger id
     *    @serialField passengerID
     */
    private int passengerID = -1;

    /**
     *  Passenger 
     *    @serialField p
     */
    private Passenger p = null;

    /**
     *  Terminal state 
     *    @serialField isEmpty
     */
    private boolean isEmpty;

    /**
     *  Instantiating a message (form 1).
     *
     *    @param type message type
     */

    public MessageArrTermExit (int type)
    {
        msgType = type;
    }

    /**
     *  Instantiating a message (form 2).
     *
     *    @param type message type
     *    @param passengerID passengerID
     */

    public MessageArrTermExit (int type,  int passengerID)
    {
        msgType = type;
        this.passengerID = passengerID;
    }

    /**
     *  Instantiating a message (form 3).
     *
     *    @param type message type
     *    @param st passenger state
     */

    public MessageArrTermExit (int type, Passenger.InternalState st)
    {
        msgType = type;
        this.st = st;
    }

    /**
     *  Instantiating a message (form 4).
     *
     *    @param type message type
     *    @param p passenger 
     */

    public MessageArrTermExit (int type, Passenger p)
    {
        msgType = type;
        this.p = p;
    }

    /**
     *  Instantiating a message (form 4).
     *
     *    @param type message type
     *    @param isEmpty 
     */

    public MessageArrTermExit (int type, Boolean isEmpty)
    {
        msgType = type;
        this.isEmpty = isEmpty;
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

}