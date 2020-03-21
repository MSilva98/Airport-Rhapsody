package airportrhapsody.sharedRegions;

/**
 * Barrier
 */
public class Barrier {

    private int counter;
    private Semaphore[] s;
    
    public Barrier(int capacity) {
        counter = 0;
        s = new Semaphore[capacity-1];
        for (int i = 0; i < s.length; i++) {
            s[i]= new Semaphore();
        }
    }

    public synchronized void down() {
        if( counter < s.length){
            s[counter].down();
            counter ++;
        }else{
            for (int i = 0; i < s.length; i++) {
                s[i].up();
            }
            counter = 0;
        }
    }
}