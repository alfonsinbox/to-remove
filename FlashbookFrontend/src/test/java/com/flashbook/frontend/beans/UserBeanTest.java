package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.FriendHandler;
import com.flashbook.frontend.handlers.LogHandler;
import com.flashbook.frontend.handlers.MessageHandler;
import com.flashbook.frontend.handlers.UserHandler;
import com.flashbook.frontend.viewmodels.ViewMessage;
import com.flashbook.frontend.viewmodels.ViewUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.faces.context.FacesContext;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserBeanTest {

    private UserBean userBean;

    @Mock
    private UserHandler userHandler;

    @Mock
    private LogHandler logHandler;

    @Mock
    private FriendHandler friendHandler;

    @Mock
    private MessageHandler messageHandler;

    @Mock
    private UserSessionBean userSessionBean;

    @Mock
    private FacesContext facesContext;

    @Before
    public void setUp() throws Exception {
        userBean = new UserBean();
        userBean.setUserHandler(logHandler);
        userBean.setUserHandler(userHandler);
        userBean.setFriendHandler(friendHandler);
        userBean.setMessageHandler(messageHandler);
        userBean.setUserSessionBean(userSessionBean);
        userBean.setFacesContext(facesContext);
    }

    @Test
    public void loadLogsShouldPopulateList() {
        when(logHandler.getLogsForUser("1111.2222.3333",3)).thenReturn(new ArrayList<>());
        when(userSessionBean.getJwt()).thenReturn("1111.2222.3333");
        userBean.setId(3);

        userBean.loadLogs();
        assertNotNull(userBean.getLogs());
    }

    @Test
    public void loadLogsShouldDoNothing() {
        when(logHandler.getLogsForUser("1111.2222.3333",3)).thenReturn(null);
        when(userSessionBean.getJwt()).thenReturn("1111.2222.3333");
        userBean.setId(3);

        userBean.loadLogs();
        assertEquals(0, userBean.getLogs().size());
    }

    @Test
    public void loadUserOnPostbackShouldDoNothing(){
        when(facesContext.isPostback()).thenReturn(true);
        String redirect = userBean.loadUser();
        assertEquals("", redirect);
    }

    @Test
    public void loadUserFailureShouldGoToIndex(){
        when(facesContext.isPostback()).thenReturn(false);
        when(userHandler.getUser("1234.qwer.asdf", 3)).thenReturn(null);
        when(userSessionBean.getJwt()).thenReturn("1234.qwer.asdf");
        userBean.setId(3);

        String redirect = userBean.loadUser();
        assertEquals("index?faces-redirect=true", redirect);
        verify(userHandler).getUser("1234.qwer.asdf", 3);
    }

    @Test
    public void loadUserSuccessfullyShouldSetBeanData(){
        when(facesContext.isPostback()).thenReturn(false);

        ViewUser mockUser = mock(ViewUser.class);
        when(mockUser.getId()).thenReturn(3);
        when(mockUser.getName()).thenReturn("arp");
        when(mockUser.isFriend()).thenReturn(true);

        when(userHandler.getUser("1234.qwer.asdf", 3)).thenReturn(mockUser);
        when(userSessionBean.getJwt()).thenReturn("1234.qwer.asdf");
        userBean.setId(3);

        String redirect = userBean.loadUser();
        assertEquals("", redirect);
        verify(userHandler).getUser("1234.qwer.asdf", 3);
        assertEquals(3, userBean.getId());
        assertEquals("arp", userBean.getName());
        assertTrue(userBean.isFriend());
    }

    @Test
    public void sendPrivateMessageShouldReturnSuccess() {
        when(messageHandler.sendPrivateMessage("1111.2222.3333", 5, "hello 5")).thenReturn(mock(ViewMessage.class));
        when(userSessionBean.getJwt()).thenReturn("1111.2222.3333");
        userBean.setId(5);
        userBean.setMessage("hello 5");

        userBean.sendPrivateMessage();
        assertEquals("success", userBean.getPmMessage());
        assertEquals("", userBean.getMessage());
    }

    @Test
    public void sendPrivateMessageShouldReturnFailure() {
        when(messageHandler.sendPrivateMessage("1111.2222.3333", 5, "hello 5")).thenReturn(null);
        when(userSessionBean.getJwt()).thenReturn("1111.2222.3333");
        userBean.setId(5);
        userBean.setMessage("hello 5");

        userBean.sendPrivateMessage();
        assertEquals("failure", userBean.getPmMessage());
        assertEquals("", userBean.getMessage());
    }

    @Test
    public void addFriendShouldReturnTrue() {
        when(friendHandler.addFriend("1111.2222.3333", 5)).thenReturn(true);
        when(userSessionBean.getJwt()).thenReturn("1111.2222.3333");
        userBean.setId(5);

        userBean.addFriend();
        assertTrue(userBean.isFriend());
    }

    @Test
    public void addFriendShouldReturnFalse() {
        when(friendHandler.addFriend("1111.2222.3333", 5)).thenReturn(false);
        when(userSessionBean.getJwt()).thenReturn("1111.2222.3333");
        userBean.setId(5);

        userBean.addFriend();
        assertFalse(userBean.isFriend());
    }
}