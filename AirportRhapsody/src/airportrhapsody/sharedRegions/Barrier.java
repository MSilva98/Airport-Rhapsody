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

    public  void down() {
        System.out.println("Barrier: down");
        if( counter < s.length){
            synchronized(this){
                System.out.println("Barrier: down -> counter = " + counter + " s_length = " + s.length);
                counter ++;
            }

            s[counter-1].down();

        }else{
            for (int i = 0; i < s.length; i++) {
                s[i].up();
            }
            counter = 0;
        }
    }
}