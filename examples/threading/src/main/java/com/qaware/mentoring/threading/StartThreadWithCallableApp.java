package com.qaware.mentoring.threading;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static com.qaware.mentoring.threading.SleepUtils.sleepSomeTime;

/**
 * Demonstrates how to start a thread using the {@link java.util.concurrent.Callable} interface
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

        // In Java 8 you can collapse the anonymous class into a Lambda.
        // IntelliJ will help you to do so with Alt + Enter if you have placed the caret on the constructor call.
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
