package com.flashbook.frontend.beans;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserSessionBeanTest {

    private UserSessionBean userSessionBean;

    @Before
    public void setUp() throws Exception {
        userSessionBean = new UserSessionBean();
        // Token with userId = 3 and sub = arp
        userSessionBean.setJwt("irrelevant.eyJ1c2VySWQiOjMsInN1YiI6ImFycCJ9.signature");
    }

    @Test
    public void getIdShouldReturnThree() {
        Integer id = userSessionBean.getId();
        assertNotNull(id);
        assertEquals(new Integer(3), id);
    }

    @Test
    public void getUsernameShouldReturnArp() {
        String username = userSessionBean.getUsername();
        assertNotNull(username);
        assertEquals("arp", username);
    }

    @Test
    public void isLoggedInShouldBeTrue() {
        boolean loggedIn = userSessionBean.isLoggedIn();
        assertTrue(loggedIn);
    }
}