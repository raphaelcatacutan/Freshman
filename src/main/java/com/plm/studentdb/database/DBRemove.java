package com.plm.studentdb.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBRemove {

    public static void removeCourse(int courseID) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM course WHERE CourseID = ?")) {

            stmt.setInt(1, courseID);
            stmt.executeUpdate();
        }
    }

    public static void removeLesson(int lessonID) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM lessons WHERE LessonID = ?")) {

            stmt.setInt(1, lessonID);
            stmt.executeUpdate();
        }
    }

    public static void removeStudent(int studentID) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM students WHERE StudentID = ?")) {

            stmt.setInt(1, studentID);
            stmt.executeUpdate();
        }
    }

    public static void removeAccount(int accountID) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM accounts WHERE AccountID = ?")) {

            stmt.setInt(1, accountID);
            stmt.executeUpdate();
        }
    }

    public static void removeProgram(String programID) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM programs WHERE ProgramID = ?")) {

            stmt.setString(1, programID);
            stmt.executeUpdate();
        }
    }
}
