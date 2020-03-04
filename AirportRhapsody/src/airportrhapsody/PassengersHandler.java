package airportrhapsody;

public class PassengersHandler {
    private Passenger[] p;
    private boolean full;
    private boolean empty;
    private int index;

    public PassengersHandler() {
    }

    public PassengersHandler(int n) {
        this.p = new Passenger[n];
        this.full = false;
        this.empty = true;
        this.index = 0;
    }

    public Passenger[] getP() {
        return this.p;
    }

    public void setP(Passenger[] p) {
        this.p = p;
    }

    public boolean isFull(){
        return this.full;
    }

    public boolean isEmpty(){
        return this.empty;
    }

    public void insertPassenger(Passenger x) {
        if(!this.isFull()){
            p[this.index] = x;
            this.empty = false;
            this.index++;
            if(this.index == this.p.length){
                this.full = true;
            }
        }
    }

    public Passenger removePassenger(){
        Passenger x;
        if(!this.isEmpty()){
            x = p[this.index-1];
            this.index--;
            this.full = false;
            if(this.index == 0){
                this.empty = true;
            }
            return x;
        }   
        return null;
    }

    @Override
    public String toString() {
        return "{" +
            " p='" + getP() + "'" +
            "}";
    }


}