package comInf;

import java.io.Serializable;

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

    public MessageReclaimOffice (int type)
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