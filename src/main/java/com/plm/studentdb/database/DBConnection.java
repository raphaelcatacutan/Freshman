package com.plm.studentdb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DATABASE_NAME = "studentdb2";
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
                    "CourseID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "CourseName VARCHAR(255) NOT NULL, " +
                    "Year INT NOT NULL, " +
                    "Semester INT NOT NULL, " +
                    "Units INT NOT NULL, " +
                    "Sections INT NOT NULL, " +
                    "Capacity INT NOT NULL" +
                    ")";
            stmt.executeUpdate(createCourseTableQuery);
        }

        // Lessons
        try (Statement stmt = connection.createStatement()) {
            String createClassesTableQuery = "CREATE TABLE IF NOT EXISTS lessons (" +
                    "LessonID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "StudentID INT NOT NULL, " +
                    "CourseID INT NOT NULL, " +
                    "Section INT NOT NULL, " +
                    "Grade DECIMAL(5,2) NOT NULL, " +
                    "FOREIGN KEY (StudentID) REFERENCES students(StudentID) ON DELETE CASCADE, " +
                    "FOREIGN KEY (CourseID) REFERENCES course(CourseID) ON DELETE CASCADE" +
                    ")";
            stmt.executeUpdate(createClassesTableQuery);
        }

        // Accounts
        try (Statement stmt = connection.createStatement()) {
            String createAccountsTableQuery = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "AccountID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "Email VARCHAR(255) NOT NULL, " +
                    "Password VARCHAR(255) NOT NULL, " +
                    "Access VARCHAR(255) NOT NULL" +
                    ")";
            stmt.executeUpdate(createAccountsTableQuery);
        }

        // Programs
        try (Statement stmt = connection.createStatement()) {
            String createProgramsTableQuery = "CREATE TABLE IF NOT EXISTS programs (" +
                    "ProgramID VARCHAR(255) NOT NULL, " +
                    "ProgramName VARCHAR(255) NOT NULL, " +
                    "College VARCHAR(255) NOT NULL, " +
                    "PRIMARY KEY (ProgramID)" +
                    ")";
            stmt.executeUpdate(createProgramsTableQuery);
        }

        // Students
        try (Statement stmt = connection.createStatement()) {
            String createStudentTableQuery = "CREATE TABLE IF NOT EXISTS students (" +
                    "StudentID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "StudentName VARCHAR(255) NOT NULL, " +
                    "ProgramID VARCHAR(255) NOT NULL, " +
                    "Year INT NOT NULL, " +
                    "Block INT NOT NULL, " +
                    "Email VARCHAR(255) NOT NULL, " +
                    "Password VARCHAR(255) NOT NULL, " +
                    "FOREIGN KEY (ProgramID) REFERENCES programs(ProgramID) ON DELETE CASCADE" +
                    ")";
            stmt.executeUpdate(createStudentTableQuery);
        }
    }


    public static Connection getConnection() {
        return connection;
    }
}