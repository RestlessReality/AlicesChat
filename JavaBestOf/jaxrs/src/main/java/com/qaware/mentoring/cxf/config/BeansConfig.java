package com.qaware.mentoring.cxf.config;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.spring.SpringResourceFactory;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.Path;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Programmatic Spring configuration.
 */
@Configuration
public class BeansConfig {

    // Application is available at http://localhost:8080/example/...
    private static final String ROOT_PATH = "/example/*";

    @Autowired
    private ApplicationContext ctx;

    /**
     * The CXF servlet.
     *
     * @return the servlet
     */
    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), ROOT_PATH);
    }

    /**
     * Performs serialization from objects to JSON.
     *
     * @return the JSON provider
     */
    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }

    /**
     * The JAX-RS server.
     * Initialized with Spring beans that have a {@link Path} annotation.
     *
     * @param jsonProvider the JSON serializer
     * @return the JAX-RS server
     */
    @Bean
    public Server jaxRsServer(JacksonJsonProvider jsonProvider) {
        LinkedList<ResourceProvider> resourceProviders = new LinkedList<>();
        for (String beanName : ctx.getBeanDefinitionNames()) {
            if (ctx.findAnnotationOnBean(beanName, Path.class) != null) {
                SpringResourceFactory factory = new SpringResourceFactory(beanName);
                factory.setApplicationContext(ctx);
                resourceProviders.add(factory);
            }
        }
        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
        factory.setBus(ctx.getBean(SpringBus.class));
        factory.setProviders(Collections.singletonList(jsonProvider));
        factory.setResourceProviders(resourceProviders);
        return factory.create();
    }
}
