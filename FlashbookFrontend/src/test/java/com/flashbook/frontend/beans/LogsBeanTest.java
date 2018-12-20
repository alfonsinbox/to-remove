package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.LogHandler;
import com.flashbook.frontend.viewmodels.ViewFeedLog;
import com.flashbook.frontend.viewmodels.ViewLog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogsBeanTest {

    private LogsBean logsBean;

    @Mock
    private LogHandler logHandler;

    @Mock
    private UserSessionBean userSessionBean;

    @Before
    public void setUp() throws Exception {
        logsBean = new LogsBean();
        logsBean.setLogHandler(logHandler);
        logsBean.setUserSessionBean(userSessionBean);
    }

    @Test
    public void getMyLogsShouldReturnList() {
        when(logHandler.getMyLogs("1234.qwer.asdf")).thenReturn(new ArrayList<>());
        when(userSessionBean.getJwt()).thenReturn("1234.qwer.asdf");
        List<ViewLog> myLogs = logsBean.getMyLogs();
        assertNotNull(myLogs);
    }

    @Test
    public void getMyLogsShouldReturnNull() {
        when(logHandler.getMyLogs("1234.qwer.asdf")).thenReturn(null);
        when(userSessionBean.getJwt()).thenReturn("1234.qwer.asdf");
        List<ViewLog> myLogs = logsBean.getMyLogs();
        assertEquals(0, myLogs.size());
    }

    @Test
    public void getFeedShouldReturnList() {
        when(logHandler.getFeed("1234.qwer.asdf")).thenReturn(new ArrayList<>());
        when(userSessionBean.getJwt()).thenReturn("1234.qwer.asdf");
        List<ViewFeedLog> myFeed = logsBean.getFeed();
        assertNotNull(myFeed);
    }


    @Test
    public void getFeedShouldReturnNull() {
        when(logHandler.getFeed("1234.qwer.asdf")).thenReturn(null);
        when(userSessionBean.getJwt()).thenReturn("1234.qwer.asdf");
        List<ViewFeedLog> myFeed = logsBean.getFeed();
        assertEquals(0, myFeed.size());
    }
}