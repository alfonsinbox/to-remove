package com.flashbook.frontend.handlers;

import com.flashbook.frontend.viewmodels.LoginData;
import com.flashbook.frontend.viewmodels.RegisterData;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AuthHandler extends RestClient {

    // Constructor for real usage
    public AuthHandler() {
    }

    // Constructor for testing
    AuthHandler(WebTarget client) {
        super(client);
    }

    /**
     * Login a user with given credentials
     * @param username - Username of user
     * @param password - Password of user
     * @return JWT for user authentication or null if something went wrong
     */
    public String loginUser(String username, String password) {
        Response response = getClient()
                .path("/auth/login")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(new LoginData(username, password), MediaType.APPLICATION_JSON));
        if(response.getStatus() == 200)
            return response.readEntity(String.class);
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
        return null;
    }

    /**
     * Create a user with given credentials and sign in as that user
     * @param username - Username of user
     * @param password - Password of user
     * @return JWT for user authentication or null if something went wrong
     */
    public String registerUser(String username, String password) {
        Response response = getClient()
                .path("/auth/register")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(new RegisterData(username, password), MediaType.APPLICATION_JSON));
        if(response.getStatus() == 200)
            return response.readEntity(String.class);
        return null;
    }
}
