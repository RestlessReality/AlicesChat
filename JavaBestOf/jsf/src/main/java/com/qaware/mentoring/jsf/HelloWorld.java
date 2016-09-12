package com.qaware.mentoring.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "helloWorldBean")
@RequestScoped
public class HelloWorld {

    public String getMessage() {
        return "Hello World!!!";
    }

}
