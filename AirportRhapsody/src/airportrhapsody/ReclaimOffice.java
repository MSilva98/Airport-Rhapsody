package airportrhapsody;

/**
 * ReclaimOffice
 */
public class ReclaimOffice {

    private int reclaims;

    public ReclaimOffice() {
    }

    public ReclaimOffice(int reclaims) {
        this.reclaims = reclaims;
    }

    public int getReclaims() {
        return this.reclaims;
    }

    public void setReclaims(int reclaims) {
        this.reclaims = reclaims;
    }

    @Override
    public String toString() {
        return "reclaims = " + getReclaims();
    }

}