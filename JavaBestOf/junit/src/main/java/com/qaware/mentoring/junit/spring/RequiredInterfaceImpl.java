package com.qaware.mentoring.junit.spring;

import org.springframework.stereotype.Component;

@Component
public class RequiredInterfaceImpl implements RequiredInterface {

    @Override
    public String foo() {
        return "bar";
    }

}
