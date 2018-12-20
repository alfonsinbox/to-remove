package com.flashbook.frontend.handlers;

import com.flashbook.frontend.viewmodels.ViewFriendship;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FriendHandlerTest {

    private FriendHandler friendHandler;

    @Mock
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder builder;

    @Mock
    private Response response;

    @Before
    public void setUp() throws Exception {
        friendHandler = new FriendHandler(webTarget);
    }

    @Test
    public void addFriendShouldReturnTrue() {
        when(webTarget.path("/friends/add/3")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);

        boolean status = friendHandler.addFriend("1234.qwer.asdf", 3);
        assertTrue(status);
    }

    @Test
    public void addFriendShouldReturnFalse() {
        when(webTarget.path("/friends/add/3")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(500);

        boolean status = friendHandler.addFriend("1234.qwer.asdf", 3);
        assertFalse(status);
    }

    @Test
    public void getFriendsShouldReturnList() {
        when(webTarget.path("/friends")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(new GenericType<List<ViewFriendship>>(){})).thenReturn(new ArrayList<>());

        List<ViewFriendship> friends = friendHandler.getFriends("1234.qwer.asdf");
        assertNotNull(friends);
    }

    @Test
    public void getFriendsShouldReturnNull() {
        when(webTarget.path("/friends")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(500);

        List<ViewFriendship> friends = friendHandler.getFriends("1234.qwer.asdf");
        assertNull(friends);
    }

    @Test
    public void acceptFriendshipShouldReturnFriendship() {
        when(webTarget.path("/friends/accept/3")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(ViewFriendship.class)).thenReturn(mock(ViewFriendship.class));

        ViewFriendship viewFriendship = friendHandler.acceptFriendship("1234.qwer.asdf", 3);
        assertNotNull(viewFriendship);
    }

    @Test
    public void acceptFriendshipShouldReturnNull() {
        when(webTarget.path("/friends/accept/3")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(500);

        ViewFriendship viewFriendship = friendHandler.acceptFriendship("1234.qwer.asdf", 3);
        assertNull(viewFriendship);
    }

    @Test
    public void removeFriendshipShouldReturnTrue() {
        when(webTarget.path("/friends/remove/3")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);

        boolean status = friendHandler.removeFriendship("1234.qwer.asdf", 3);
        assertTrue(status);
    }

    @Test
    public void removeFriendshipShouldReturnFalse() {
        when(webTarget.path("/friends/remove/3")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(500);

        boolean status = friendHandler.removeFriendship("1234.qwer.asdf", 3);
        assertFalse(status);
    }
}