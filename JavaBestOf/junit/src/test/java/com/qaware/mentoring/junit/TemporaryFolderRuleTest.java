package com.qaware.mentoring.junit;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Same effect as {@link TemporaryFolderWithoutRuleTest} but with @Rule from JUnit.
 * Advantage: No need to implement @Before and @After in the test class itself.
 * Testing code becomes clearer.
 */
public class TemporaryFolderRuleTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void test() throws Exception {
        File file = folder.newFile("test.txt");
        assertThat(file.exists(), is(true));
    }
}
