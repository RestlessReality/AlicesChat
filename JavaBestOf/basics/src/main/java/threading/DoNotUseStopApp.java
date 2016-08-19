package threading;

import java.util.Date;

import static threading.SleepUtils.sleepSomeTime;

/**
 * Do not use stop() to stop a thread (this method is deprecated) because this can leave
 * the thread in an inconsistent state.
 *
 * Instead ask the thread politely to stop its activity with Thread.interrupt()
 * (However there is no guarantee that the Thread will do so)
 */
public class DoNotUseStopApp {

    public static void main(String[] args) {

        Thread runsForever = new Thread(() -> {
            while(true) {

                //check if Thread is interrupted
                if (Thread.currentThread().isInterrupted()) {
                    // Note: We can check the flag also with Thread.interrupted().
                    // However Thread.interrupted() has the side effect that it also deletes the interrupted flag.
                    // Therefore it is safer to call Thread.currentThread().isInterrupted()

                    System.out.println("Detected interruption. Exiting thread.");
                    break; //ends the while
                }

                System.out.println(new Date() + ": Hello from thread " + Thread.currentThread().getName() + "I'm still alive.");
                // Sends the thread into the TIMED_WAITING state.
                // Upon (nachdem) interruption the thread returns from TIMED_WAITING into RUNNING
                sleepSomeTime();
            }
        });

        // Now we start the Thread
        runsForever.start();

        try {
            // Send the main thread to sleep for a while, then interrupt the other thread.
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Now we ask the Thread to stop it's activity ( do not use stop()! )
        runsForever.interrupt();
    }
}
