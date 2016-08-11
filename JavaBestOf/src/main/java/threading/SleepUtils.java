package threading;

import java.util.Random;

/**
 * Utility class to hide try-catch block from interesting code.
 */
public final class SleepUtils {

    private static final int MAX_TIME_TO_SLEEP_IN_MS = 1000;

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private SleepUtils() {
    }

    /**
     * Sets the current thread into SLEEP mode for a random time period.
     */
    public  static void sleepSomeTime() {
        try {
            Thread.sleep(new Random().nextInt(MAX_TIME_TO_SLEEP_IN_MS));
        } catch (InterruptedException e) {
            // Important: The JVM resets the interrupted flag when entering the catch-block.
            // Therefore we have to set the interrupted flag for code that is executed after this catch block and relies on it.
            // For example the subsequent code might ask Thread.currentThread().isInterrupted() to detect
            // whether the thread was interrupted and if it was interrupted exit the thread.
            System.out.println("Thread " + Thread.currentThread().getName() + " was interrupted");
            Thread.currentThread().interrupt();
        }
    }

}
