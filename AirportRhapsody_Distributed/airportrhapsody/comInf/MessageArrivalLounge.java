package airportrhapsody.comInf;

import java.io.Serializable;

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

    //Message arguments

    /**
     *  Message type
     *    @serialField msgType
     */

    private int msgType = -1;



    /**
     *  Instantiating a message (form 1).
     *
     *    @param type message type
     */

    public MessageArrivalLounge (int type)
    {
        msgType = type;
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