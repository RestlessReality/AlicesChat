package threading;

import java.util.Date;

import static threading.SleepUtils.sleepSomeTime;
/**
 * Do not use stop() to stop a thread (this method is deprecated) because this can leave
 * the thread in an inconsistent state.
 *
 * Instead ask the thread politely to stop its activity.
 * (However there is no guarantee that the Thread will do so)
 */
public class DoNotUseStopApp {

    /**
     * Runs the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        Thread runsForever = new Thread(() -> {
            while(true) {
                // Note: We can check the flag also with Thread.interrupted().
                // However Thread.interrupted() has the side effect that it also deletes the interrupted flag.
                // Therefore it is safer to call Thread.currentThread().isInterrupted()
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Detected interruption. Exiting thread.");
                    // Escapes the while loop
                    break;
                }
                System.out.println(new Date() + ": Hello from thread " + Thread.currentThread().getName());
                // Sends the thread into the TIMED_WAITING state.
                // Upon interruption the thread returns from TIMED_WAITING into RUNNING
                sleepSomeTime();
            }
        });
        runsForever.start();

        try {
            // Send the main thread to sleep for a while, then interrupt the other thread.
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Not recommended:
        // runsForever.stop();

        // Instead call interrupt()
        runsForever.interrupt();
    }
}
