package airportrhapsody.sharedRegions;

/**
 * ReclaimOffice
 */
public class ReclaimOffice {

    private int reclaims;
    private int numBagsMissing;

    public ReclaimOffice() {
        numBagsMissing = 0;
    }

    public ReclaimOffice(int reclaims) {
        this.reclaims = reclaims;
    }

    public void reportMissingBags(int numBags) {
        numBagsMissing++;
    }

    public int getNumBagsMissing() {
        return this.numBagsMissing;
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