package airportrhapsody.comInf;

import java.io.Serializable;

import airportrhapsody.clientSide.Passenger;

public class MessageReclaimOffice implements Serializable {

    /**
     *  Key
     *    @serialField serialVersionUID
     */
    private static final long serialVersionUID = -1568907649192352215L;

    // Message types

    /**
     * Report missing bags
     * 
     * @serialField RMB
     */
    public static final int RMB = 1;

    /**
     * get number of bags missing
     * 
     * @serialField GNBM
     */
    public static final int GNBM = 2;

    /**
     * Get reclaims
     * 
     * @serialField GR
     */
    public static final int GR = 3;

    /**
     * Set reclaims
     * 
     * @serialField SR
     */
    public static final int SR = 4;

    /**
     * Success
     * 
     * @serialField ACK
     */
    public static final int ACK =  5;


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
     *  Passenger ID or bags or reclaims
     *    @serialField genInt
     */
    private int genInt = -1;



    /**
     *  Instantiating a message (form 1).
     *
     *    @param type message type
     */

    public MessageReclaimOffice (int type)
    {
        msgType = type;
    }

    /**
     *  Instantiating a message (form 2).
     *
     *    @param type message type
     *    @param st Passenger state
     */
    public MessageReclaimOffice (int type, Passenger.InternalState st)
    {
        msgType = type;
        this.st = st;
    }

    /**
     *  Instantiating a message (form 2).
     *
     *    @param type message type
     *    @param i Passenger ID or bags or reclaims
     */
    public MessageReclaimOffice (int type, int genInt)
    {
        msgType = type;
        this.genInt = genInt;
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

    public Passenger.InternalState getPassengerSt(){
        return pst;
    }

    public int getGenInt(){
        return genInt;
    }
}