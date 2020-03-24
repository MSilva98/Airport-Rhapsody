package airportrhapsody.sharedRegions;

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

    public int size(){
        return this.index;
    }

    public boolean addLuggage(Luggage bag){
        if(!this.isFull()){
            this.bags[index] = bag;
            this.empty = false;
            this.index++;
            if(this.index == this.bags.length){
                this.full = true;
            }
            return true;
        }
        return false;
    }

    public Luggage remLuggage(){
        if(!this.isEmpty()){
            this.index--;
            this.full = false;
            if(this.index == -1){
                this.empty = true;
                this.index = 0;
                return null;
            }
            return this.bags[this.index];
        }
        return null;
    }

    public Luggage remLuggage(int id){
        if(!this.isEmpty()){
            for (int i = 0; i < bags.length; i++) {
                System.out.println("bags size " + bags.length + " i=" + i);
                if (bags[i].getOwner() == id){
                    Luggage l = bags[i];
                    for(int j = i+1; j < bags.length; j++) {
                        bags[j-1] = bags[j]; 
                        bags[j] = null;
                    }
                    this.full = false;
                    this.index--;
                    return l;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.bags.length; i++) {
            s += "\n" + bags[i];
        }
        return s;
    }

    
}