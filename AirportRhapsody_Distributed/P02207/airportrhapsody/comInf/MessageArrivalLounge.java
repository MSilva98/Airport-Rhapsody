package airportrhapsody.comInf;

import java.io.Serializable;

import airportrhapsody.clientSide.Passenger;



/**
 * This type of data defines messages that are exchanged between clients and Arrival Lounge server
 */

public class MessageArrivalLounge implements Serializable{

    /**
     *  Key
     *    @serialField serialVersionUID
     */

    private static final long serialVersionUID = -6338611818174727389L;

    // Message types

    /**
     * Put a bag on the arrival lounge
     * 
     * @serialField PB
     */
    public static final int PB = 1;

    /**
     * Try to collect a bag from the arrival lounge
     * 
     * @serialField TTCB
     */
    public static final int TTCAB = 2;

    /**
     * Block porter
     * 
     * @serialField RP
     */
    public static final int RP = 3;

    /**
     * Wake up porter
     * 
     * @serialField WP
     */
    public static final int WP = 4;

    /**
     * Take a rest
     * 
     * @serialField TAR
     */
    public static final int TAR = 5;

    /**
     * What should i do
     * 
     * @serialField WSID
     */
    public static final int WSID = 6;

    /**
     * Get day end
     * 
     * @serialField GDE
     */
    public static final int GDE = 7;

    /**
     * Set day end
     * 
     * @serialField SDE
     */
    public static final int SDE = 8;

    /**
     * Success
     * 
     * @serialField ACK
     */
    public static final int ACK =  9;

    /**
     * Size
     * 
     * @serialField S
     */
    public static final int S =  10;

    /**
     * Shut
     * 
     * @serialField Shut
     */
    public static final int SHUT =  11;

    /**
     * Set Porter End
     * 
     * @serialField SE
     */
    public static final int SPE =  12;

    /**
     * Get Porter End
     * 
     * @serialField GE
     */
    public static final int GPE =  13;

    /**
     * Set Passengers End
     * 
     * @serialField SE
     */
    public static final int SPAE =  14;

    /**
     * Get Passengers End
     * 
     * @serialField GE
     */
    public static final int GPAE =  15;

    //Message arguments

    /**
     *  Message type
     *    @serialField msgType
     */

    private int msgType = -1;

    /**
     *  Luggage
     *    @serialField l
     */
    private Luggage l = null;

    /**
     *  Passenger situation
     *    @serialField s
     */
    private Passenger.Situation s = null;

    /**
     *  Day state or What passagenger should do according to his situation
     *    @serialField genBool
     */
    private boolean genBool;

    /**
     *  Size
     *    @serialField size
     */
    private int size = -1;

    // /**
    //  *  What passagenger should do according to his situation
    //  *    @serialField whatIDo
    //  */
    // private boolean whatIDo;

    /**
     *  Instantiating a message (form 1).
     *
     *    @param type message type
     */

    public MessageArrivalLounge (int type)
    {
        this.msgType = type;
    }

    /**
     *  Instantiating a message (form 2).
     *
     *    @param type message type
     *    @param l luggage
     */

    public MessageArrivalLounge (int type, Luggage l)
    {
        this.msgType = type;
        this.l = l;
    }

    /**
     *  Instantiating a message (form 3).
     *
     *    @param type message type
     *    @param b state
     */

    public MessageArrivalLounge (int type, Boolean genBool)
    {
        this.msgType = type;
        this.genBool = genBool;
    }

    /**
     *  Instantiating a message (form 4).
     *
     *    @param type message type
     *    @param s Passenger situation
     */

    public MessageArrivalLounge (int type, Passenger.Situation s)
    {
        this.msgType = type;
        this.s = s;
    }

    /**
     *  Instantiating a message (form 5).
     *
     *    @param type message type
     *    @param s Passenger situation
     */

    public MessageArrivalLounge (int type, int size)
    {
        this.msgType = type;
        this.size = size;
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

    public Luggage getLuggage(){
        return l;
    }

    public boolean getGenBool(){
        return genBool;
    }

    public Passenger.Situation getSituation(){
        return s;
    }

    public int getSize(){
        return size;
    }
}