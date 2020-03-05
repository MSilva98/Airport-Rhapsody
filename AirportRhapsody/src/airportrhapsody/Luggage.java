package airportrhapsody;

/**
 * Luggage
 */
public class Luggage {
    private int bagID;
    private int owner;

    public Luggage() {
    }

    public Luggage(int bagID, int owner) {
        this.bagID = bagID;
        this.owner = owner;
    }

    public int getBagID() {
        return this.bagID;
    }

    public void setBagID(int bagID) {
        this.bagID = bagID;
    }

    public int getOwner() {
        return this.owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "{" +
            " bagID='" + getBagID() + "'" +
            ", owner='" + getOwner() + "'" +
            "}";
    }

}