package airportrhapsody;

public class BusDriver {
    enum InternalState {
        PARKING_AT_THE_ARRIVAL_TERMINAL,
        DRIVING_FORWARD,
        PARKING_AT_THE_DEPARTURE_TERMINAL,
        DRIVING_BACKWARD
    }

    private InternalState state;
    private String[] seats; // occupation state for seat in the bus (passenger id / - (empty))

    private void goToDepartureTerminal() {
        
    }

    private void goToArrivalTerminal() {
        
    }


    private void hasDaysWorkEnded() {
        
    }

    private void announcingBusBoarding() {
        
    }


    public BusDriver() {
    }

    public BusDriver(int numOfSeats) {
        this.state = InternalState.PARKING_AT_THE_ARRIVAL_TERMINAL;
        this.seats = new String[numOfSeats];
    }

    public InternalState getState() {
        return this.state;
    }

    public void setState(InternalState state) {
        this.state = state;
    }

    public String[] getSeats() {
        return this.seats;
    }

    public void setSeats(String[] seats) {
        this.seats = seats;
    }

}
