package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.MessageHandler;
import com.flashbook.frontend.viewmodels.ViewMessage;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean for viewing private messages sent to signed in user
 */
@RequestScoped
@ManagedBean(name = "pm")
public class PrivateMessagesBean {

    @ManagedProperty("#{userSession}")
    private UserSessionBean userSessionBean;

    @PostConstruct
    private void init() {
        messageHandler = new MessageHandler();
    }

    private MessageHandler messageHandler;

    void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void setUserSessionBean(UserSessionBean userSessionBean) {
        this.userSessionBean = userSessionBean;
    }

    /**
     * Get all messages for signed in user
     * @return List of ViewMessage or empty array if something went wrong
     */
    public List<ViewMessage> getPrivateMessages() {
        if (!userSessionBean.isLoggedIn()) {
            return new ArrayList<>();
        }
        List<ViewMessage> messages = messageHandler.getMessages(userSessionBean.getJwt());
        if (messages != null) {
            return messages;
        }
        return new ArrayList<>();
    }
}
