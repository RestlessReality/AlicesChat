package com.qaware.mentoring.bean.validation;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

/**
 * Uses validation annotations and Hibernate validator annotations.
 */
public class Address {

    /**
     * Value constructor.
     *
     * @param number  the number
     * @param street  the street
     * @param city    the city
     * @param state   the state
     * @param zip     the zip
     * @param country the country
     */
    public Address(String number, String street, String city, String state, String zip, String country) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    @NotNull(groups = Default.class)
    @Size(min = 1, max = 12, groups = Default.class)
    private final String number;

    @NotNull(groups = Default.class)
    @Size(min = 1, max = 120, groups = Default.class)
    private final String street;

    @NotNull(groups = Default.class)
    @Size(min = 1, max = 120, groups = Default.class)
    private final String city;

    @NotEmpty(groups = Default.class)
    private final String state;

    /**
     * Format: nnnnn
     */
    @NotNull(groups = Default.class)
    @Pattern(regexp = "[0-9]{5}", groups = Default.class)
    private final String zip;

    @NotNull(groups = Default.class)
    @Size(min = 1, max = 120, groups = Default.class)
    private final String country;

    @Override
    public String toString() {
        return "Address{" +
                "number='" + number + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
