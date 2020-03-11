package airportrhapsody;

public class BusDriver extends Thread{
    enum InternalState {
        PARKING_AT_THE_ARRIVAL_TERMINAL,
        DRIVING_FORWARD,
        PARKING_AT_THE_DEPARTURE_TERMINAL,
        DRIVING_BACKWARD
    }
    private int busDriverID;
    private ArrTransQuay arrTransQuay;
    private DepTransQuay depTransQuay;
    private InternalState busDriverState;
    private String[] seats; // occupation state for seat in the bus (passenger id / - (empty))

    @Override
    public void run(){
        System.out.println("Thread BusDriver");
    }

    private void goToDepartureTerminal() {
        
    }

    private void goToArrivalTerminal() {
        
    }


    private void hasDaysWorkEnded() {
        
    }

    private void announcingBusBoarding() {
        
    }


    public BusDriver() {
        this.busDriverState = InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL;
    }

    public BusDriver(int id, int numOfSeats, ArrTransQuay arrTransQuay, DepTransQuay depTransQuay ) {
        this.busDriverState = InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL;
        this.seats = new String[numOfSeats];
        this.arrTransQuay = arrTransQuay;
        this.depTransQuay = depTransQuay;
    }

    public InternalState getBusDriverState() {
        return this.busDriverState;
    }

    public void setBusDriverState(InternalState state) {
        this.busDriverState = state;
    }

    public String[] getSeats() {
        return this.seats;
    }

    public void setSeats(String[] seats) {
        this.seats = seats;
    }

}
