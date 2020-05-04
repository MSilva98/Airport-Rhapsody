package airportrhapsody.comInf;

import java.io.Serializable;

import airportrhapsody.clientSide.BusDriver;
import airportrhapsody.clientSide.Passenger;

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

    /**
     * Success
     * 
     * @serialField ACK
     */
    public static final int ACK =  4;

    //Message arguments

    /**
     *  Message type
     *    @serialField msgType
     */

    private int msgType = -1;

    /**
     *  Passenger state
     *    @serialField st
     */
    private Passenger.InternalState pst = null;

    /**
     *  Passenger ID
     *    @serialField passengerID
     */
    private int passengerID = -1;

    /**
     *      Bis Driver state
     *    @serialField b
     */
    private BusDriver.InternalState bst = null;

    /**
     *  Passenger 
     *    @serialField p
     */
    private Passenger p = null;


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
     *  Instantiating a message (form 2).
     *
     *    @param type message type
     *    @param st Passenger state
     */
    public MessageDepTransQuay (int type, Passenger.InternalState pst)
    {
        msgType = type;
        this.pst = pst;
    }

    /**
     *  Instantiating a message (form 3).
     *
     *    @param type message type
     *    @param p Passenger
     */
    public MessageDepTransQuay (int type, Passenger p)
    {
        msgType = type;
        this.p = p;
    }

    /**
     *  Instantiating a message (form 4).
     *
     *    @param type message type
     *    @param passengerID Passenger ID
     */
    public MessageDepTransQuay (int type, int passengerID)
    {
        msgType = type;
        this.passengerID = passengerID;
    }

    /**
     *  Instantiating a message (form 5).
     *
     *    @param type message type
     *    @param st Bus Driver state
     */
    public MessageDepTransQuay (int type, BusDriver.InternalState bst)
    {
        msgType = type;
        this.bst = bst;
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

    public Passenger.InternalState getPassengerSt(){
        return pst;
    }

    public BusDriver.InternalState getBusDriverSt(){
        return bst;
    }

    public Passenger getPassenger(){
        return p;
    }
}