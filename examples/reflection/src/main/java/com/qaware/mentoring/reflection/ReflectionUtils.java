package com.qaware.mentoring.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pretty printer utility class.
 */
public final class ReflectionUtils {

    /**
     * Private constructor prevents instantiation of utility class
     */
    private ReflectionUtils() {
    }

    /**
     * Pretty formats a constructor.
     *
     * @param ctor the constructor
     * @return the pretty string
     */
    public static String prettyFormat(final Constructor<?> ctor) {
        return Modifier.toString(ctor.getModifiers()) + " " + ctor.getName() + prettyFormat(ctor.getParameterTypes());
    }

    /**
     * Pretty formats the given parameters.
     *
     * @param parameterTypes the parameters
     * @return the pretty string
     */
    public static String prettyFormat(final Class<?>[] parameterTypes) {
        if (parameterTypes.length > 0) {
            return "(" + removeBrackets(Arrays.toString(parameterTypes)) + ")";
        }
        return "()";
    }

    /**
     * Pretty formats a method.
     *
     * @param method the method
     * @return the pretty string
     */
    public static String prettyFormat(final Method method) {
        return prettyFormat(method.getAnnotations()) + " " +
                Modifier.toString(method.getModifiers()) + " " +
                method.getReturnType() + " " +
                method.getName() + " " +
                prettyFormat(method.getParameterTypes());
    }

    /**
     * Pretty formats a field.
     *
     * @param field the field
     * @return the pretty string
     */
    public static String prettyFormat(final Field field) {
        return Modifier.toString(field.getModifiers()) + " " + field.getType() + " " + field.getName();
    }

    /**
     * Pretty formats an annotation.
     *
     * @param annotation the annotation
     * @return the pretty string
     */
    public static String prettyFormat(final Annotation annotation) {
        return "@" + annotation.toString();
    }

    /**
     * Pretty formats an array of annotations.
     *
     * @param annotations the annotations
     * @return the pretty string
     */
    public static String prettyFormat(final Annotation[] annotations) {
        if (annotations.length > 0) {
            return Arrays.toString(annotations);
        }
        return "";
    }

    public static List<Method> getAllMethods(final Class<?> clazz) {
        final List<Method> methods = new ArrayList<>();
        methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        if (clazz.getSuperclass() != null) {
            methods.addAll(getAllMethods(clazz.getSuperclass()));
        }
        return methods;
    }

    /* Private helper */

    private static String removeBrackets(String params) {
        return params.substring(params.indexOf("[") + 1, params.lastIndexOf("]"));
    }

}
