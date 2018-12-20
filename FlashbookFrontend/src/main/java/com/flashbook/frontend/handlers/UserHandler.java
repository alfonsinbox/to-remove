package com.flashbook.frontend.handlers;

import com.flashbook.frontend.viewmodels.ViewSearchUser;
import com.flashbook.frontend.viewmodels.ViewUser;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Communication to the User resource on the REST API
 */
public class UserHandler extends RestClient {

    // Constructor for real usage
    public UserHandler() {
    }

    // Constructor for testing
    UserHandler(WebTarget client) {
        super(client);
    }

    /**
     * Search for users with names matching the given query
     * @param jwt - Token for signed in user
     * @param query - Search query
     * @return List of ViewSearchUser or null if something went wrong
     */
    public List<ViewSearchUser> search(String jwt, String query) {
        Response response = getClient()
                .path("/users/search/" + query)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + jwt)
                .get();
        if(response.getStatus() == 200)
            return response.readEntity(new GenericType<List<ViewSearchUser>>() {
            });
        return null;
    }

    /**
     * Get a single user matching the given Id
     * @param jwt - Token for signed in user
     * @param id - Id for user
     * @return Single ViewUser or null if something went wrong
     */
    public ViewUser getUser(String jwt, int id) {
        Response response = getClient()
                .path("/users/" + id)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + jwt)
                .get();
        if(response.getStatus() == 200)
            return response.readEntity(ViewUser.class);
        return null;
    }
}
