package ru.job4j.todo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.todo.persistence.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.job4j.todo.controller.TaskServlet.LOGGER;


public class HibernateStore implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private HibernateStore() {
    }

    private static final class Lazy {
        private static final Store INST = new HibernateStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public void saveTask(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Collection<Task> findAllTasks() {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Task");
        List<Task> tasks = new ArrayList<Task>(query.list());
        session.getTransaction().commit();
        session.close();
        return tasks;
    }

    @Override
    public void updateTask(String id, boolean done) {
        LOGGER.info("updateTask done = " + done);
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Task s set s.done = :newDone where s.id = :fId");
        query.setParameter("fId", Integer.parseInt(id));
        query.setParameter("newDone", done);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
