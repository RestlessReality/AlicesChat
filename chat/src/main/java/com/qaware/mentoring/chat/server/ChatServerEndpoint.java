package com.qaware.mentoring.chat.server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;

/**
 * Spring bean which implements a JSR-356 server endpoint.
 * Instantiated explicitly because of registration purposes.
 */
public class ChatServerEndpoint extends Endpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatServerEndpoint.class);
    private final EchoService echoService;

    /**
     * Only for showing that dependency injection works.
     * Note that the bean is only instantiated on behalf of a connection request.
     *
     * @param echoService the service
     */
    @Autowired
    public ChatServerEndpoint(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        LOGGER.info("Session opened! {}", session.getId());

        final RemoteEndpoint.Basic remote = session.getBasicRemote();

        /* Note: the JSR-356 implementation seems to be unable to cope with Lambda expressions */
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String message) {
                LOGGER.info("Received message from client: {}", message);
                try {
                    /* Use the RemoteEndpoint object to send a message to the client */
                    remote.sendText(echoService.replyTo(message));
                } catch (IOException ioe) {
                    LOGGER.info("Error sending message: {}", ioe);
                }
            }
        });
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        LOGGER.info("Session closed: {}, {}", session.getId(), closeReason.getReasonPhrase());
    }

    @Override
    public void onError(Session session, Throwable cause) {
        LOGGER.info("Error: {}, {}", session.getId(), cause.getMessage());
    }
}
