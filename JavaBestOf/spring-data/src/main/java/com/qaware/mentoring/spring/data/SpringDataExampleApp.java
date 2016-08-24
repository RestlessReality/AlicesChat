package com.qaware.mentoring.spring.data;

import com.qaware.mentoring.spring.data.entity.Person;
import com.qaware.mentoring.spring.data.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring Boot application which uses JPA and Spring Data repositories.
 */
@SpringBootApplication
@PropertySource(value = {"classpath:application.properties"})
@EnableTransactionManagement
@EnableJpaRepositories("com.qaware.mentoring.spring.data.repositories")
@ComponentScan(basePackages = "com.qaware.mentoring.spring.data.config")
@Component
public class SpringDataExampleApp {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringDataExampleApp.class, args);
        SpringDataExampleApp app = ctx.getBean(SpringDataExampleApp.class);
        app.init();
        app.query();
    }

    private void query() {
        repository.findAll().forEach(System.out::println);
    }

    private void init() {
        repository.save(new Person("Steven", "Wilson"));
        repository.save(new Person("Farin", "Urlaub"));
        repository.save(new Person("Maynard", "Keenan"));

    }
}
