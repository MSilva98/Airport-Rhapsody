package airportrhapsody.serverSide;

public class ArrivalLoungeInterface {
    /**
     * Arrival lounge
     * 
     * @serialField arrivalLounge
     */
    private ArrivalLounge arrivalLounge;

    /**
     *  Instantiating the arrival lounge interface
     *  @param arrivalLounge Arrival Lounge
     */
    public ArrivalLoungeInterface(ArrivalLounge arrivalLounge){
        this.arrivalLounge = arrivalLounge;
    }

}