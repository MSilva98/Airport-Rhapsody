package airportrhapsody;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Deadlock
 */
public class Deadlock extends Thread {

    public Deadlock() {
    }

    @Override
    public void run() {
        System.out.println("DEADLOCK DETECTION THREAD");
        // while (true) {
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.detectDeadlock();
        // }
    }

    private static void detectDeadlock(){
        ThreadMXBean threadbean = ManagementFactory.getThreadMXBean();
        long[] threadIDs = threadbean.findDeadlockedThreads();
        long[] threads = threadbean.getAllThreadIds();
        boolean deadlock = threadIDs != null && threadIDs.length > 0;
        System.out.println("Deadlocks: " + deadlock + " deadlock threads " + threadIDs);
        ThreadInfo[] t = threadbean.getThreadInfo(threads);
        String s = "";
        for (int i = 0; i < t.length; i++) {
            s += t[i].toString();
        } 
        System.out.println("All threads: " + s);
    }    
}