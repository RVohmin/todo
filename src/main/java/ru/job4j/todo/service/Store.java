package ru.job4j.todo.service;

import ru.job4j.todo.persistence.Task;
import ru.job4j.todo.persistence.User;

import java.util.List;

public interface Store {
    void saveTask(Task task);
    List findAllTaskByUserId(int id);
    void updateTask(String id, boolean done);

    User findUserById(int id);
    User findUserByEmail(String email);
    void saveUser(User user);
}
