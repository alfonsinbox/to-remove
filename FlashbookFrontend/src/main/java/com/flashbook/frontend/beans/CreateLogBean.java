package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.LogHandler;
import com.flashbook.frontend.viewmodels.ViewLog;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Bean for dealing with the creation of a new log
 */
@ViewScoped
@ManagedBean(name = "createLog")
public class CreateLogBean {

    private String text;
    private String creationStatus;

    @ManagedProperty("#{userSession}")
    private UserSessionBean userSessionBean;

    public void setUserSessionBean(UserSessionBean userSessionBean) {
        this.userSessionBean = userSessionBean;
    }

    @PostConstruct
    private void init() {
        logHandler = new LogHandler();
    }

    private LogHandler logHandler;

    void setLogHandler(LogHandler logHandler) {
        this.logHandler = logHandler;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreationStatus() {
        return creationStatus;
    }

    public void setCreationStatus(String creationStatus) {
        this.creationStatus = creationStatus;
    }

    /**
     * Create a new log with the given text in the bean. Also sets a status message
     * to indicate if the creation was successful
     * @return String with where to redirect
     */
    public String create() {
        ViewLog log = logHandler.createLog(userSessionBean.getJwt(), text);
        if (log != null) {
            creationStatus = "Created!";
        } else {
            creationStatus = "Failed to create...";
        }
        text = "";
        return "profile";
    }
}
