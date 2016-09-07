package com.qaware.mentoring.types.covariance;

/**
 * Type conversion for assignment of references and method calls is covariant in Java.
 */
public class AssignmentAndMethodCalls {

    public static void main(String[] args) {

        Parent parent = new Child();                // Upwards conversion is ok
        // Child child = (Child) new Parent();      // Downwards conversion will fail: ClassCastException
        Child child = new Child();

        takeParent(child);                          // Ok, automatic conversion
        takeParent(parent);                         // Ok, same type
        takeChild(child);                           // Ok, same type
        // takeChild(parent);                       // Will not be converted automatically and explicit casting will fail

        Parent looksLikeParent = new Child();
        Child actually = (Child) looksLikeParent;   // Works! However the compiler will not protect us from ClassCastExceptions here if the reference is pointing to a Parent object
    }

    /**
     * Method takes Child objects as well.
     *
     * @param parent the parent
     */
    public static void takeParent(Parent parent) {
        // Do something...
    }

    /**
     * Method will not take Parent objects.
     *
     * @param child the child
     */
    public static void takeChild(Child child) {
        // Do something...
    }

}
