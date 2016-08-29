package com.qaware.mentoring.junit;

import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Example for using the {@link StopWatchRule}.
 */
public class StopWatchRuleTest {

    @Rule
    public StopWatchRule stopWatchRule = new StopWatchRule();

    @Test
    public void shouldPass() throws Exception {
        Thread.sleep(300);
        assertThat(42, is(42));
    }

    @Test
    public void shouldFail() throws Exception {
        Thread.sleep(300);
        assertThat(42, is(43));
    }

}
