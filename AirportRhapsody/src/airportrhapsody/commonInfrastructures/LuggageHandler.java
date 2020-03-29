package airportrhapsody.commonInfrastructures;

/**
 * Luggage handler
 */

public class LuggageHandler {
    /**
     * Array of bags
     */
    private Luggage[] bags;
    /**
     * index
     */
    private int index;
    /**
     * Indicate if is full
     */
    private boolean full;
    /**
     * Indicate if is empty
     */
    private boolean empty;

    public LuggageHandler(){
    }
    /**
     * Lugagge handler constructor
     * @param n size of the handler
     */
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
    /**
     * Check if the luggage handler is full
     * @return <li> true if is full
     *         <li> else false
     */
    public boolean isFull(){
        return this.full;
    }
    /**
     * Check if the luggage handler is empty
     * @return <li> true if is empty
     *         <li> else false
     */
    public boolean isEmpty(){
        return this.empty;
    }
    /**
     * Current number of bags
     * @return number of bags
     */
    public int size(){
        return this.index;
    }
    /**
     * Add a bag to the handler
     * @param bag bag
     * @return <li> true is add bag with success
     *         <li> false if fail
     */
    public boolean addLuggage(Luggage bag){
        if(!this.isFull()){
            this.bags[index] = bag;
            this.index++;
            this.empty = this.size() == 0;
            this.full = this.size() == bags.length;
            return true;
        }
        return false;
    }
    /**
     * Remove a bag
     * @return bag
     */
    public Luggage remLuggage(){
        if(!this.isEmpty()){
            this.index--;
            this.full = this.size() == bags.length;
            this.empty = this.size() == 0;
            return this.bags[this.index];
        }
        return null;
    }
    /**
     * Remove a bag
     * @param id passenger id
     * @return bag
     */
    public Luggage remLuggage(int id){
        if(!this.isEmpty()){
            for (int i = 0; i < bags.length; i++) {
                if(bags[i] != null){
                    if (bags[i].getOwner() == id){
                        Luggage l = bags[i];
                        for(int j = i+1; j < bags.length; j++) {
                            bags[j-1] = bags[j]; 
                            bags[j] = null;
                        }
                        this.index--;
                        this.full = this.size() == bags.length;
                        this.empty = this.size() == 0;
                        return l;
                    }
                }
                else{
                    return null;
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