package com.flashbook.frontend.beans;

import com.flashbook.frontend.handlers.AuthHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginBeanTest {

    private LoginBean loginBean;

    @Mock
    private AuthHandler authHandler;

    @Mock
    private UserSessionBean userSessionBean;

    @Mock
    private FacesContext facesContext;

    @Before
    public void setUp() throws Exception {
        loginBean = new LoginBean();
        loginBean.setAuthHandler(authHandler);
        loginBean.setUserSessionBean(userSessionBean);
        loginBean.setFacesContext(facesContext);
    }

    @Test
    public void loginSuccessfullyShouldSetJwt() {
        loginBean.setUsername("arp");
        loginBean.setPassword("karp");

        when(authHandler.loginUser("arp", "karp")).thenReturn("asdf.1234.qwer");

        String response = loginBean.doLogin();
        assertEquals("index?faces-redirect=true", response);
        verify(userSessionBean).setJwt("asdf.1234.qwer");
    }

    @Test
    public void loginWithFailureShouldSetReturnLogin() {
        loginBean.setUsername("arp");
        loginBean.setPassword("karp");
        when(authHandler.loginUser("arp", "karp")).thenReturn(null);
//        doNothing().when(facesContext).addMessage(anyString(), any());

        String response = loginBean.doLogin();
        assertEquals("login", response);
    }

    @Test
    public void logoutSuccessfully() {
//        when(authHandler.loginUser("arp", "karp")).thenReturn(null);
//        doNothing().when(facesContext).addMessage(anyString(), any());

        when(facesContext.getExternalContext()).thenReturn(mock(ExternalContext.class));
        String response = loginBean.doLogout();
        assertEquals("index?faces-redirect=true", response);
    }
}