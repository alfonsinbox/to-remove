package com.flashbook.frontend.handlers;

import com.flashbook.frontend.viewmodels.ViewMessage;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageHandlerTest {

    private MessageHandler messageHandler;

    @Mock
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder builder;

    @Mock
    private Response response;

    @Before
    public void setUp() throws Exception {
        messageHandler = new MessageHandler(webTarget);
    }

    @Test
    public void getMessagesShouldReturnList() {
        when(webTarget.path("/messages")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(new GenericType<List<ViewMessage>>(){})).thenReturn(new ArrayList<>());

        List<ViewMessage> myMessages = messageHandler.getMessages("1234.qwer.asdf");
        assertNotNull(myMessages);
    }

    @Test
    public void getMessagesShouldReturnNull() {
        when(webTarget.path("/messages")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(500);

        List<ViewMessage> myMessages = messageHandler.getMessages("1234.qwer.asdf");
        assertNull(myMessages);
    }

    @Test
    public void sendPrivateMessageShouldReturnViewMessage() {
        when(webTarget.path("/messages")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.post(any())).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(ViewMessage.class)).thenReturn(mock(ViewMessage.class));

        ViewMessage viewMessage = messageHandler.sendPrivateMessage("1234.qwer.asdf", 3, "hello 3");
        assertNotNull(viewMessage);
    }

    @Test
    public void sendPrivateMessageShouldReturnNull() {
        when(webTarget.path("/messages")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.post(any())).thenReturn(response);
        when(response.getStatus()).thenReturn(500);

        ViewMessage viewMessage = messageHandler.sendPrivateMessage("1234.qwer.asdf", 3, "hello 3");
        assertNull(viewMessage);
    }
}
