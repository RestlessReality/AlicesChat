package com.qaware.mentoring.annotations;

@CustomTest(name = "Some other test.", desc = "This is another test which will be found.")
public class OtherExampleTest {

    @CustomTestMethod(desc = "An informative desc about someTest().")
    public void someTest() {
        System.out.println("Method someTest() is executed.");
    }
}
