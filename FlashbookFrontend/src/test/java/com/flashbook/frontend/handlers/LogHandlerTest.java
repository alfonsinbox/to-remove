package com.flashbook.frontend.handlers;

import com.flashbook.frontend.viewmodels.ViewFeedLog;
import com.flashbook.frontend.viewmodels.ViewLog;
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
public class LogHandlerTest {

    private LogHandler logHandler;

    @Mock
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder builder;

    @Mock
    private Response response;

    @Before
    public void setUp() throws Exception {
        logHandler = new LogHandler(webTarget);
    }

    @Test
    public void getMyLogsShouldReturnList() {
        when(webTarget.path("/logs")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(new GenericType<List<ViewLog>>(){})).thenReturn(new ArrayList<>());

        List<ViewLog> myLogs = logHandler.getMyLogs("1234.qwer.asdf");
        assertNotNull(myLogs);
    }

    @Test
    public void getMyLogsShouldReturnNull() {
        when(webTarget.path("/logs")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(500);

        List<ViewLog> myLogs = logHandler.getMyLogs("1234.qwer.asdf");
        assertNull(myLogs);
    }

    @Test
    public void createLogShouldReturnViewLog() {
        when(webTarget.path("/logs")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.post(any())).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(ViewLog.class)).thenReturn(mock(ViewLog.class));

        ViewLog log = logHandler.createLog("1234.qwer.asdf", "My log!");
        assertNotNull(log);
    }

    @Test
    public void createLogShouldReturnNull() {
        when(webTarget.path("/logs")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.post(any())).thenReturn(response);
        when(response.getStatus()).thenReturn(500);

        ViewLog log = logHandler.createLog("1234.qwer.asdf", "My log!");
        assertNull(log);
    }

    @Test
    public void getFeedShouldReturnList() {
        when(webTarget.path("/logs/feed")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(new GenericType<List<ViewFeedLog>>(){})).thenReturn(new ArrayList<>());

        List<ViewFeedLog> myFeed = logHandler.getFeed("1234.qwer.asdf");
        assertNotNull(myFeed);
    }

    @Test
    public void getFeedShouldReturnNull() {
        when(webTarget.path("/logs/feed")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(500);

        List<ViewFeedLog> myFeed = logHandler.getFeed("1234.qwer.asdf");
        assertNull(myFeed);
    }

    @Test
    public void getLogsForUserShouldReturnList() {
        when(webTarget.path("/logs/foruser/3")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(new GenericType<List<ViewLog>>(){})).thenReturn(new ArrayList<>());

        List<ViewLog> logsForUser = logHandler.getLogsForUser("1234.qwer.asdf", 3);
        assertNotNull(logsForUser);
    }

    @Test
    public void getLogsForUserShouldReturnNull() {
        when(webTarget.path("/logs/foruser/3")).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).thenReturn(builder);
        when(builder.header("Authorization", "Bearer 1234.qwer.asdf")).thenReturn(builder);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(500);

        List<ViewLog> logsForUser = logHandler.getLogsForUser("1234.qwer.asdf", 3);
        assertNull(logsForUser);
    }
}
