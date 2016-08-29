package com.qaware.mentoring.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Test which creates a temporary file in the TEMP directory before the test and cleans up afterwards.
 * Without a @Rule the code has to be written into the @Before and @After methods.
 * Disadvantage: You always have to implement these methods in all test classes that should use it.
 */
public class TemporaryFolderWithoutRuleTest {

    private File folder;

    @Before
    public void createTemporaryFolder() throws Exception {
        folder = File.createTempFile("myFolder", "");
        folder.delete();
        folder.mkdir();
    }

    @Test
    public void test() throws Exception {
        File file = new File(folder, "test.txt");
        file.createNewFile();
        assertTrue(file.exists());
    }

    @After
    public void deleteTemporaryFolder() {
        recursivelyDelete(folder);
    }

    private void recursivelyDelete(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File each : files) {
                recursivelyDelete(each);
            }
        }
        file.delete();
    }
}
