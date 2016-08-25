package com.qaware.mentoring.cxf.service;

import com.qaware.mentoring.cxf.api.Greeting;
import com.qaware.mentoring.cxf.api.Status;
import org.springframework.stereotype.Component;

@Component
public class GreetingServiceImpl implements GreetingService {

    @Override
    public Status statusCheck() {
        return Status.OK;
    }

    @Override
    public Greeting getUser(String user) {
        return new Greeting(user);
    }
}
