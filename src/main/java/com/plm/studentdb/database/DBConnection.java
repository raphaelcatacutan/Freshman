package com.plm.studentdb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DATABASE_NAME = "studentdb";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            try (Statement stmt = connection.createStatement()) {
                String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
                stmt.executeUpdate(createDatabaseQuery);
            }

            connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);

            initializeTables();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initializeTables() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS studentdb.student_record (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "student_id INT, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "college VARCHAR(100) NOT NULL, " +
                    "course VARCHAR(100) NOT NULL, " +
                    "year INT NOT NULL, " +
                    "first_sem_gwa DECIMAL(4, 2) NOT NULL, " +
                    "second_sem_gwa DECIMAL(4, 2) NOT NULL, " +
                    "total_gwa DECIMAL(4, 2) NOT NULL, " +
                    "status VARCHAR(20) NOT NULL, " +
                    "year_enrolled INT NOT NULL)";
            stmt.executeUpdate(createTableQuery);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        if (DBConnection.getConnection() != null) {
            System.out.println("Connection to database established successfully!");
        } else {
            System.out.println("Failed to establish connection to database.");
        }
        DBAdd.addStudentRecord(202334038, "Trisha Lau", "CISTM", "BSCS", 1, 2.5, 4, 2023);
        DBAdd.addStudentRecord(202334039, "Trisha Mae Lau", "CISTM", "BSIT", 1, 1.5, 1.75, 2023);
    }
}