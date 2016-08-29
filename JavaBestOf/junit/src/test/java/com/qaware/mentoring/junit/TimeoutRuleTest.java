package com.qaware.mentoring.junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Example how to use the Timeout Rule provided by JUnit.
 */
public class TimeoutRuleTest {

    // Timeout each test after 50 milliseconds
    @Rule
    public Timeout timeout = new Timeout(50);

    @Test
    public void firstTest() throws Exception {
        Thread.sleep(300);
    }

    @Test
    public void secondTest() {
        for (; ; ) {
        }
    }

}
