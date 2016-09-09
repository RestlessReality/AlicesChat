package com.qaware.mentoring.junit.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Spring JUnit runner executes our test.
 * Context configuration is done programmatically.
 * Test properties are taken from the properties file in the resources folder.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestWithProgrammaticSpringConfig.SpringTestConfig.class)
@TestPropertySource("classpath:test-application.properties")
public class TestWithProgrammaticSpringConfig {

    public static class SpringTestConfig {

        @Value("${some.important.property}")
        private String fooMockString;

        @Bean
        public FooService getFooService(RequiredInterface requiredInterface) {
            return new FooService(requiredInterface);
        }

        @Bean
        public RequiredInterface getRequiredInterface() {
            return new RequiredInterfaceMockImpl(fooMockString);
        }

    }

    @Autowired
    private FooService fooService;

    @Test
    public void testFooService() throws Exception {
        assertThat(fooService.getSomeBar(), is("mock"));
    }


}
