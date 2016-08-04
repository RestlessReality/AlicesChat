package com.qaware.mentoring.chat.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServerEndpointRegistration;


/**
 * Example WebSocket setup with Spring Boot.
 * Sets up a WebSocket endpoint on ws://localhost:8080/echo
 * <p>
 * Source: https://spring.io/blog/2013/05/23/spring-framework-4-0-m1-websocket-support.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableWebSocket
@ComponentScan
public class ChatApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }

    @Bean
    public ServerEndpointRegistration echoEndpoint() {
        /* Registers the JSR-356 server endpoint at the WebSocket container (Jetty) */
        return new ServerEndpointRegistration("/echo", ChatServerEndpoint.class);
    }

    @Bean
    public ServerEndpointExporter endpointExporter() {
        return new ServerEndpointExporter();
    }
}
