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
 * We want to annotate methods with this annotation.
 * <p>
 * Annotated method must have no parameters to be properly executed.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CustomTestMethod {

    /**
     * The test method desc.
     *
     * @return the desc
     */
    String desc() default "No test method desc was found.";

}
