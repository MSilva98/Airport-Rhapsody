package airportrhapsody.Tests;

/**
 * SemTest
 */
public class SemTest {

    public static void main(String[] args) {
        SemaphoreTimeoutTest t = new SemaphoreTimeoutTest();
        SemaphoreTimeoutTest t2 = new SemaphoreTimeoutTest();
        t.start();
        t2.start();
    }
}