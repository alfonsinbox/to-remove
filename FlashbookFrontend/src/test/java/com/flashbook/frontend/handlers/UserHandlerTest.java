package com.flashbook.frontend.handlers;

import com.flashbook.frontend.viewmodels.ViewSearchUser;
import com.flashbook.frontend.viewmodels.ViewUser;
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserHandlerTest {

    private UserHandler userHandler;

    @Mock
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder builder;

    @Mock
    private Response response;

    @Before
    public void setUp() throws Exception {
        userHandler = new UserHandler(webTarget);
    }

    @Test
    public void searchShouldReturnUsers() {
        when(webTarget.path("/users/search/query")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization","Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(new GenericType<List<ViewSearchUser>>(){})).thenReturn(new ArrayList<>());

        List users = userHandler.search("1234.qwer.asdf", "query");
        assertNotNull(users);
    }

    @Test
    public void searchShouldReturnNull() {
        when(webTarget.path("/users/search/query")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization","Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(500);

        List users = userHandler.search("1234.qwer.asdf", "query");
        assertNull(users);
    }

    @Test
    public void getUserShouldReturnUsers() {
        when(webTarget.path("/users/2")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization","Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(ViewUser.class)).thenReturn(mock(ViewUser.class));

        ViewUser user = userHandler.getUser("1234.qwer.asdf", 2);
        assertNotNull(user);
    }

    @Test
    public void getUserShouldReturnNull() {
        when(webTarget.path("/users/2")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization","Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(500);

        ViewUser user = userHandler.getUser("1234.qwer.asdf", 2);
        assertNull(user);
    }
}