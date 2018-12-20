package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.AuthHandler;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Bean for dealing with the login page
 */
@RequestScoped
@ManagedBean(name = "login")
public class LoginBean {
    private String username;
    private String password;

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
    private void init(){
        authHandler = new AuthHandler();
        facesContext = FacesContext.getCurrentInstance();
    }

    private AuthHandler authHandler;

    void setAuthHandler(AuthHandler authHandler) {
        this.authHandler = authHandler;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sign in user with provided credentials
     * @return String with where to redirect depending on how login went
     */
    public String doLogin() {
        System.out.printf("logging in user with %s and password %s%n", this.username, this.password);
        String jwt = authHandler.loginUser(username, password);
        if (jwt != null) {
            System.out.println("Correct login JWT = " + jwt);
            userSessionBean.setJwt(jwt);
            return "index?faces-redirect=true";
        } else {
            System.out.println("Failed login");
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Could not log in", "null"));
            return "login";
        }
    }

    /**
     * Log out user and redirect user to index
     * @return String with where to redirect, will always be index
     */
    public String doLogout() {
        System.out.println("DOING LOGOUT");
        facesContext.getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
}
