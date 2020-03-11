package airportrhapsody;

public class LuggageHandler {
    private Luggage[] nbags;
    private int index;
    private boolean full;
    private boolean empty;

    public LuggageHandler(){
    }

    public LuggageHandler(int n) {
        this.nbags = new Luggage[n];
        this.index = 0;
        this.full = false;
        this.empty = true;
    }

    public Luggage[] getNbags() {
        return this.nbags;
    }

    public void setNbags(Luggage[] nbags) {
        this.nbags = nbags;
    }

    public boolean isFull(){
        return this.full;
    }

    public boolean isEmpty(){
        return this.empty;
    }

    public void addLuggage(Luggage bag){
        if(!this.isFull()){
            this.nbags[index] = bag;
            this.index++;
            this.empty = false;
            if(this.index == this.nbags.length){
                this.full = true;
            }
        }
    }

    public Luggage remLuggage(){
        if(!this.isEmpty()){
            this.index--;
            this.full = false;
            if(this.index == 0){
                this.empty = true;
                return null;
            }
            return this.nbags[index];
        }
        return null;
    }

    @Override
    public String toString() {
        return "{" +
            " nbags='" + getNbags() + "'" + 
            "}";
    }

    
}