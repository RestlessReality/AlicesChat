package com.qaware.mentoring.jdbc;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Shows how to use prepared statements in order to avoid SQL injection attacks.
 */
public class PreparedStatementApp {


    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/example_db";
    private static final String USER = "root";
    private static final String PASSWORD = null;

    private static final Set<Person> persons = new HashSet<>();

    static {
        persons.add(new Person("Charles", "Darwin"));
        persons.add(new Person("Euler", "Leonhard"));
        persons.add(new Person("Alfred", "Nobel"));
    }

    public static void main(String[] args) throws Exception {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            loadJdbcDriver();
            connection = createConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO PERSON (first_name, surname) VALUES(?, ?)");

            for (Person person : persons) {
                preparedStatement.setString(1, person.getFirstName());
                preparedStatement.setString(2, person.getSurname());
                preparedStatement.execute();
            }

            String selectAll = "SELECT * FROM PERSON";
            ResultSet rs = preparedStatement.executeQuery(selectAll);
            System.out.println("Retrieving records from database...");

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String surname = rs.getString("surname");
                System.out.println(id + " " + firstName + " " + surname);
            }

            rs.close();
            preparedStatement.close();
            connection.close();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
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
