package ru.job4j.todo.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.todo.persistence.User;
import ru.job4j.todo.service.HibernateStore;
import ru.job4j.todo.service.MockStore;
import ru.job4j.todo.service.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MockStore.class)
public class AuthServletTest {
    @Test
    public void whenAuth() throws IOException, ServletException {
        Store store = MockStore.instOf();
        PowerMockito.mockStatic(MockStore.class);
        when(HibernateStore.instOf()).thenReturn(store);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession sc = mock(HttpSession.class);
        when(req.getSession()).thenReturn(sc);
        when(req.getParameter("password")).thenReturn("password");

        User user = new User(1, "Alex", "email", "password");
        String email = user.getEmail();
        String password = "password";
        when(HibernateStore.instOf().findUserByEmail(email)).thenReturn(user);
        User actual = store.findUserByEmail("email");
        new AuthServlet().doPost(req, resp);
        assertEquals("email", user.getEmail());
    }


}