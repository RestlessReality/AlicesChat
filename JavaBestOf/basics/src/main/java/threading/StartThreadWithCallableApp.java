package threading;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static threading.SleepUtils.sleepSomeTime;
/**
 * Demonstrates how to start a thread using the {@link Callable} interface
 * Newer interface than Runnable
 */
public class StartThreadWithCallableApp {

    private static final int FIBONACCI_NUMBER_TO_BE_CALCULATED = 10;

    /**
     * Runs the program.
     * The program calculate the Nth fibonacci number were N is {@link #FIBONACCI_NUMBER_TO_BE_CALCULATED}.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        // Anonymous thread-class of the Callable-Interface, capable of providing a return-value after execution.
        // Collapse the anonymous class into a Lambda: Alt+Enter
        Callable<Integer> fibonacciCallable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Integer fibonacci = 1;
                Integer first = 0;
                Integer second = 1;
                for (int i = 0; i < FIBONACCI_NUMBER_TO_BE_CALCULATED; ++i) {
                    System.out.println(new Date() + ": Hello from thread " + Thread.currentThread().getName() + " -> " + fibonacci);
                    fibonacci = first + second;
                    first = second;
                    second = fibonacci;
                    sleepSomeTime();
                }
                fibonacci = first;
                // When we get here and return the result the Thread will terminate
                return fibonacci;
            }
        };

        // "double-wrapping" of the Thread-call, because only a "Task" ran obtain a return value
        FutureTask<Integer> fibonacciTask = new FutureTask<>(fibonacciCallable);
        Thread thread = new Thread(fibonacciTask, "With-Callable-Inside");
        // Starts a separate thread - important: do not call .run() instead of .start()
        // because .run() would simply call the method of the Callable in the same thread
        thread.start();

        try {
            // When calling .get() on a FutureTask the current Thread waits until the
            // thread of the Callable has finished and returned the result.
            Integer fibonacciResult = fibonacciTask.get();
            System.out.println("The result is " + fibonacciResult);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
