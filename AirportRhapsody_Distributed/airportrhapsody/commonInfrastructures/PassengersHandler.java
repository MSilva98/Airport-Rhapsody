package airportrhapsody.commonInfrastructures;

import airportrhapsody.entities.Passenger;

/**
 * Passengers handler
 */
public class PassengersHandler {
    /**
     * Array of passengers
     */
    private Passenger[] p;
    /**
     * Indicate if is full
     */
    private boolean full;
    /**
     * Indicate if is empty
     */
    private boolean empty;
    /**
     * index
     */
    private int index;
    /**
     * max number of passengers
     */
    private int max;

    public PassengersHandler() {
    }
    /**
     * Passenger handler constructor
     * @param n size of the handler
     */
    public PassengersHandler(int n) {
        this.p = new Passenger[n];
        this.full = false;
        this.empty = true;
        this.index = 0;
        this.max = n;
    }

    public int[] getIDs(){
        int[] ids = new int[p.length];
        for (int i = 0; i < p.length; i++) {
            if(p[i] != null){
                ids[i] = p[i].getPassengerID();
            }
            else{
                ids[i] = -1;
            }
        }
        return ids;
    }

    public int maxSize() {
        return max;
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

    public int size(){
        return index;
    }
    /**
     * Insert passenger in handler
     * @param x passenger
     * @return <li> true if insert with success
     *         <li> else false
     */
    public boolean insertPassenger(Passenger x) {
        if(!this.isFull()){
            p[this.index] = x;
            this.index++;
            this.empty = this.size() == 0;
            this.full = this.size() == p.length;
            return true;
        }
        return false;
    }
    /**
     * Remove passenger from handler
     * @return passenger
     */
    public Passenger removePassenger(){
        if(!this.isEmpty()){
            this.index--;
            this.full = this.size() == p.length;
            this.empty = this.size() == 0;
            Passenger x = this.p[this.index];
            this.p[this.index] = null;
            return x;
        }   
        return null;
    }
    /**
     * Remove passenger from handler
     * @param id passenger id
     * @return passenger
     */
    public Passenger removePassenger(int id){
        if(!this.isEmpty()){
            for (int i = 0; i < p.length; i++) {
                // System.out.println("p size " + p.length + " i=" + i);
                if(p[i] != null){
                    if (p[i].getPassengerID() == id){
                        Passenger l = p[i];
                        for(int j = i+1; j < p.length; j++) {
                            p[j-1] = p[j]; 
                            p[j] = null;
                        }
                        this.index--;
                        this.full = this.size() == p.length;
                        this.empty = this.size() == 0;
                        return l;
                    }
                }
            }
        }   
        return null;
    }
    /**
     * Remove all passengers
     */
    public void removeAll(){
        this.p = new Passenger[p.length];
        this.full = false;
        this.empty = true;
        this.index = 0;
    }

    public PassengersHandler copyTo(Passenger[] pass){
        PassengersHandler t = new PassengersHandler(pass.length);
        for (int i = 0; i < pass.length; i++) {
            t.insertPassenger(pass[i]);
        }
        return t;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.p.length; i++) {
            s += "\n" + p[i];
        }
        return s;
    }

}