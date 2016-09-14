package com.qaware.mentoring.jsf;

import org.slf4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "helloWorldBean")
@RequestScoped
public class HelloWorld {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(HelloWorld.class);

    @ManagedProperty(value="#{messageServiceBean}")
    private MessageService messageService;

    public String getMessage() {
        LOGGER.info("getMessage() called");
        return messageService.getMessage();
    }

    public void setMessageService(MessageService messageService) {
        LOGGER.info("setMessageService() called");
        this.messageService = messageService;
    }

}
