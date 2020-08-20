package ru.job4j.todo.service;

import ru.job4j.todo.persistence.Task;

import java.util.Collection;

public interface Store {
    void saveTask(Task task);
    Collection<Task> findAllTasks();
    void updateTask(String id, boolean done);
}
