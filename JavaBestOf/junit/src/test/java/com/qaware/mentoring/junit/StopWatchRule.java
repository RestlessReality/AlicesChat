package com.qaware.mentoring.junit;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * Custom test rule that takes the time required for each test.
 */
public class StopWatchRule extends TestWatcher {

    private static final long NANOSECONDS_PER_MILLISECOND = 1000 * 1000;
    private long begin = 0;
    private long end = 0;

    /**
     * Called before the test method is called.
     *
     * @param description the test description
     */
    protected void starting(Description description) {
        System.out.println("=========================================================");
        System.out.println("Starting " + testLabel(description));
        begin = System.nanoTime();
    }

    /**
     * Called after the test method has finished.
     *
     * @param description the description.
     */
    protected void finished(Description description) {
        end = System.nanoTime();
        System.out.println("Finished " + testLabel(description) + " in " + elapsed() + " ms");
        System.out.println("=========================================================");
    }

    private String testLabel(Description description) {
        return description.getTestClass().getSimpleName() + "." + description.getMethodName() + "()";
    }

    /* Note: Not accurate because of Integer division but OK for this example. */
    private long elapsed() {
        return (end - begin) / NANOSECONDS_PER_MILLISECOND;
    }
}
