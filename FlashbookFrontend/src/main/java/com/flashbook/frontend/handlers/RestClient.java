package com.flashbook.frontend.handlers;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Super class for entity handlers that will communicate with the Flashbook REST API
 */
class RestClient {
    private WebTarget client;

    // Constructor for real usage
    RestClient() {
        client = ClientBuilder.newClient()
                .target("http://fb-api-gateway/api");
    }

    // Constructor for testing
    RestClient(WebTarget client) {
        this.client = client;
    }

    /**
     * Get the WebTarget with configured URL for REST API
     */
    WebTarget getClient() {
        return client;
    }
}
