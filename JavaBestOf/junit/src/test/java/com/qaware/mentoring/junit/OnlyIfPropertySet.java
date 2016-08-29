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
        Statement result = statement;
        // Conditionally replace the rule if the property is not set (i.e. is null)
        if (System.getProperty(property) == null) {
            result = new IgnoreStatement(testLabel(description) + " not executed because property '" + property + "' not found");
        }
        return result;
    }

    private String testLabel(Description description) {
        return description.getTestClass().getSimpleName() + "." + description.getMethodName() + "()";
    }
}
