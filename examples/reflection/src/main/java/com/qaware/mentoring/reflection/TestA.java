package com.qaware.mentoring.reflection;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Deprecated
public class TestA extends TestB implements SomeInterface{

    private String someMagicString;

    private Integer secretMethod(Integer left, Integer right) {
        return left + right;
    }

    @Override
    public Long toBeImplemented(String exampleParameter) {
        throw new NotImplementedException();
    }

    public String getSomeMagicString() {
        return someMagicString;
    }

    public void setSomeMagicString(String someMagicString) {
        this.someMagicString = someMagicString;
    }
}
