package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.FriendHandler;
import com.flashbook.frontend.handlers.LogHandler;
import com.flashbook.frontend.handlers.MessageHandler;
import com.flashbook.frontend.handlers.UserHandler;
import com.flashbook.frontend.viewmodels.ViewLog;
import com.flashbook.frontend.viewmodels.ViewMessage;
import com.flashbook.frontend.viewmodels.ViewUser;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean for viewing another users profile
 */
@ViewScoped
@ManagedBean(name = "user")
public class UserBean {
    private int id;
    private String name;
    private boolean isFriend;
    private String pmMessage;
    private String message;
    private List<ViewLog> logs;

    public String getPmMessage() {
        return pmMessage;
    }

    private FacesContext facesContext;

    void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    @ManagedProperty("#{userSession}")
    private UserSessionBean userSessionBean;

    public void setUserSessionBean(UserSessionBean userSessionBean) {
        this.userSessionBean = userSessionBean;
    }

    @PostConstruct
    private void init() {
        userHandler = new UserHandler();
        logHandler = new LogHandler();
        friendHandler = new FriendHandler();
        messageHandler = new MessageHandler();
        facesContext = FacesContext.getCurrentInstance();
    }

    private UserHandler userHandler;

    void setUserHandler(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    private LogHandler logHandler;

    void setUserHandler(LogHandler loghandler) {
        this.logHandler = loghandler;
    }

    private FriendHandler friendHandler;

    void setFriendHandler(FriendHandler friendhandler) {
        this.friendHandler = friendhandler;
    }

    private MessageHandler messageHandler;

    void setMessageHandler(MessageHandler messagehandler) {
        this.messageHandler = messagehandler;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ViewLog> getLogs() {
        return logs;
    }

    public void setLogs(List<ViewLog> logs) {
        this.logs = logs;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    /**
     * Get the user's logs and store them (or an empty array, if something went wrong) in the bean
     */
    public void loadLogs() {
        List<ViewLog> logsForUser = logHandler.getLogsForUser(userSessionBean.getJwt(), id);
        if (logsForUser != null) {
            logs = logsForUser;
        } else {
            logs = new ArrayList<>();
        }
    }

    /**
     * Get the user's profile information and store them in the bean
     * @return Empty String if all went well, path to index (for redirection) if something went wrong
     */
    public String loadUser() {
        if (facesContext.isPostback()) return "";
        System.out.println("Load user for other user");
        ViewUser user = userHandler.getUser(userSessionBean.getJwt(), id);
        if (user == null)
            return "index?faces-redirect=true";
        id = user.getId();
        name = user.getName();
        isFriend = user.isFriend();
        return "";
    }

    /**
     * Send a private message to the current user, and set a message
     * to indicate whether or not the sending went well
     */
    public void sendPrivateMessage() {
        ViewMessage viewMessage = messageHandler.sendPrivateMessage(userSessionBean.getJwt(), id, message);
        if (viewMessage != null) {
            pmMessage = "success";
        } else {
            pmMessage = "failure";
        }
        message = "";
    }

    /**
     * Send a friend request to the current user, and
     * set value to indicate friend request already sent
     */
    public void addFriend() {
        if (friendHandler.addFriend(userSessionBean.getJwt(), id)) {
            isFriend = true;
        }
    }
}
