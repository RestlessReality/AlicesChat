package com.qaware.mentoring.bean.validation;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Person {

    /**
     * Value constructor.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param address  the address
     * @param contact  the contact
     */
    public Person(String firstName, String lastName, Address address, Contact contact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contact = contact;
    }

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    /**
     * The annotation @Valid tells the validator to check the address for annotations and to validate them as well.
     * Note that the field is only checked if the Default.class group is used (or no group at all) for validation.
     */
    @NotNull
    @Valid
    private Address address;

    /**
     * The annotation @Valid tells the validator to check the contact for annotations and to validate them as well.
     * Note that the field is only checked if the Default.class group is used (or no group at all) for validation.
     */
    @NotNull
    @Valid
    private Contact contact;

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", contact=" + contact +
                '}';
    }
}
