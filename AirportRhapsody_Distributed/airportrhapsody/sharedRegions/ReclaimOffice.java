package airportrhapsody.sharedRegions;

import airportrhapsody.entities.Passenger;

/**
 * Baggage reclaim office
 */
public class ReclaimOffice {
    /**
     * Number of reclaims
     * 
     * @serialField reclaims
     */
    private int reclaims;
    /**
     * number of bags missing
     * 
     * @serialField numBagsMissing
     */
    private int numBagsMissing;
    /**
     *  Logger - general repository of information
     * 
     *  @serialField generalRepo
     */
    private Logger generalRepo;
    /**
     * Instantiating the baggage reclaim office.
     * @param generalRepo
     */
    public ReclaimOffice(Logger generalRepo) {
        this.generalRepo = generalRepo;
        this.numBagsMissing = 0;
    }
    public ReclaimOffice(int reclaims, Logger generalRepo) {
        this.reclaims = reclaims;
        this.generalRepo = generalRepo;
    }
    /**
     * Report missing bags
     * @param numBags number of bags missing
     * @param p passenger
     */
    public Passenger.InternalState reportMissingBags(int numBags) {
        synchronized(this){
            this.numBagsMissing += numBags;
        }
        return Passenger.InternalState.AT_THE_BAGGAGE_RECLAIM_OFFICE;
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