package com.qaware.mentoring.types.covariance;

/**
 * Example for covariance in type conversion.
 * Array type conversions in Java are covariant.
 */
public class ArraysAreCovariant {

    public static void main(String[] args) {

        // Arrays are covariant:
        // You can put any objects of subclasses into the array slots

        Number[] numbers = new Number[2];
        numbers[0] = new Integer(23);   // Integer extends Number
        numbers[1] = new Double(42.0);  // Double extends Number

        // You can even assign an array of a subtype, Arrays are covariant in the type of the array itself (dangerous!)

        numbers = new Integer[2];       // Assignment of Number[] <- Integer[]

        // However at runtime we get an ArrayStoreException because we can do this:
        // The compiler can not protect us here

        Number[] integers = new Integer[3];
        integers[0] = new Double(42.0);

    }

}
