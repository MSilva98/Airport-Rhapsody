package airportrhapsody.comInf;

import java.io.Serializable;

public class MessageDepTransQuay implements Serializable {


    /**
     *  Key
     *    @serialField serialVersionUID
     */
    private static final long serialVersionUID = 4513293397647022675L;
    // Message types

    /**
     * Passenger leave departure terminal transfer quay
     * 
     * @serialField LDTQ
     */
    public static final int LDTQ = 1;

    /**
     * Park the bus and let pass off
     * 
     * @serialField PTBALPO
     */
    public static final int PTBALPO = 2;

    /**
     * Passenger leave the bus
     * 
     * @serialField LB
     */
    public static final int LB = 3;

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

    public MessageDepTransQuay (int type)
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