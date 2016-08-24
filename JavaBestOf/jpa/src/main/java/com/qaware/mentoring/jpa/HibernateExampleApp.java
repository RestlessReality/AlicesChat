package com.qaware.mentoring.jpa;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Simple example for using JPA with Hibernate.
 * The Hibernate configuration is located in the resource folder in a file named hibernate.cfg.xml.
 * The program reads data from a CSV file and inserts it into an embedded Derby database.
 */
public class HibernateExampleApp {

    /* CSV to read the person data from */
    private static final String PERSONS_CSV = "/persons.csv";

    /* Query String to get all persons. Note: Person is spelled like the Java class, not the table name */
    private static final String QUERY_STRING = "FROM Person";

    /* Makes Hibernate logging work with SLF4J */
    static {
        System.setProperty("org.jboss.logging.provider", "slf4j");
    }

    /**
     * Runs the program.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        SessionFactory factory = createSessionFactory();
        CsvPersonReader reader = new CsvPersonReader(new File(HibernateExampleApp.class.getResource(PERSONS_CSV).getFile()));
        List<Integer> ids = reader.getPersons().stream()
                .map(person -> insert(factory, person))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        ids.forEach(System.out::println);
        queryAll(factory).forEach(System.out::println);
    }

    /**
     * Creates the session factory.
     * Notifies Hibernate that there is an annotated class {@link Person}.
     *
     * @return the factory
     */
    private static SessionFactory createSessionFactory() {
        try {
            return new Configuration().
                    configure().
                    addAnnotatedClass(Person.class).
                    buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Inserts a {@link Person} into the DB.
     *
     * @param factory the session factory
     * @param person  the person to be inserted
     * @return the Optional containing the ID if insertion was successful, an empty Optional otherwise
     */
    private static Optional<Integer> insert(SessionFactory factory, Person person) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Integer id = (Integer) session.save(person);
            transaction.commit();
            return Optional.of(id);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();
    }

    /**
     * Returns all {@link Person}s from the DB.
     *
     * @param factory the session factory
     * @return the persons
     */
    private static List<Person> queryAll(SessionFactory factory) {
        List<Person> persons = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Query<Person> query = session.createQuery(QUERY_STRING, Person.class);
            persons.addAll(query.list());
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return persons;
    }

}
