package com.plm.studentdb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "Trisha";
    private static final String PASSWORD = "Trl527";
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
        // Courses
        try (Statement stmt = connection.createStatement()) {
            String createCourseTableQuery = "CREATE TABLE IF NOT EXISTS course (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "course_code VARCHAR(255) NOT NULL, " +
                    "units INT NOT NULL, " +
                    "sections INT NOT NULL, " +
                    "course_name VARCHAR(255) NOT NULL" +
                    ")";
            stmt.executeUpdate(createCourseTableQuery);
        }
        // Enrolled
        try (Statement stmt = connection.createStatement()) {
            String createEnrolledTableQuery = "CREATE TABLE IF NOT EXISTS enrolled (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "student_number VARCHAR(255) NOT NULL, " +
                    "course_code VARCHAR(255) NOT NULL, " +
                    "section INT NOT NULL, " +
                    "year INT NOT NULL, " +
                    "grade DECIMAL(5,2) NOT NULL" +
                    ")";
            stmt.executeUpdate(createEnrolledTableQuery);
        }
        // Accounts
        try (Statement stmt = connection.createStatement()) {
            String createAccountsTableQuery = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(255) NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "program_access VARCHAR(255) NOT NULL" +
                    ")";
            stmt.executeUpdate(createAccountsTableQuery);
        }
        // Colleges
        try (Statement stmt = connection.createStatement()) {
            String createCollegeTableQuery = "CREATE TABLE IF NOT EXISTS college (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "college_name VARCHAR(255) NOT NULL, " +
                    "program VARCHAR(255) NOT NULL" +
                    ")";
            stmt.executeUpdate(createCollegeTableQuery);
        }
        // Students
        try (Statement stmt = connection.createStatement()) {
            String createStudentTableQuery = "CREATE TABLE IF NOT EXISTS student (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "full_name VARCHAR(255) NOT NULL, " +
                    "student_id VARCHAR(255) UNIQUE NOT NULL, " +
                    "program VARCHAR(255) NOT NULL, " +
                    "year INT NOT NULL, " +
                    "block VARCHAR(255) NOT NULL, " +
                    "email VARCHAR(255) NOT NULL" +
                    ")";
            stmt.executeUpdate(createStudentTableQuery);
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
    }
}