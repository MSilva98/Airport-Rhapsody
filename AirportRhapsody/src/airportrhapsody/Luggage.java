package airportrhapsody;

import airportrhapsody.Passenger.Situation;;

/**
* Luggage
 */
public class Luggage {
    private static int bagID = 0;
    private int owner;
    private Situation si; 

    public Luggage() {
    }

    public Luggage(int owner, Situation si) {
        this.bagID++;
        this.owner = owner;
        this.si = si;
    }

    public static int getBagID() {
        return bagID;
    }

    public int getOwner() {
        return this.owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public Situation getSi(){
        return this.si;
    }

    public void setSi(Situation si){
        this.si = si;
    }

    @Override
    public String toString() {
        return "{" +
            " bagID='" + getBagID() + "'" +
            ", owner='" + getOwner() + "'" +
            "}";
    }

}