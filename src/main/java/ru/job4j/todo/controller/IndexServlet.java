package ru.job4j.todo.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.job4j.todo.controller.TaskServlet.LOGGER;

/**
 * ru.job4j.dream.servlet
 *
 * @author romanvohmin
 * @since 29.07.2020
 */
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("------- In IndexServlet ------------");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
