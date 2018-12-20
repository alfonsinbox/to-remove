package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.LogHandler;
import com.flashbook.frontend.viewmodels.ViewFeedLog;
import com.flashbook.frontend.viewmodels.ViewLog;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean for getting logs for signed in user
 */
@RequestScoped
@ManagedBean(name = "logs")
public class LogsBean {

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

    /**
     * Get all logs created by signed in user
     * @return List of ViewLog or empty array if something went wrong
     */
    public List<ViewLog> getMyLogs() {
        List<ViewLog> myLogs = logHandler.getMyLogs(userSessionBean.getJwt());
        if (myLogs != null) {
            return myLogs;
        }
        return new ArrayList<>();
    }

    /**
     * Get feed for signed in user
     * @return List of ViewFeedLog or empty array if something went wrong
     */
    public List<ViewFeedLog> getFeed() {
        List<ViewFeedLog> feed = logHandler.getFeed(userSessionBean.getJwt());
        if (feed != null) {
            return feed;
        }
        return new ArrayList<>();
    }
}
