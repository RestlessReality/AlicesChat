package com.qaware.mentoring.junit.spring;

import org.springframework.beans.factory.annotation.Value;

public class RequiredInterfaceMockImpl implements RequiredInterface {

    private final String foo;

    public RequiredInterfaceMockImpl(@Value("${some.important.property}") String foo) {
        this.foo = foo;
    }

    @Override
    public String foo() {
        return foo;
    }

}
