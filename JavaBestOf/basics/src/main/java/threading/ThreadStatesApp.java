package threading;

import java.util.Date;

import static threading.SleepUtils.sleepSomeTime;
/**
 * A thread can have the following states:
 *
 * - NEW                bevore call of .start() - not started yet
 * - RUNNABLE           started, running
 * - BLOCKED            waits for a lock, e.g. when entering a synchronized block
 * - WAITING            waiting, e.g. for notify()
 * - TIMED_WAITING      waiting, e.g. for sleep()
 * - TERMINATED         terminated, thread can not be started again
 *
 * This program starts two thread:
 *
 * - A thread named GREETING-THREAD which simply prints out on the console its name and its state periodically.
 * - A thread named WATCHING-THREAD which prints out periodically the state of the GREETING-THREAD.
 *
 * We will see that the WATCHING-THREAD will see the GREETING-THREAD most of the time in state TIMED_WAITING.
 * The reason for this is that the GREETING-THREAD is calling Thread.sleep(...) periodically which puts it into
 * the TIMED_WAITING state. Only when it is not sleeping it is RUNNING but that is a really short period so the
 * WATCHING-THREAD will almost certainly see the GREETING-THREAD in state TIMED_WAITING all the time.
 */
public class ThreadStatesApp {

    private static final int NUMBER_OF_MESSAGES_FROM_THREAD = 10;
    private static final int NUMBER_OF_TIMES_TO_WATCH = 10;

    //todo SKR is quite shure the latch is a race condition, therefore delete it.
    //but a latch might be useful sometimes. hang on.
//    private static final CountDownLatch latch = new CountDownLatch(1);

    /**
     * Runs the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        final Thread greetingThread = new Thread(new Runnable() {
            private int a = 0; // The Runnable can have fields. That's why it's only startable once.
            @Override
            public void run() {
                for (int i = 0; i < NUMBER_OF_MESSAGES_FROM_THREAD; ++i) {
                    System.out.println(new Date() + ": Hello from thread " + Thread.currentThread().getName() +
                            " -> " + Thread.currentThread().getState());
                    a++;
                    // The following statement puts the thread from RUNNING into state TIMED_WAITING
                    // However we can not print the state when this thread is waiting, therefore another thread,
                    // the WATCHING-THREAD, watches us and prints out our state.
                    sleepSomeTime();
                }
//                // The latch is used at the end of the main() method in order to wait for this thread to terminate.
//                latch.countDown();
            }
        }, "GREETING-THREAD");
        System.out.println("Thread state before .start() -> " + greetingThread.getState());
        greetingThread.start(); // The Runnable can have fields. That's why it's only startable once.

        final Thread watchingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < NUMBER_OF_TIMES_TO_WATCH; ++i) {
                    System.out.println(new Date() + ": The " + Thread.currentThread().getName() +
                            " sees that the " + greetingThread.getName() + " is in state -> " + greetingThread.getState());
                    sleepSomeTime();
                }
            }
        }, "WATCHING-THREAD");
        watchingThread.start();

        try {
            // We wait here until the greetingThread has finished, i.e. has count down the latch.
//            latch.await();
            greetingThread.join(); //waits for the end of greetingThread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //The thread still provides the state Terminated if finished/dead.
        System.out.println("Thread state after termination -> " + greetingThread.getState());

    }

}
