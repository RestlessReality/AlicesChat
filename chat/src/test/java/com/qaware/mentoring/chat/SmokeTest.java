package com.qaware.mentoring.chat;


import com.qaware.mentoring.chat.client.TestClientEndpoint;
import com.qaware.mentoring.chat.server.ChatApplication;
import org.eclipse.jetty.websocket.jsr356.ClientContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ChatApplication.class)
public class SmokeTest {

    private String lastMsg;
    private CountDownLatch latch;
    private WebSocketContainer container;
    private TestClientEndpoint client;

    @Before
    public void setUp() throws Exception {
        latch = new CountDownLatch(1);
        container = ContainerProvider.getWebSocketContainer();
        client = new TestClientEndpoint();
        client.registerOnMessageHandler(
                (msg, session) -> {
                    lastMsg = msg;
                    latch.countDown();
                }
        );
    }

    @Test
    public void smokeTest() throws Exception {
        Session clientSession = container.connectToServer(client, new URI("ws://localhost:8080/echo"));
        clientSession.getBasicRemote().sendText("Hi from client!");
        latch.await(5, TimeUnit.SECONDS);
        assertThat(lastMsg, is("HI FROM CLIENT!"));
    }

    @After
    public void tearDown() throws Exception {
        ((ClientContainer) container).stop();
    }
}
