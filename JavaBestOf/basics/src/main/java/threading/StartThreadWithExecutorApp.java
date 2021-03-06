package threading;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static threading.SleepUtils.sleepSomeTime;

/**
 * Demonstrates how to start threads using an {@link ExecutorService}.
 *
 * The executor service usually uses a thread pool of pre-created threads, in order to avoid
 * the expensive creation of new threads.
 *
 * If the number of submitted Runnables is larger than the pool-size, the remaining
 * Runnables are started, once the first Runnables are finished
 */
public class StartThreadWithExecutorApp {

    private static final int NUMBER_OF_MESSAGES_FROM_EACH_THREAD = 10;
    private static final int NUMBER_OF_THREADS = 10;
    private static final int THREAD_POOL_SIZE = 5;

    /**
     * Runs the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        // Creates a thread pool with fixed size, i.e. {@link THREAD_POOL_SIZE} can be run in parallel by this executor.
        // There are multiple implementations of {@link ExecutorService}s, e.g. ThreadPoolExecutor, ScheduledThreadPoolExecutor
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        // Starts the threads one after another
        for (int i = 0; i < NUMBER_OF_THREADS; ++i) {
            executorService.execute(newRunnable(i));
        }

        // Shuts down the executor service properly
        executorService.shutdown();
    }

    private static Runnable newRunnable(int nr) {
        return () -> {
            String load = "*";
            for (int i = 0; i < NUMBER_OF_MESSAGES_FROM_EACH_THREAD; ++i) {
                System.out.println(new Date() + ": Hello from thread " + Thread.currentThread().getName() + " and Runnable " + nr + " load " + load);
                load += "*";
                sleepSomeTime();
            }
            System.out.println(new Date() + ": Thread " + Thread.currentThread().getName() + " finishes!");
    };
    }
}
