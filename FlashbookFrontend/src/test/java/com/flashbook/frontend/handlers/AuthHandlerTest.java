package com.flashbook.frontend.handlers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthHandlerTest {

    private AuthHandler authHandler;

    @Mock
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder builder;

    @Mock
    private Response response;


    @Before
    public void setUp() throws Exception {
        authHandler = new AuthHandler(webTarget);
    }

    @Test
    public void loginSuccessfully() {
        when(webTarget.path("/auth/login")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.post(any())).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(String.class)).thenReturn("1234.qwer.asdf");

        String jwt = authHandler.loginUser("arp", "karp");
        assertEquals(jwt, "1234.qwer.asdf");
    }

    @Test
    public void loginWithFailure() {
        when(webTarget.path("/auth/login")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.post(any())).thenReturn(response);
        when(response.getStatus()).thenReturn(401);

        String jwt = authHandler.loginUser("arp", "karp");
        assertNull(jwt);
    }

    @Test
    public void registerSuccessfully() {
        when(webTarget.path("/auth/register")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.post(any())).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(String.class)).thenReturn("1234.qwer.asdf");

        String jwt = authHandler.registerUser("arp", "karp");
        assertEquals(jwt, "1234.qwer.asdf");
    }

    @Test
    public void registerWithFailure() {
        when(webTarget.path("/auth/register")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.post(any())).thenReturn(response);
        when(response.getStatus()).thenReturn(500);

        String jwt = authHandler.registerUser("arp", "karp");
        assertNull(jwt);
    }
}