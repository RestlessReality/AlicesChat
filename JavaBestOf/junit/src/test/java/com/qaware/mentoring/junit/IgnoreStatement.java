package com.qaware.mentoring.junit;

import org.junit.runners.model.Statement;

/**
 * JUnit statement which does not execute anything but only prints out some message.
 */
public class IgnoreStatement extends Statement {

    private final String msg;

    public IgnoreStatement(String msg) {
        this.msg = msg;
    }

    @Override
    public void evaluate() throws Throwable {
        System.out.println(msg);
    }
}
