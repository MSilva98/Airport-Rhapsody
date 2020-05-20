package airportrhapsody.comInf;

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

    /**
     * Success
     * 
     * @serialField ACK
     */
    public static final int ACK =  3;

    /**
     * Size
     * 
     * @serialField S
     */
    public static final int S =  4;

    /**
     * Shut
     * 
     * @serialField Shut
     */
    public static final int SHUT =  5;

    //Message arguments

    /**
     *  Message type
     *    @serialField msgType
     */

    private int msgType = -1;


    /**
     *  Luaggage
     *    @serialField l
     */

    private Luggage l = null;

    /**
     *  size
     *    @serialField size
     */

    private int size = -1;


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
     *  Instantiating a message (form 2).
     *
     *    @param type message type
     */

    public MessageTempStoreArea (int type, Luggage l)
    {
        msgType = type;
        this.l = l;
    }
    /**
     *  Instantiating a message (form 3).
     *
     *    @param type message type
     */

    public MessageTempStoreArea (int type, int size)
    {
        msgType = type;
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

    public Luggage getLugagge(){
        return l;
    }

    public int getSize(){
        return size;
    }


}