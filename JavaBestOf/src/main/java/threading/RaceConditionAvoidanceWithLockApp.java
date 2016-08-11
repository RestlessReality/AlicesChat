package threading;

/**
 * Example for how a race condition can be avoided using a
 * synchronized block and a lock object.
 */
public class RaceConditionAvoidanceWithLockApp {

    private static int sharedState = 0;
    private static final int MAX_COUNT = 1_000_000;

    // In general the LOCK could be any final object reference which is visible to threads to be synchronized.
    // For instance you also could use the static RaceConditionAvoidanceWithLockApp.class object.
    // However it is often more readable and easier to understand if you use a separate Object for it.
    private static final Object LOCK = new Object();

    /**
     * Runs the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        Thread thread1 = incrementingThreadWithLock();
        Thread thread2 = incrementingThreadWithLock();

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

    private static Thread incrementingThreadWithLock() {
        return new Thread(() -> {
            for (int i = 0; i < MAX_COUNT; ++i) {
                // Both threads synchronize on the same LOCK object
                synchronized (LOCK) {
                    // Because of the synchronized block only one thread is allowed to enter this section.
                    // The read and the write operation is therefore "atomic".
                    // Disclaimer: In this example we assume that no other threads are reading/writing
                    // on the sharedState variable.
                    sharedState++;
                }
            }
        }, "INCREMENTING-THREAD-WITH-LOCK");
    }
}
