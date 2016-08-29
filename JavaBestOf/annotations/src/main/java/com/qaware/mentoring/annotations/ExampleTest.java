package com.qaware.mentoring.annotations;

@CustomTest(name = "A very special test", desc = "This test is only here to be found by the TestRunner")
public class ExampleTest {

    @CustomTestMethod(desc = "The first test.")
    public void firstTest() {
        System.out.println("Method firstTest() is executed.");
    }

    @CustomTestMethod
    public void secondTest() {
        System.out.println("Method secondTest() is executed.");
    }
}
