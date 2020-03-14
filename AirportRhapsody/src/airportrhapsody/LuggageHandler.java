package airportrhapsody;

public class LuggageHandler {
    private Luggage[] bags;
    private int index;
    private boolean full;
    private boolean empty;

    public LuggageHandler(){
    }

    public LuggageHandler(int n) {
        this.bags = new Luggage[n];
        this.index = 0;
        this.full = false;
        this.empty = true;
    }

    public Luggage[] getNbags() {
        return this.bags;
    }

    public void setNbags(Luggage[] nbags) {
        this.bags = nbags;
    }

    public boolean isFull(){
        return this.full;
    }

    public boolean isEmpty(){
        return this.empty;
    }

    public void addLuggage(Luggage bag){
        if(!this.isFull()){
            this.bags[index] = bag;
            this.index++;
            this.empty = false;
            if(this.index == this.bags.length){
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
            return this.bags[index];
        }
        return null;
    }

    public Luggage remLuggage(int id){
        if(!this.isEmpty()){
            for (int i = 0; i < bags.length; i++) {
                if (bags[i].getOwner() == id){
                    Luggage l = bags[i];
                    for(int j = i+1; j < bags.length; j++) {
                        bags[j-1] = bags[j]; 
                    }
                    this.full = false;
                    return l;
                }
            }
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