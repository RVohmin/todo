package ru.job4j.todo.controller;

import ru.job4j.todo.persistence.User;
import ru.job4j.todo.service.HibernateStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ru.job4j.dream.servlet
 *
 * @author romanvohmin
 * @since 04.08.2020
 */


public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sc = req.getSession();
        sc.removeAttribute("user");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = HibernateStore.instOf().findUserByEmail(email);
        System.out.println(user);
        if (user != null) {
            if (!user.getPassword().equals(password)) {
                req.setAttribute("error", "Не верный email или пароль");
                System.out.println("неверный пароль");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/index.do");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");

        }
    }
}
