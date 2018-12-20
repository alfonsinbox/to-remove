package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.FriendHandler;
import com.flashbook.frontend.viewmodels.ViewFriendship;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Bean for dealing with friends
 */
@ViewScoped
@ManagedBean(name = "friends")
public class FriendBean {

    private List<ViewFriendship> friends;

    @ManagedProperty("#{userSession}")
    private UserSessionBean userSessionBean;

    public void setUserSessionBean(UserSessionBean userSessionBean) {
        this.userSessionBean = userSessionBean;
    }

    private FriendHandler friendHandler;

    void setFriendHandler(FriendHandler friendHandler) {
        this.friendHandler = friendHandler;
    }

    @PostConstruct
    private void init() {
        friendHandler = new FriendHandler();
        loadFriends();
    }

    void loadFriends() {
        if (userSessionBean.isLoggedIn())
            friends = friendHandler.getFriends(userSessionBean.getJwt());
    }

    public List<ViewFriendship> getFriends() {
        return friends;
    }

    /**
     * Accept a friend request given as argument. Reload friend list if all went well
     * @param friendship - The friendship to accept
     */
    public void accept(ViewFriendship friendship) {
        System.out.println("Accept request from user " + friendship.getName());
        ViewFriendship newFriendship = friendHandler.acceptFriendship(userSessionBean.getJwt(), friendship.getId());
        if (newFriendship != null) {
            loadFriends();
        }
    }

    /**
     * Remove a friendship given as argument. Reload friend list if all went well
     * @param friendship - The friendship to remove
     */
    public void remove(ViewFriendship friendship) {
        System.out.println("Remove friendship with user " + friendship.getName());
        if (friendHandler.removeFriendship(userSessionBean.getJwt(), friendship.getId())) {
            loadFriends();
        } else {
            System.out.println("Remove friend was a failure");
        }
    }
}
