package com.qaware.mentoring.chat.client;


import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import java.util.Collection;
import java.util.function.BiConsumer;

public class TestClientEndpoint extends Endpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestClientEndpoint.class);

    private Collection<BiConsumer<String, Session>> consumers = Lists.newArrayList();

    /**
     * Registers a consumer which is called on message received events.
     *
     * @param consumer the consumer to be registered
     */
    public void registerOnMessageHandler(BiConsumer<String, Session> consumer) {
        consumers.add(consumer);
    }

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        LOGGER.info("Client connected! " + session.getId());
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String message) {
                LOGGER.info("Received message from server: {}, {}", session.getId(), message);
                consumers.forEach(c -> c.accept(message, session));
            }
        });
    }

    @Override
    public void onError(Session session, Throwable cause) {
        LOGGER.error("Error: {}, {}", session.getId(), cause.getMessage());
    }
}
