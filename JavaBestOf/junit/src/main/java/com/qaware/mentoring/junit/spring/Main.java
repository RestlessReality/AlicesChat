package com.qaware.mentoring.junit.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ComponentScan(basePackages = "com.qaware.mentoring.junit.spring")
public class Main {

    @Autowired
    private FooService fooService;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Main main = context.getBean(Main.class);
        main.run();
    }

    public void run() {
        System.out.println(fooService.getSomeBar());
    }

}
