package ru.job4j.todo.controller;

import ru.job4j.todo.persistence.User;
import ru.job4j.todo.service.HibernateStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ru.job4j.dream.servlet
 *
 * @author romanvohmin
 * @since 05.08.2020
 */
public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        if (HibernateStore.instOf().findUserByEmail(req.getParameter("email")) != null) {
            System.out.println("Пользователь с таким email уже зарегистрирован");
            resp.sendRedirect(req.getContextPath() + "/regErrorEmail.jsp");
            return;
        }
        User user = new User(0,
                req.getParameter("login"),
                req.getParameter("email"),
                req.getParameter("password"));
        HibernateStore.instOf().saveUser(user);
        HttpSession sc = req.getSession();
        sc.setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/index.do");
    }
}
