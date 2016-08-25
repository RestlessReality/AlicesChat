package com.qaware.mentoring.chat.server;

import org.springframework.stereotype.Component;

/**
 * Spring bean to be injected into the JSR-356 server endpoint.
 * Class exists only to show that Spring Dependency Injection works for JSR-356 WebSocket endpoints.
 */
@Component
public class EchoService {

    public String replyTo(String message) {
        return message.toUpperCase();
    }
}
