package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.FriendHandler;
import com.flashbook.frontend.viewmodels.ViewFriendship;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FriendBeanTest {


    private FriendBean friendBean;

    @Mock
    private FriendHandler friendHandler;

    @Mock
    private UserSessionBean userSessionBean;

    @Before
    public void setUp() throws Exception {
        friendBean = new FriendBean();
        friendBean.setFriendHandler(friendHandler);
        friendBean.setUserSessionBean(userSessionBean);
    }

    @Test
    public void loadFriendsWhenLoggedIn() {
        when(userSessionBean.isLoggedIn()).thenReturn(true);
        when(userSessionBean.getJwt()).thenReturn("1234.qwer.asdf");
        when(friendHandler.getFriends("1234.qwer.asdf")).thenReturn(new ArrayList<>());

        friendBean.loadFriends();
        assertNotNull(friendBean.getFriends());
    }

    @Test
    public void loadFriendsWhenLoggedOut() {
        when(userSessionBean.isLoggedIn()).thenReturn(false);

        friendBean.loadFriends();
        assertNull(friendBean.getFriends());
    }

    @Test
    public void acceptFriendshipSuccessfully() {
        when(userSessionBean.getJwt()).thenReturn("1234.qwer.asdf");
        when(friendHandler.acceptFriendship("1234.qwer.asdf", 3)).thenReturn(mock(ViewFriendship.class));
        when(userSessionBean.isLoggedIn()).thenReturn(true);

        friendBean.accept(new ViewFriendship(3, "arp", false, true));
        verify(friendHandler).acceptFriendship("1234.qwer.asdf", 3);
        verify(friendHandler).getFriends("1234.qwer.asdf");
    }

    @Test
    public void acceptFriendshipWithFailure() {
        when(userSessionBean.getJwt()).thenReturn("1234.qwer.asdf");
        when(friendHandler.acceptFriendship("1234.qwer.asdf", 3)).thenReturn(null);

        friendBean.accept(new ViewFriendship(3, "arp", false, true));
        verify(friendHandler).acceptFriendship("1234.qwer.asdf", 3);
        verify(friendHandler, never()).getFriends("1234.qwer.asdf");
    }

    @Test
    public void removeFriendshipSuccessfully() {
        when(userSessionBean.getJwt()).thenReturn("1234.qwer.asdf");
        when(friendHandler.removeFriendship("1234.qwer.asdf", 3)).thenReturn(true);
        when(userSessionBean.isLoggedIn()).thenReturn(true);

        friendBean.remove(new ViewFriendship(3, "arp", false, true));
        verify(friendHandler).removeFriendship("1234.qwer.asdf", 3);
        verify(friendHandler).getFriends("1234.qwer.asdf");
    }

    @Test
    public void removeFriendshipWithFailure() {
        when(userSessionBean.getJwt()).thenReturn("1234.qwer.asdf");
        when(friendHandler.removeFriendship("1234.qwer.asdf", 3)).thenReturn(false);

        friendBean.remove(new ViewFriendship(3, "arp", false, true));
        verify(friendHandler).removeFriendship("1234.qwer.asdf", 3);
        verify(friendHandler, never()).getFriends("1234.qwer.asdf");
    }
}