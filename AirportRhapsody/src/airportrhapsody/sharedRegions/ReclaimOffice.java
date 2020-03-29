package airportrhapsody.sharedRegions;

import airportrhapsody.Logger;
import airportrhapsody.entities.Passenger;

/**
 * ReclaimOffice
 */
public class ReclaimOffice {

    private int reclaims;
    private int numBagsMissing;
    private Logger generalRepo;

    public ReclaimOffice(Logger generalRepo) {
        this.generalRepo = generalRepo;
        this.numBagsMissing = 0;
    }

    public ReclaimOffice(int reclaims, Logger generalRepo) {
        this.reclaims = reclaims;
        this.generalRepo = generalRepo;
    }

    public void reportMissingBags(int numBags, Passenger p) {
        p.setPassengerState(Passenger.InternalState.AT_THE_BAGGAGE_RECLAIM_OFFICE);
        this.generalRepo.setSt(p.getPassengerID(), "BRO");
        this.generalRepo.write(false);
        this.numBagsMissing += numBags;
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