package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.Passenger;

public class PassengersHandler {
    private Passenger[] p;
    private boolean full;
    private boolean empty;
    private int index;
    private int max;

    public PassengersHandler() {
    }

    public PassengersHandler(int n) {
        this.p = new Passenger[n];
        this.full = false;
        this.empty = true;
        this.index = 0;
        this.max = n;
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

    public boolean insertPassenger(Passenger x) {
        if(!this.isFull()){
            p[this.index] = x;
            this.empty = false;
            this.index++;
            if(this.index == this.p.length){
                this.full = true;
            }
            return true;
        }
        return false;
    }

    public Passenger removePassenger(){
        if(!this.isEmpty()){
            this.index--;
            this.full = false;
            if(this.index == -1){
                this.empty = true;
                this.index = 0;
                return null;
            }
            return this.p[this.index];
        }   
        return null;
    }

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
                        this.full = false;
                        this.index--;
                        return l;
                    }
                }
            }
        }   
        return null;
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