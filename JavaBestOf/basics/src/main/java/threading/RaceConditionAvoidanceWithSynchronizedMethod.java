package threading;

/**
 * Shows how to define a critical section using the synchronized keyword on a method.
 */
public class RaceConditionAvoidanceWithSynchronizedMethod {

    private static int sharedState = 0;
    private static final int MAX_COUNT = 1_000_000;

    private synchronized void bar() {
        this.toString(); //implizite Sperrung des Monitor-locks des this-Objektes
    }

    private void blub() {
        synchronized (this) { //explizite Sperrung des Monitor-locks des this-Objektes
            this.toString();
        }
    }

    /**
     * Runs the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        RaceConditionAvoidanceWithSynchronizedMethod m = new RaceConditionAvoidanceWithSynchronizedMethod();
        m.bar();

        Thread thread1 = incrementingThreadCallingSynchronizedMethod();
        Thread thread2 = incrementingThreadCallingSynchronizedMethod();

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Actual: " + sharedState + ", Expected: " + 4 * MAX_COUNT);
    }

    private static Thread incrementingThreadCallingSynchronizedMethod() {
        return new Thread(() -> {
            for (int i = 0; i < MAX_COUNT; ++i) {
                increment();
                foo();
            }
        }, "INCREMENTING-THREAD-WITH-SYNCHRONIZED");
    }

    /**
     * Using the synchronized keyword on a method ensures that only one thread can enter the method at any given time.
     *
     * Whenever a thread enters the method it acquires a so called monitor lock.
     * As soon as the thread leaves the method it releases the monitor lock.
     * Other threads trying to enter the method have to acquire the lock first before entering.
     * If the lock is already taken by another thread the thread trying to get the lock enters the
     * {@link Thread.State#BLOCKED} state (where it remains until the other thread leaves the
     * method and lock can be acquired).
     *
     * The monitor lock is similar to a lock object on the "this" reference (for non-static methods)
     * or on the ".class" reference for static methods.
     * For example the block below could be also written as:
     *
     * private static void increment() {
     *     synchronized(RaceConditionAvoidanceWithSynchronizedMethod.class) {
     *         sharedState++;
     *     }
     * }
     */

    // Variante 1
    private static synchronized void increment() {
        sharedState++;
    }

    // Variante 2
    // Tut das gleiche, feiner steuerbar, da nicht ganze Methode synchronized
    private static void foo() {
        synchronized (RaceConditionAvoidanceWithSynchronizedMethod.class) {
            sharedState++;
        }
    }

    private static int plus(int a, int b) {
        return a + b;
    }
}
