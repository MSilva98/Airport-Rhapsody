package airportrhapsody;

public class LuggageHandler {
    private int nbags;
    private int finalBags;
    private int otherBags;

    public LuggageHandler(){
    }

    public LuggageHandler(int nbags, int finalBags, int otherBags) {
        this.nbags = nbags;
        this.finalBags = finalBags;
        this.otherBags = otherBags;
    }

    public int getNbags() {
        return this.nbags;
    }

    public void setNbags(int nbags) {
        this.nbags = nbags;
    }

    public int getFinalBags() {
        return this.finalBags;
    }

    public void setFinalBags(int finalBags) {
        this.finalBags = finalBags;
    }

    public int getOtherBags() {
        return this.otherBags;
    }

    public void setOtherBags(int otherBags) {
        this.otherBags = otherBags;
    }

    @Override
    public String toString() {
        return "{" +
            " nbags='" + getNbags() + "'" +
            ", finalBags='" + getFinalBags() + "'" +
            ", otherBags='" + getOtherBags() + "'" +
            "}";
    }

    
}