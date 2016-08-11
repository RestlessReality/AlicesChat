package threading;

/**
 * Example for a race condition between threads.
 * <p>
 * Whenever you share state between multiple threads you should
 * synchronize the access on this shared state via a critical section.
 * <p>
 * You can implement critical sections using
 * <p>
 * - synchronized on methods
 * - synchronized on lock objects
 */
public class RaceConditionDemoApp {

    private static int sharedState = 0;
    private static final int MAX_COUNT = 1_000_000;

    /**
     * Runs the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        Thread thread1 = unsafeIncrementingThread();
        Thread thread2 = unsafeIncrementingThread();

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Actual: " + sharedState + ", Expected: " + 2 * MAX_COUNT);
    }

    private static Thread unsafeIncrementingThread() {
        return new Thread(() -> {
            for (int i = 0; i < MAX_COUNT; ++i) {
                // Race condition happens here:
                // The statement sharedState++; is actually two operations: a read and a write on the variable.
                // If another thread happens to modify the counter in between, the write operation
                // will override the write operation of the other thread.
                // Whenever this happens, one increment is lost.
                sharedState++;
            }
        });
    }
}
