package com.flashbook.frontend.handlers;

import com.flashbook.frontend.viewmodels.ViewFeedLog;
import com.flashbook.frontend.viewmodels.ViewLog;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class LogHandler extends RestClient {

    // Constructor for real usage
    public LogHandler() {
    }

    // Constructor for testing
    LogHandler(WebTarget client) {
        super(client);
    }

    /**
     * Get all logs for signed in user
     * @param jwt - Token for signed in user
     * @return List of ViewLog or null if something went wrong
     */
    public List<ViewLog> getMyLogs(String jwt) {
        Response response = getClient()
                .path("/logs")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + jwt)
                .get();
        if (response.getStatus() == 200)
            return response.readEntity(new GenericType<List<ViewLog>>() {
            });
        return null;
    }

    /**
     * Get logs for a single user
     * @param jwt - Token for signed in user
     * @param userId - Id for user to get logs from
     * @return List of ViewLog or null if something went wrong
     */
    public List<ViewLog> getLogsForUser(String jwt, int userId) {
        Response response = getClient()
                .path("/logs/foruser/" + userId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + jwt)
                .get();
        if (response.getStatus() == 200)
            return response.readEntity(new GenericType<List<ViewLog>>() {
            });
        return null;
    }

    /**
     * Create a log with given text for signed in user
     * @param jwt - Token for signed in user
     * @param text - Text to put in the log
     * @return Single ViewLog or null if something went wrong
     */
    public ViewLog createLog(String jwt, String text) {
        Response response = getClient()
                .path("/logs")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + jwt)
                .post(Entity.entity(text, MediaType.APPLICATION_JSON));
        if (response.getStatus() == 200)
            return response.readEntity(ViewLog.class);
        return null;
    }

    /**
     * Get feed for signed in user
     * @param jwt - Token for signed in user
     * @return List of ViewFeedLog or null if something went wrong
     */
    public List<ViewFeedLog> getFeed(String jwt) {
        Response response = getClient()
                .path("/logs/feed")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + jwt)
                .get();
        if (response.getStatus() == 200)
            return response.readEntity(new GenericType<List<ViewFeedLog>>() {
            });
        return null;
    }
}
