package com.qaware.mentoring.types.covariance;

/**
 * Example for covariance in a type hierarchy.
 * The subtype returns an object which has a more special type.
 */
public class Parent {

    Object getMember() {
        return new Object();
    }

}
