package threading;

import java.util.Date;
import java.util.concurrent.Callable;

import static threading.SleepUtils.sleepSomeTime;

/**
 * This is an anti-example.
 * You should not derive from {@link Thread},
 * better implement {@link Runnable} or {@link Callable} instead.
 *
 * @see StartThreadWithRunnableApp
 * @see StartThreadWithCallableApp
 */
public class StartThreadWithDerivedClassApp {

    private static final int NUMBER_OF_MESSAGES_FROM_THREAD = 5;

    /**
     * Runs the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        Thread thread = new DerivedFromThread("DERIVED-FROM-THREAD-CLASS");

        // .start() starts a separate thread
        // important: do not call .run() instead of .start() !
        // Then .run() would simply call the method of the Thread synchronously
        thread.start();
    }

    private static class DerivedFromThread extends Thread {

        private DerivedFromThread(String name) {
            super(name);
        }

        /**
         * The run-method determines the lifetime of the thread.
         */
        @Override
        public void run() {
            for (int i = 0; i < NUMBER_OF_MESSAGES_FROM_THREAD; ++i) {
                System.out.println(new Date() + ": Hello from thread " + Thread.currentThread().getName());
                sleepSomeTime();
            }
        }
    }
}
