package com.qaware.mentoring.jsf;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "messageServiceBean")
@RequestScoped
public class MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    private String message = "Hello World!!!";

    public String getMessage() {
        LOGGER.info("getMessage() called");
        return message;
    }
}
