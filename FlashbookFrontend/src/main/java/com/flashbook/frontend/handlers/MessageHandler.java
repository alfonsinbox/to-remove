package com.flashbook.frontend.handlers;


import com.flashbook.frontend.viewmodels.CreateMessage;
import com.flashbook.frontend.viewmodels.ViewMessage;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class MessageHandler extends RestClient {

    // Constructor for real usage
    public MessageHandler() {
    }

    // Constructor for testing
    MessageHandler(WebTarget client) {
        super(client);
    }

    /**
     * Get messages for signed in user
     * @param jwt - Token for signed in user
     * @return List of ViewMessage or null if something went wrong
     */
    public List<ViewMessage> getMessages(String jwt) {
        Response response = getClient()
                .path("/messages")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + jwt)
                .get();
        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<ViewMessage>>() {
            });
        }
        return null;
    }

    /**
     * Send message to a single user
     * @param jwt - Token for signed in user
     * @param receiverId - Id of receiving user
     * @param text - Text to put in message
     * @return Single ViewMessage or null if something went wrong
     */
    public ViewMessage sendPrivateMessage(String jwt, int receiverId, String text) {
        Response response = getClient()
                .path("/messages")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + jwt)
                .post(Entity.entity(new CreateMessage( receiverId, text), MediaType.APPLICATION_JSON));
        if(response.getStatus() == 200)
            return response.readEntity(ViewMessage.class);
        return null;
    }

}
