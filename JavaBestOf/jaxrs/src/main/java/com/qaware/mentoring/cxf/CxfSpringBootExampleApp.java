package com.qaware.mentoring.cxf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Minimal Spring Boot REST service built with CXF and Jackson.
 * <p>
 * <p>
 * Returns "OK" on HTTP GET on http://localhost:8080/example/greetings
 * Returns Greeting message on HTTP POST on http://localhost:8080/example/greetings/<name>
 * <p>
 * Uses JSON format.
 */
@SpringBootApplication
public class CxfSpringBootExampleApp {

    public static void main(String[] args) {
        SpringApplication.run(CxfSpringBootExampleApp.class, args);
    }
}
