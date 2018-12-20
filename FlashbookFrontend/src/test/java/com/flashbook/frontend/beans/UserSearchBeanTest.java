package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.UserHandler;
import com.flashbook.frontend.viewmodels.ViewSearchUser;
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
public class UserSearchBeanTest {

    private UserSearchBean userSearchBean;

    @Mock
    private UserHandler userHandler;
    @Mock
    private UserSessionBean userSessionBean;

    @Before
    public void setUp() throws Exception {
        userSearchBean = new UserSearchBean();
        userSearchBean.setUserHandler(userHandler);
        userSearchBean.setUserSessionBean(userSessionBean);
    }

    @Test
    public void searchUsersShouldReturnUsers() {
        when(userHandler.search("1111.2222.3333", "query")).thenReturn(new ArrayList<>());
        when(userSessionBean.getJwt()).thenReturn("1111.2222.3333");
        userSearchBean.setQuery("query");

        List<ViewSearchUser> users = userSearchBean.getUsers();
        assertNotNull(users);
    }

    @Test
    public void searchUsersShouldReturnEmpty() {
        when(userHandler.search("1111.2222.3333", "query")).thenReturn(null);
        when(userSessionBean.getJwt()).thenReturn("1111.2222.3333");
        userSearchBean.setQuery("query");

        List<ViewSearchUser> users = userSearchBean.getUsers();
        assertEquals(0, users.size());
    }
}