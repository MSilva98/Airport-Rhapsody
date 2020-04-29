package airportrhapsody.comInf;

import java.io.Serializable;

import airportrhapsody.clientSide.Passenger;

public class MessageDepTermEntrance implements Serializable{

    /**
     *  Key
     *    @serialField serialVersionUID
     */
    private static final long serialVersionUID = 3124977643902114045L;

    // Message types

    /**
     * Insert passenger in terminal
     * 
     * @serialField AR
     */
    public static final int AR = 1;

    /**
     * Remove passenger from terminal
     * 
     * @serialField LT
     */
    public static final int LT = 2;

    /**
     * Check if terminal is empty
     * 
     * @serialField EP
     */
    public static final int EP = 3;

    /**
     * Prepare next leg
     * 
     * @serialField PNL
     */
    public static final int PNL = 4;

    //Message arguments

    /**
     *  Message type
     *    @serialField msgType
     */
    private int msgType = -1;

    /**
     *  Passenger state
     *    @serialField st
     */
    private Passenger.InternalState st = null;

    /**
     *  Passenger ID
     *    @serialField passengerID
     */
    private int passengerID = -1;

    /**
     * 
     *    @serialField genBool
     */
    private boolean genBool;

    /**
     *  Passenger 
     *    @serialField st
     */
    private Passenger p = null;



    /**
     *  Instantiating a message (form 1).
     *
     *    @param type message type
     */
    public MessageDepTermEntrance (int type)
    {
        msgType = type;
    }

    /**
     *  Instantiating a message (form 2).
     *
     *    @param type message type
     *    @param st Passenger state
     */
    public MessageDepTermEntrance (int type, Passenger.InternalState st)
    {
        msgType = type;
        this.st = st;
    }

    /**
     *  Instantiating a message (form 3).
     *
     *    @param type message type
     *    @param p Passenger
     */
    public MessageDepTermEntrance (int type, Passenger p)
    {
        msgType = type;
        this.p = p;
    }

    /**
     *  Instantiating a message (form 4).
     *
     *    @param type message type
     *    @param passengerID Passenger ID
     */
    public MessageDepTermEntrance (int type, int passengerID)
    {
        msgType = type;
        this.passengerID = passengerID;
    }

    /**
     *  Instantiating a message (form 4).
     *
     *    @param type message type
     *    @param b
     */
    public MessageDepTermEntrance (int type, boolean genBool)
    {
        msgType = type;
        this.genBool = genBool;
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