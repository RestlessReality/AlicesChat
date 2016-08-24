package com.qaware.mentoring.jdbc;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * JDBC example app.
 * <p>
 * Required preconditions:
 * - MariaDB running on localhost on port 3306
 * - Connecting with user root without password must be allowed
 * - MariaDB must have a database named example_db
 * <p>
 * For further information please have a look at the README.txt
 * <p>
 * Note: in a real application you would not use the JDBC API directly.
 * You would probably use an object-relational mapper (ORM) like Hibernate
 * or a framework built on top of Hibernate like Spring.
 * <p>
 * Executing this program twice will insert the same records twice.
 */
public class JdbcExampleApp {

    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/example_db";
    private static final String USER = "root";
    private static final String PASSWORD = null;

    private static final Set<Person> persons = new HashSet<>();

    static {
        persons.add(new Person("Charles", "Darwin"));
        persons.add(new Person("Leonhard", "Euler"));
        persons.add(new Person("Alfred", "Nobel"));
    }

    public static void main(String[] args) throws Exception {

        Connection connection = null;
        Statement statement = null;
        try {
            loadJdbcDriver();
            connection = createConnection();

            statement = connection.createStatement();

            for (Person person : persons) {
                // Note: In a productive application you should never do this because
                // you allow SQL injection attacks if you are not using prepared statements.
                String insert = "INSERT INTO PERSON (first_name, surname) VALUES('" +
                        person.getFirstName() + "', '" + person.getSurname() + "')";
                System.out.println("Inserting " + person);
                statement.executeUpdate(insert);
            }

            String selectAll = "SELECT * FROM PERSON";
            ResultSet rs = statement.executeQuery(selectAll);
            System.out.println("Retrieving records from database...");

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String surname = rs.getString("surname");
                System.out.println(id + " " + firstName + " " + surname);
            }

            rs.close();
            statement.close();
            connection.close();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static Connection createConnection() throws SQLException {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static Class<?> loadJdbcDriver() throws ClassNotFoundException {
        try {
            return Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
