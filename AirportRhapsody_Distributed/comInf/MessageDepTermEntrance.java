package comInf;

import java.io.Serializable;

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
     *  Instantiating a message (form 1).
     *
     *    @param type message type
     */

    public MessageDepTermEntrance (int type)
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