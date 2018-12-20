package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.LogHandler;
import com.flashbook.frontend.viewmodels.ViewLog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateLogBeanTest {

    private CreateLogBean createLogBean;

    @Mock
    private LogHandler logHandler;

    @Mock
    private UserSessionBean userSessionBean;

    @Before
    public void setUp() throws Exception {
        createLogBean= new CreateLogBean();
        createLogBean.setLogHandler(logHandler);
        createLogBean.setUserSessionBean(userSessionBean);
    }

    @Test
    public void removeFriendshipWithFailure() {
        when(logHandler.createLog("1234.qwer.asdf", "My log")).thenReturn(mock(ViewLog.class));
        when(userSessionBean.getJwt()).thenReturn("1234.qwer.asdf");
        createLogBean.setText("My log");

        String s = createLogBean.create();
        verify(logHandler).createLog("1234.qwer.asdf", "My log");
        assertEquals("profile", s);
    }
}