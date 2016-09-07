package com.qaware.mentoring.types.contravariance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generics {

    public static void main(String[] args) {

        {

            // Will not compile: Generics are contravariant with respect to the parametrized type

            // ArrayList<Number> numbers = new ArrayList<Integer>();

            // The generic type itself is still covariant though, ArrayList is a subtype of List and can be assigned to a List

            List<Number> numbers = new ArrayList<Number>(); // You can do that but you should use the diamond operator instead here.

            // Example for wildcard as parameter type: The question mark stands for 'any' type.
            // This is however hardly useful most of the time because the returned objects are of type Object.

            List<?> anything = Arrays.asList(2, "Hello!", new Object());
            Object anyObject = anything.get(1);
            System.out.println(anyObject);

            // Example for assignment with bounded wildcards:

            ArrayList<Float> floats = new ArrayList<>();
            ArrayList<Object> objects = new ArrayList<>();

            ArrayList<? extends Number> withSubTypes = floats;      // Float is subtype of Number
            floats.add(new Float(23.0f));
            System.out.println("With subtypes of Number: " + Arrays.toString(withSubTypes.toArray()));

            ArrayList<? super Number> withSuperTypes = objects;     // Object is supertype of Number
            objects.add("String is a subtype of Object which is a supertype of Number so add() is OK");
            System.out.println("With supertypes of Number: " + Arrays.toString(withSuperTypes.toArray()));  // Looks strange but is OK
        }

        // Use the PECS principle when using collections:
        // Producer Extends, Consumer Super.

        // Example for how a list is used as a producer

        {

            List<Integer> ints = Arrays.asList(1, 2, 3);
            System.out.println("Sum of ints: " + sum(ints));

            List<Float> floats = Arrays.asList(5.0f, 7.0f, 9.0f);
            System.out.println("Sum of floats: " + sum(floats));

            List<Number> mixed = Arrays.asList(1.0, 2, 3.8f);
            System.out.println("Sum of mixed: " + sum(mixed));

        }

        // Example for how a list is used as a consumer (see method below)

        {

            List<Number> numbers = new ArrayList<>();
            addDefault(numbers, 7, new Integer(57));
            System.out.println(Arrays.toString(numbers.toArray()));

            List<Object> objects = new ArrayList<>();
            addDefault(objects, 5, new Float(23));
            System.out.println(Arrays.toString(objects.toArray()));

        }

    }

    /**
     * A producer is always allowed to produce something more specific.
     *
     * @param numbers the numbers
     * @return the sum (as double)
     */
    public static double sum(List<? extends Number> numbers) {
        Double sum = 0.0;
        for (Number number : numbers) {
            sum += number.doubleValue();     // The List acts as a producer because we get values from it
        }
        return sum;
    }

    /**
     * A consumer is allowed to accept something more general.
     *
     * @param numbers list of numbers to be filled with default values
     * @param times the number of times to add the value to the list
     * @param value the default value
     */
    public static void addDefault(List<? super Number> numbers, int times, Number value) {
        for (int i = 0; i < times; ++i) {
            numbers.add(value);             // The List acts as a consumer because we put elements into it
        }
    }
}
