package com.qaware.mentoring.bean.validation;

public class PersonBuilder {
    private String firstName;
    private String lastName;
    private Address address;
    private Contact contact;

    public PersonBuilder setFistName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder setAddress(Address address) {
        this.address = address;
        return this;
    }

    public PersonBuilder setContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public Person createPerson() {
        return new Person(firstName, lastName, address, contact);
    }
}