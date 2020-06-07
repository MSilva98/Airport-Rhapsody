package airportrhapsody.comInf;

import java.io.Serializable;

import airportrhapsody.clientSide.Porter;

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

    /**
     * Success
     * 
     * @serialField ACK
     */
    public static final int ACK =  8;

    /**
     * Shut
     * 
     * @serialField Shut
     */
    public static final int SHUT =  9;
    

    //Message arguments

    /**
     *  Message type
     *    @serialField msgType
     */
    private int msgType = -1;

    /**
     *  Passenger ID
     *    @serialField passengerID
     */
    private int passengerID = -1;

    /**
     *  Nuber of bags
     *    @serialField nr
     */
    private int nr = -1;

    /**
     * 
     *    @serialField genBool
     */
    private boolean genBool;

    /**
     *  Luaggage
     *    @serialField l
     */
    private Luggage l = null;

    /**
     *  Porter state
     *    @serialField st
     */
    private Porter.InternalState st = null;



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
     *  Instantiating a message (form 2).
     *
     *    @param type message type
     */
    public MessageCollectionPoint (int type, int passengerID, int nr)
    {
        msgType = type;
        this.passengerID = passengerID;
        this.nr = nr;
    }

    /**
     *  Instantiating a message (form 2).
     *
     *    @param type message type
     *    @param b
     */
    public MessageCollectionPoint (int type, boolean genBool)
    {
        msgType = type;
        this.genBool = genBool;
    }

    /**
     *  Instantiating a message (form 3).
     *
     *    @param type message type
     *    @param l luggage
     */
    public MessageCollectionPoint (int type, Luggage l)
    {
        msgType = type;
        this.l = l;
    }

    /**
     *  Instantiating a message (form 3).
     *
     *    @param type message type
     *    @param st porter state
     */
    public MessageCollectionPoint (int type, Porter.InternalState st)
    {
        msgType = type;
        this.st = st;
    }

    /**
     *  Instantiating a message (form 4).
     *
     *    @param type message type
     *    @param n  id or number os bags
     */
    public MessageCollectionPoint (int type, int n)
    {
        msgType = type;
        this.nr = n;
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

    public boolean getB(){
        return genBool;
    }

    public Porter.InternalState getSt(){
        return st;
    }

    public int getNR(){
        return nr;
    }

    public int getPassengerID(){
        return passengerID;
    }

    public Luggage getBag(){
        return l;
    }
}