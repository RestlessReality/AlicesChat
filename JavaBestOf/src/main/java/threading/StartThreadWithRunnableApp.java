package threading;

import java.util.Date;

import static threading.SleepUtils.sleepSomeTime;
/**
 * Demonstrates how to start a thread using the {@link Runnable} interface.
 * This is the older interface.
 */
public class StartThreadWithRunnableApp {

    public static final int NUMBER_OF_MESSAGES_FROM_THREAD = 5;

    /**
     * Runs the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        // Anonymous class. Runnable is the interface-name. Then override the methods.
        //
        // In Java 8 you can collapse the anonymous class into a Lambda.
        // IntelliJ will help you to do so with Alt + Enter if you have placed the caret on the constructor call.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < NUMBER_OF_MESSAGES_FROM_THREAD; ++i) {
                    System.out.println(new Date() + ": Hello from thread " + Thread.currentThread().getName());
                    sleepSomeTime();
                }
            }
        };

        // Wraps the Runnable into a Thread object.
        Thread thread = new Thread(runnable, "With-Runnable-Inside");

        // Starts a separate thread - important: do not call .run() instead of .start()
        // because .run() would simply call the method of the Runnable in the same thread
        thread.start();
    }


}
