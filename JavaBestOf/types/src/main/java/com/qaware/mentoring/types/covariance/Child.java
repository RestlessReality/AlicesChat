package com.qaware.mentoring.types.covariance;

/**
 * Example for covariance in a type hierarchy: Return types in Java are covariant.
 * <p>
 * The method returns a subtype of the return type of {@link #getMember()}.
 * By doing so the contract is still fulfilled.
 */
public class Child extends Parent {

    @Override
    public String getMember() {
        return "I'm more special than Object so it is ok to return me";
    }

}
