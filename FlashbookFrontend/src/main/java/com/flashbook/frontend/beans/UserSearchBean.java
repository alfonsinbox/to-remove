package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.UserHandler;
import com.flashbook.frontend.viewmodels.ViewSearchUser;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean for searching for users
 */
@ViewScoped
@ManagedBean(name = "userSearch")
public class UserSearchBean {
    private String query;

    @ManagedProperty("#{userSession}")
    private UserSessionBean userSessionBean;

    public void setUserSessionBean(UserSessionBean userSessionBean) {
        this.userSessionBean = userSessionBean;
    }

    @PostConstruct
    private void init() {
        userHandler = new UserHandler();
    }
    private UserHandler userHandler;


    void setUserHandler(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Gets users matching the bean's query
     * @return List of ViewSearchUser, empty if something went wrong
     */
    public List<ViewSearchUser> getUsers() {
        List<ViewSearchUser> search = userHandler.search(userSessionBean.getJwt(), query);
        if (search != null) {
            return search;
        }
        return new ArrayList<>();
    }
}
