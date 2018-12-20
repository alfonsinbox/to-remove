package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.MessageHandler;
import com.flashbook.frontend.viewmodels.ViewMessage;
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
public class PrivateMessageBeanTest {

    private PrivateMessagesBean privateMessagesBean;

    @Mock
    private MessageHandler messageHandler;

    @Mock
    private UserSessionBean userSessionBean;

    @Before
    public void setUp() throws Exception {
        privateMessagesBean = new PrivateMessagesBean();
        privateMessagesBean.setMessageHandler(messageHandler);
        privateMessagesBean.setUserSessionBean(userSessionBean);
    }

    @Test
    public void getPrivateMessagesShouldReturnList() {
        when(userSessionBean.getJwt()).thenReturn("1111.2222.3333");
        when(userSessionBean.isLoggedIn()).thenReturn(true);
        when(messageHandler.getMessages("1111.2222.3333")).thenReturn(new ArrayList<>());
        List<ViewMessage> myPrivateMessages = privateMessagesBean.getPrivateMessages();
        assertNotNull(myPrivateMessages);
    }

    @Test
    public void getPrivateMessagesShouldReturnNull() {
        when(userSessionBean.isLoggedIn()).thenReturn(false);
        List<ViewMessage> myPrivateMessages = privateMessagesBean.getPrivateMessages();
        assertEquals(0, myPrivateMessages.size());
    }
}