package ru.job4j.todo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.persistence.Task;
import ru.job4j.todo.persistence.User;

import javax.persistence.Query;
import java.util.List;
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
        tx(session -> session.save(task));
    }

    @Override
    public List findAllTaskByUserId(int id) {
        return tx(session -> session.createQuery("from Task t where t.user.id = " + id).list());
//        tx(
//                session -> {
//                    Query query = session.createQuery(
//                            "select item.id, item.describe, item.created, " +
//                                    "item.done, item.user.name from Task as item");
//                    query.executeUpdate();
//                    List list = query.list();
//                    LOGGER.info("list " + list.size());
//                    return list;
//                }
//        );
//        return tx;
    }

    @Override
    public void updateTask(String id, boolean done) {
        tx(
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
    public User findUserById(int id) {
        return tx(
                session -> {
                    final org.hibernate.query.Query<?> query = session.createQuery(
                            "from Task s where s.id = :fId");
                    query.setParameter("fId", id);
                    return (User) query.uniqueResult();
                }
        );
    }

    @Override
    public User findUserByEmail(String email) {
        return tx(session -> {
            Query query = session.createQuery("from User s where s.email = :fEmail");
            query.setParameter("fEmail", email);
            User user = (User) query.getSingleResult();
            if (user == null) {
                return null;
            }
            return user.getEmail().equals(email) ? user : null;
        });
    }

    @Override
    public void saveUser(User user) {
        tx(session -> session.save(user));
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
