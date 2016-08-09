package com.qaware.mentoring.reflection;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

/**
 * Example program which prints some Reflection information of classes.
 */
public class PrintClassInfoApp {

    /**
     * Runs the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        // Inspects a custom class

        inspectClass(TestA.class);

        // Inspects an anonymous class

        inspectClass(new Serializable() {}.getClass());

        // Inspects a generic type

        inspectClass(HashMap.SimpleEntry.class);

    }


    private static void inspectClass(final Class<?> clazz) {

        System.out.println("/////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("-------------------------------- GENERAL CLASS INFO ---------------------------------");
        System.out.println("Simple class name: " + clazz.getSimpleName());
        System.out.println("Class name: " + clazz.getName());
        System.out.println("Canonical class name: " + clazz.getCanonicalName());
        System.out.println("Super class: " + clazz.getSuperclass());
        System.out.println("Interfaces: " + Arrays.toString(clazz.getInterfaces()));
        System.out.println("");

        System.out.println("----------------------------------- CONSTRUCTORS ------------------------------------");
        final Collection<Constructor<?>> declaredConstructors = Arrays.asList(clazz.getDeclaredConstructors());
        declaredConstructors.forEach(ctor -> System.out.println(ReflectionUtils.prettyFormat(ctor)));
        System.out.println("");

        System.out.println("-------------------------------------- FIELDS ---------------------------------------");
        final Collection<Field> declaredFields = Arrays.asList(clazz.getDeclaredFields());
        declaredFields.forEach(field -> System.out.println(ReflectionUtils.prettyFormat(field)));
        System.out.println("");

        System.out.println("-------------------------------------- METHODS --------------------------------------");
        final Collection<Method> methods = ReflectionUtils.getAllMethods(clazz);
        methods.forEach(method -> System.out.println(ReflectionUtils.prettyFormat(method)));
        System.out.println("");

        System.out.println("------------------------------------ ANNOTATIONS ------------------------------------");
        final Collection<Annotation> annotations = Arrays.asList(clazz.getAnnotations());
        annotations.forEach(annotation -> System.out.println(ReflectionUtils.prettyFormat(annotation)));
        System.out.println("");
    }

}
