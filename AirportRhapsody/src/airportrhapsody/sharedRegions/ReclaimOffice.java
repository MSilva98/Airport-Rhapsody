package airportrhapsody.sharedRegions;

import airportrhapsody.mainProgram.Passenger;

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

    public void reportMissingBags(int numBags, Passenger p) {
        p.setPassengerState(Passenger.InternalState.AT_THE_BAGGAGE_RECLAIM_OFFICE);
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