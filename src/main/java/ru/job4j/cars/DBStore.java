package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.function.Function;

/**
 * DBStore.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class DBStore implements Store {

    /**
     * An instance of DBStore.
     */
    private static final DBStore STORE = new DBStore();

    /**
     * An instance of SessionFactory.
     */
    private final SessionFactory sf = new Configuration().configure().buildSessionFactory();

    private DBStore() {
    }

    public static DBStore getInstance() {
        return STORE;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = this.sf.openSession();
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

    /**
     * The method adds an element to the storage.
     * @param elem an element to add.
     */
    @Override
    public <K> void add(K elem) {
        this.tx(session -> session.save(elem));
    }

    /**
     * The method updates an element.
     * @param elem an element to update.
     */
    @Override
    public <K> void update(K elem) {
        this.tx(session -> {
                    session.update(elem);
                    return null;
                }
        );
    }

    /**
     * The method deletes an element.
     * @param elem an element to delete.
     */
    @Override
    public <K> void delete(K elem) {
        this.tx(session -> {
            session.delete(elem);
            return null;
        });
    }

    /**
     * The method returns an element with the specified id.
     * @param id element ID.
     * @param className
     * @return an element with the specified id.
     */
    @Override
    public <K> K getElem(int id, String className) {
        K elem = null;
        try {
            Class cl = Class.forName("ru.job4j.cars.models." + className);
            elem = (K) this.tx(session -> session.get(cl, id));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return elem;
    }

    /**
     * The method returns a list of elements.
     * @param className
     * @return a list of elements.
     */
    @Override
    public <K> List<K> getList(String className) {
        String str = "from ru.job4j.cars.models." + className;
        return this.tx(session -> session.createQuery(str).list());
    }

    /**
     * The method returns a list of elements with the specified name.
     * @param name element name.
     * @param className
     * @param column
     * @return a list of elements with the specified name.
     */
    @Override
    public <K> List<K> getElementsByName(String name, String className, String column) {
        StringBuilder query = new StringBuilder();
        query.append("from ru.job4j.cars.models.").append(className).append(" where ").append(column).append(" = :name");
        return this.tx(session -> session.createQuery(query.toString()).setString("name", name).list());
    }
}