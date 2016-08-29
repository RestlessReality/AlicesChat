package com.qaware.mentoring.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Retention policy determines how long the annotation information is available.
 * ElementType determines which elements can be annotated.
 * <p>
 * Here we want the information to be available at runtime.
 * We want to annotate classes with this annotation.
 * <p>
 * Annotated class must provide a default constructor for instantiation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CustomTest {

    /**
     * The test name.
     *
     * @return the test name
     */
    String name() default "No test name was found.";

    /**
     * The test desc.
     *
     * @return the test desc
     */
    String desc() default "No test desc was found.";

}
