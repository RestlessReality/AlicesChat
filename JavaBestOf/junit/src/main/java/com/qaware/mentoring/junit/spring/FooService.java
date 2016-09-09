package com.qaware.mentoring.junit.spring;

import org.springframework.stereotype.Component;

@Component
public class FooService {

    private final RequiredInterface requiredInterface;

    public FooService(RequiredInterface injectedBean) {
        this.requiredInterface = injectedBean;
    }

    public String getSomeBar() {
        return requiredInterface.foo();
    }

}
