package com.qaware.mentoring.jsf;


import javax.faces.bean.ManagedBean;

@ManagedBean(name = "editorBean")
public class Editor {

    private String value = "This editor is provided by PrimeFaces";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
