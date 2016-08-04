package com.qaware.mentoring.chat.client;


import org.eclipse.jetty.websocket.jsr356.ClientContainer;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

/**
 * Minimum WebSocket client which connects to the server on localhost and sends some messages to it via WebSockets.
 */
public class ClientApplication {

    public static void main(String[] args) throws Exception {

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        Session session = container.connectToServer(new TestClientEndpoint(), new URI("ws://localhost:8080/echo"));

        for (int ping = 0; ping < 20; ++ping) {
            session.getBasicRemote().sendText("Hi from client!");
            Thread.sleep(1000);
        }

        ((ClientContainer) container).stop();
    }

}
