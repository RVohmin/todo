package ru.job4j.todo.service;

import ru.job4j.todo.persistence.Task;
import ru.job4j.todo.persistence.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MockStore implements Store {
    private static final MockStore INST = new MockStore();


    private static final AtomicInteger TASK_ID = new AtomicInteger(0);
    private static final AtomicInteger USER_ID = new AtomicInteger(0);

    private final Map<Integer, Task> tasks = new ConcurrentHashMap<>();
    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private MockStore() {
        User user = new User(1, "Alex", "email", "password");
        tasks.put(1, new Task(1, "Junior Java Job", Timestamp.valueOf(LocalDateTime.now()), true,
                user));
        users.put(1, user);
    }

    public static MockStore instOf() {
        return INST;
    }

    @Override
    public void saveTask(Task task) {

    }

    @Override
    public Collection<Task> findAllTasks(int id) {
        return null;
    }

    @Override
    public void updateTask(String id, boolean done) {

    }

    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }
}
