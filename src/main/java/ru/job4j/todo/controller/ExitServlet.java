package ru.job4j.todo.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.job4j.todo.controller.TaskServlet.LOGGER;

public class ExitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("------- In HttpServletRequest ------------");
        HttpSession sc = req.getSession();
        sc.removeAttribute("user");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
