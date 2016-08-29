package com.qaware.mentoring.annotations;


import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Example how annotations can be used.
 * <p>
 * This sample scans for classes and methods annotated which are in the same package, then tries to execute these methods.
 * Similar to how frameworks like Spring and JUnit find the classes and methods to instantiate and execute.
 */
public class TestRunner {

    private static final String separator = "-------------------------------------------------------\n";
    private static Predicate<Class<?>> isTestClass = clazz -> clazz.getAnnotation(CustomTest.class) != null;
    private static Predicate<Method> isTestMethod = method -> method.getAnnotation(CustomTestMethod.class) != null;
    private static Consumer<Class<?>> logClass = clazz -> System.out.println(separator + clazz.getAnnotation(CustomTest.class).desc());
    private static Consumer<Method> logMethod = method -> System.out.println(method.getAnnotation(CustomTestMethod.class).desc());

    /**
     * Runs the program.
     *
     * @param args not used
     * @throws IOException if the class path could not be determined
     */
    public static void main(String[] args) throws IOException {
        ClassPath path = ClassPath.from(TestRunner.class.getClassLoader());
        Collection<ClassPath.ClassInfo> classes = path.getTopLevelClassesRecursive(TestRunner.class.getPackage().getName());
        long numberOfTestClasses = classes.stream()
                .map(ClassPath.ClassInfo::load)
                .filter(isTestClass)
                .peek(logClass)
                .peek(invokeTestMethod)
                .count();
        System.out.println(separator + "Test classes executed overall: " + numberOfTestClasses);
    }

    private static Consumer<Class<?>> invokeTestMethod = clazz -> {
        try {
            long numberOfTestMethods = Arrays.stream(clazz.getMethods())
                    .filter(isTestMethod)
                    .peek(logMethod)
                    .peek(invokeMethod(clazz.newInstance()))
                    .count();
            System.out.println("\nNumber of test methods in class " + clazz.getSimpleName() + ": " + numberOfTestMethods);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    };

    private static Consumer<Method> invokeMethod(Object instance) {
        return method -> {
            try {
                method.invoke(instance);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        };
    }
}
