package com.qaware.mentoring.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Example how to invoke a private method with Reflection.
 */
public class InvokingPrivateMethodsApp {

    /**
     * The full qualified class name (including package) required for instantiation.
     * If reflection is used this information often comes from a property or configuration file
     * because it is not known at compile time. Here we just hard code it.
     */
    public static final String CLASS_NAME = "com.qaware.mentoring.reflection.TestA";

    /**
     * Runs the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        callSecretMethod();
    }

    /**
     * Shows that calling a method via reflection might fail for several reasons.
     */
    private static void callSecretMethod() {
        try {

            // Gets the class object - might fail if the class is not on the class path
            Class<?> clazz = Class.forName(CLASS_NAME);

            // Gets an instance of the class - might fail if there is no appropriate constructor
            Object instance = clazz.newInstance();

            // Tries to find the method by name and parameter types - might fail if the method does not exist
            Method secretMethod = clazz.getDeclaredMethod("secretMethod", Integer.class, Integer.class);

            // Sets the method modifier to "public"
            secretMethod.setAccessible(true);

            // Invokes the method - might fail because we have no type safety when calling the method
            Object result = secretMethod.invoke(instance, 23, 19);

            // Returned result is of type Object - again no type safety (might need to cast the object for further usage)
            System.out.println("Calling the secret method results in " + result);

        } catch (ClassNotFoundException | InstantiationException |
                NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
