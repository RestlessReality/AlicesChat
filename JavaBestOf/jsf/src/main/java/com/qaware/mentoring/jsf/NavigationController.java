package com.qaware.mentoring.jsf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigationController")
@RequestScoped
public class NavigationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavigationController.class);

    //this managed property will read value from request parameter pageId
    @ManagedProperty(value = "#{param.pageId}")
    private String pageId;

    public String showPage() {
        LOGGER.info("Navigating to page " + pageId);
        if (pageId == null) {
            return "home";
        }
        if (pageId.equals("1")) {
            return "page1?faces-redirect=true";
        } else if (pageId.equals("2")) {
            return "page2?faces-redirect=true";
        } else if (pageId.equals("3")) {
            return "page3?faces-redirect=true";
        } else {
            return "home";
        }
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
