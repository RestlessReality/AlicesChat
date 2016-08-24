package com.qaware.mentoring.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Uses Hibernate annotations to tell Hibernate how to map the object to a table in the DB.
 */
@Entity
@Table(name = "PERSON")
/* package-private */ class Person {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    /**
     * Default constructor required by Hibernate.
     */
    public Person() {
    }

    /**
     * Value constructor.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    /* package-private */ Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
