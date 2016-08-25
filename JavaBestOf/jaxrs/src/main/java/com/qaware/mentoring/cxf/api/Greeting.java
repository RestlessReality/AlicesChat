package com.qaware.mentoring.cxf.api;

import java.time.LocalDate;

/**
 * Example class which is serialized to JSON when returned from the REST service.
 * JSON serialization is performed by Jackson.
 */
public class Greeting {

    private final String user;
    private final LocalDate date;

    /**
     * Value constructor.
     *
     * @param user the user name
     */
    public Greeting(String user) {
        this.user = user;
        this.date = LocalDate.now();
    }

    // --- Getter (used by framework for serialization) ---

    public String getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    // --- toString ---

    @Override
    public String toString() {
        return "Greeting{" +
                "user='" + user + '\'' +
                ", date=" + date +
                '}';
    }
}
