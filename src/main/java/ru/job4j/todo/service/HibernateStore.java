package ru.job4j.todo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.todo.persistence.Task;

import java.util.Collection;
import java.util.function.Function;


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

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void saveTask(Task task) {
        this.tx(
                session -> {
                    session.createQuery("from Task");
                    session.save(task);
                    return null;
                }
        );
    }

    @Override
    public Collection<Task> findAllTasks() {
        return this.tx(
                session -> session.createQuery("from Task").list()
        );
    }

    @Override
    public void updateTask(String id, boolean done) {
        this.tx(
                session -> {
                    final Query query = session.createQuery(
                            "update Task s set s.done = :newDone where s.id = :fId");
                    query.setParameter("fId", Integer.parseInt(id));
                    query.setParameter("newDone", done);
                    query.executeUpdate();
                    return null;
                }
        );
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
