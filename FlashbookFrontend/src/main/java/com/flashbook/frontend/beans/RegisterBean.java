package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.AuthHandler;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * Bean for the registration page
 */
@RequestScoped
@ManagedBean(name = "register")
public class RegisterBean {
    private String username;
    private String password;

    @ManagedProperty("#{userSession}")
    private UserSessionBean userSessionBean;

    private FacesContext facesContext;

    void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
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

    public void setUserSessionBean(UserSessionBean userSessionBean) {
        this.userSessionBean = userSessionBean;
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
     * Sign up a new user with the provided credentials and become signed in if all went well
     * @return String indicating where to redirect user depending on if all went well
     */
    public String createUser() {
        System.out.printf("got user with %s and password %s%n", this.username, this.password);
        String jwt = authHandler.registerUser(username, password);
        if (jwt != null) {
            System.out.println("Registered user, got token: " + jwt);
            userSessionBean.setJwt(jwt);
            return "index?faces-redirect=true";
        }
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User already exists!", ""));
        return "register";
    }
}
