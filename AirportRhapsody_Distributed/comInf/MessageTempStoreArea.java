package comInf;

import java.io.Serializable;

public class MessageTempStoreArea implements Serializable{


    /**
     *  Key
     *    @serialField serialVersionUID
     */
    private static final long serialVersionUID = -1746969311982653911L;

    // Message types

    /**
     * Insert bag
     * 
     * @serialField IB
     */
    public static final int IB = 1;

    /**
     * Collect a bag from Temporary Storage Area
     * 
     * @serialField CB
     */
    public static final int CB = 2;

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

    public MessageTempStoreArea (int type)
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