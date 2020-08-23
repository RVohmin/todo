package ru.job4j.todo.controller;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import ru.job4j.todo.persistence.Task;
import ru.job4j.todo.persistence.User;
import ru.job4j.todo.service.HibernateStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskServlet extends HttpServlet {
    public final static Logger LOGGER = Logger.getLogger(TaskServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession sc = req.getSession();
        User user = (User) sc.getAttribute("user");
            List<Task> list;
        if (user != null) {
            list = new ArrayList<>(HibernateStore.instOf().findAllTasks(user.getId()));
        String json = new Gson().toJson(list);
        LOGGER.info(json);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = new PrintWriter(resp.getOutputStream());
        out.println(json);
        out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOGGER.info("in doPost descText = " + req.getParameter("describe"));
        String descText = req.getParameter("describe");

        LOGGER.info("id = " + req.getParameter("id"));
        String id = req.getParameter("id");

        LOGGER.info("done = " + req.getParameter("done"));
        boolean done = Boolean.parseBoolean((req.getParameter("done")));
        if (Integer.parseInt(id) != 0) {
            HibernateStore.instOf().updateTask(id, done);
            return;
        }
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = localDate.format(formatter);
        Timestamp timestamp = Timestamp.valueOf(localDate);
        LOGGER.info("Дата: " + date);

        HttpSession sc = req.getSession();
        User user = (User) sc.getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        LOGGER.info("user = " + user);

        Task task = new Task(Integer.parseInt(id), descText, timestamp, true, user);
        HibernateStore.instOf().saveTask(task);
    }

}
