package airportrhapsody.comInf;

import airportrhapsody.clientSide.Passenger.Situation;

/**
* Luggage
 */
public class Luggage {
    /**
     * Luggage id counter
     */
    private static int idCount = 0;
    /**
     * Luggage identification
     */
    private int bagID;
    private int owner;
    private Situation si; 

    public Luggage() {
    }
    /**
     * Luggage construtor .
     * @param owner owner id
     */
    public Luggage(int owner){
        this.owner = owner;
        this.bagID = idCount++;
    }
    /**
     * Luggage construtor .
     * @param owner owner id
     * @param si situation of owner
     */
    public Luggage(int owner, Situation si) {
        this.bagID = idCount++;
        this.owner = owner;
        this.si = si;
    }

    public int getBagID() {
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