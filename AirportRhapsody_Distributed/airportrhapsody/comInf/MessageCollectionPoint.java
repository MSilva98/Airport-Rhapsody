package airportrhapsody.comInf;

import java.io.Serializable;

public class MessageCollectionPoint implements Serializable{

    /**
     *  Key
     *    @serialField serialVersionUID
     */
    private static final long serialVersionUID = 4531138145036280166L;

    // Message types

    /**
     * Go collect a bag
     * 
     * @serialField GCAB
     */
    public static final int GCAB = 1;

    /**
     * Insert a bag in collection point
     * 
     * @serialField IB
     */
    public static final int IB = 2;

    /**
     * No more bags to collect
     * 
     * @serialField NMBTC
     */
    public static final int NMBTC = 3;

    /**
     * Leave coll point
     * 
     * @serialField LCP
     */
    public static final int LCP = 4;

    /**
     * No bags
     * 
     * @serialField NB
     */
    public static final int NB = 5;

    /**
     * Get no bags
     * 
     * @serialField GNB
     */
    public static final int GNB = 6;

    /**
     * Get size
     * 
     * @serialField S
     */
    public static final int S = 7;

    

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

    public MessageCollectionPoint (int type)
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