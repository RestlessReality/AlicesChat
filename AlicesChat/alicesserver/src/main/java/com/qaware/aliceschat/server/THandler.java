package com.qaware.aliceschat.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class THandler extends TextWebSocketHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger( THandler.class);

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        LOGGER.info("handler called with message: {}", message.getPayload());
    }


}
