package com.flashbook.frontend.handlers;

import com.flashbook.frontend.viewmodels.ViewFriendship;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class FriendHandler extends RestClient {

    // Constructor for real usage
    public FriendHandler() {
    }

    // Constructor for testing
    FriendHandler(WebTarget client) {
        super(client);
    }

    /**
     * Send a friend request to user with given Id
     * @param jwt - Token for signed in user
     * @param friendId - Id of user to befriend
     * @return true if request was sent, false if something went wrong
     */
    public boolean addFriend(String jwt, int friendId) {
        Response response = getClient()
                .path("/friends/add/" + friendId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + jwt)
                .get();
        return response.getStatus() == 200;
    }

    /**
     * Get all friendships and friend requests for signed in user
     * @param jwt - Token for signed in user
     * @return List of ViewFriendship or null if something went wrong
     */
    public List<ViewFriendship> getFriends(String jwt) {
        Response response = getClient()
                .path("/friends")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + jwt)
                .get();
        if(response.getStatus() == 200)
            return response.readEntity(new GenericType<List<ViewFriendship>>() {
            });
        return null;
    }

    /**
     * Accept a friend request from a given user
     * @param jwt - Token for signed in user
     * @param befrienderId - Id of user that sent the request
     * @return ViewFriendship or null if something went wrong
     */
    public ViewFriendship acceptFriendship(String jwt, int befrienderId) {
        Response response = getClient()
                .path("/friends/accept/" + befrienderId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + jwt)
                .get();
        if(response.getStatus() == 200)
            return response.readEntity(ViewFriendship.class);
        return null;
    }

    /**
     * Remove a friendship or pending friend request from or to a given user
     * @param jwt - Token for signed in user
     * @param otherId - Id of other user in the friendship
     * @return true if friendship was removed, false if something went wrong
     */
    public boolean removeFriendship(String jwt, int otherId) {
        Response response = getClient()
                .path("/friends/remove/" + otherId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + jwt)
                .get();
        return response.getStatus() == 200;
    }
}
