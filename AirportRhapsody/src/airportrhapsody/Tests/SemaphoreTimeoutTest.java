package airportrhapsody.Tests;

import airportrhapsody.commonInfrastructures.Semaphore;

/**
 * SemaphoreTimeoutTest
 */
public class SemaphoreTimeoutTest extends Thread {

    private Semaphore test = new Semaphore();

    @Override
    public void run(){
        System.out.println("Thread initialized");
        test.down(5000);
        test.up();
        System.out.println("Stoped 5 seconds");
    }
}

