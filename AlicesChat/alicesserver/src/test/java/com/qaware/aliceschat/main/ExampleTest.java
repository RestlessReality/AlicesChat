package com.qaware.aliceschat.main;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ExampleTest {
    @Test
    public void home() throws Exception {
        assertThat(new Example().home(), is ("Hello World!"));
    }

}