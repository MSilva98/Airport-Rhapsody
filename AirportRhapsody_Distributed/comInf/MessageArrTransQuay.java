package comInf;

import java.io.Serializable;

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

    public MessageArrTransQuay (int type)
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