package com.flashbook.frontend.beans;

import org.json.JSONObject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Base64;

/**
 * Bean for holding the JWT of the signed in user
 */
@SessionScoped
@ManagedBean(name = "userSession")
public class UserSessionBean {
    private String jwt;

    /**
     * Get Id of signed in user, or null if user is not signed in
     * @return Id
     */
    public Integer getId() {
        JSONObject payload = getJwtPayload();
        if (payload == null) return null;
        return payload.getInt("userId");
    }

    /**
     * Get username of signed in user, or null if user is not signed in
     * @return username
     */
    public String getUsername() {
        JSONObject payload = getJwtPayload();
        if (payload == null) return null;
        return payload.getString("sub");
    }

    /**
     * Check if user is signed in or not
     * @return true if signed in, false if not signed in
     */
    public boolean isLoggedIn() {
        return jwt != null;
    }

    public String getJwt() {
        return jwt;
    }

    void setJwt(String jwt) {
        this.jwt = jwt;
    }

    // Helper for extracting JSON Payload from JWT
    private JSONObject getJwtPayload() {
        if (jwt == null) return null;
        return new JSONObject(new String(Base64.getDecoder().decode(jwt.split("\\.")[1])));
    }
}
