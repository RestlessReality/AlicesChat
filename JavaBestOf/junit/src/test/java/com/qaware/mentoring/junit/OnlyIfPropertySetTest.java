package com.qaware.mentoring.junit;


import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test which is executed conditionally if a system property is set.
 */
public class OnlyIfPropertySetTest {

    private static final String REQUIRED_PROPERTY = "foo";
    private static final String PROPERTY_VALUE = "bar";

    /**
     * Sets the required system property.
     * Try to comment these lines out to see that the test is not executed any more.
     */
    @BeforeClass
    public static void setUp() {
//        System.setProperty(REQUIRED_PROPERTY, PROPERTY_VALUE);
    }

    @Rule
    public OnlyIfPropertySet onlyIfPropertySet = new OnlyIfPropertySet(REQUIRED_PROPERTY);

    @Test
    public void firstTest() throws Exception {
        System.out.println("Test is executed!");
    }

}
