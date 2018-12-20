package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.AuthHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.faces.context.FacesContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterBeanTest {

    private RegisterBean registerBean;

    @Mock
    private AuthHandler authHandler;

    @Mock
    private UserSessionBean userSessionBean;

    @Mock
    private FacesContext facesContext;

    @Before
    public void setUp() throws Exception {
        registerBean = new RegisterBean();
        registerBean.setAuthHandler(authHandler);
        registerBean.setUserSessionBean(userSessionBean);
        registerBean.setFacesContext(facesContext);
    }

    @Test
    public void createUserShouldReturnString() {
        when(authHandler.registerUser("arp", "karp")).thenReturn("1111.2222.3333");

        registerBean.setUsername("arp");
        registerBean.setPassword("karp");
        String redirect = registerBean.createUser();
        assertEquals("index?faces-redirect=true", redirect);
        verify(userSessionBean).setJwt("1111.2222.3333");
    }

    @Test
    public void createUserShouldReturnNull() {
        when(authHandler.registerUser("arp", "karp")).thenReturn(null);

        registerBean.setUsername("arp");
        registerBean.setPassword("karp");
        String redirect = registerBean.createUser();
        assertEquals("register", redirect);
        verify(userSessionBean, never()).setJwt(anyString());
    }
}