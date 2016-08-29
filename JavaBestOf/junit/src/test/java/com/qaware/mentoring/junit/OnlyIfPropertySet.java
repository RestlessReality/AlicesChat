package com.qaware.mentoring.junit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Test rule which executes the test only if a specific property was found by System.getProperty().
 */
public class OnlyIfPropertySet implements TestRule {

    private final String property;

    /**
     * Value constructor.
     *
     * @param property the property to be required for test execution
     */
    public OnlyIfPropertySet(String property) {
        this.property = property;
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        if (System.getProperty(property) == null) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    System.out.println("Nothing happens and no test is executed -> The Power of Rules! ");
                }
            };
        }
        return statement;
    }

    private String testLabel(Description description) {
        return description.getTestClass().getSimpleName() + "." + description.getMethodName() + "()";
    }
}
