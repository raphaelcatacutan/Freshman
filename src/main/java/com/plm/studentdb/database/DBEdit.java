package com.plm.studentdb.database;

import com.plm.studentdb.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBEdit {
    public static Course editCourse(String courseID, String courseName, int year, int semester, int units, int sections, int capacity) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE course SET CourseName = ?, Year = ?, Semester = ?, Units = ?, Sections = ?, Capacity = ? WHERE CourseID = ?")) {

            stmt.setString(1, courseName);
            stmt.setInt(2, year);
            stmt.setInt(3, semester);
            stmt.setInt(4, units);
            stmt.setInt(5, sections);
            stmt.setInt(6, capacity);
            stmt.setString(7, courseID);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Editing course failed, no rows affected.");
            }

            return new Course(courseID, courseName, year, semester, units, sections, capacity);
        }
    }

    public static Lesson editLesson(int lessonID, int studentID, String courseID, int section, double grade) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE lessons SET StudentID = ?, CourseID = ?, Section = ?, Grade = ? WHERE LessonID = ?")) {

            stmt.setInt(1, studentID);
            stmt.setString(2, courseID);
            stmt.setInt(3, section);
            stmt.setDouble(4, grade);
            stmt.setInt(5, lessonID);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Editing lesson failed, no rows affected.");
            }

            return new Lesson(lessonID, studentID, courseID, section, grade);
        }
    }

    public static Student editStudent(int studentID, String studentName, String programID, int year, int block, String email, String password) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE students SET StudentName = ?, ProgramID = ?, Year = ?, Block = ?, Email = ?, Password = ? WHERE StudentID = ?")) {

            stmt.setString(1, studentName);
            stmt.setString(2, programID);
            stmt.setInt(3, year);
            stmt.setInt(4, block);
            stmt.setString(5, email);
            stmt.setString(6, password);
            stmt.setInt(7, studentID);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Editing student failed, no rows affected.");
            }

            return new Student(studentID, studentName, programID, year, block, email, password);
        }
    }

    public static Account editAccount(int accountID, String name, String email, String password, String access) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE accounts SET Name = ?, Email = ?, Password = ?, Access = ? WHERE AccountID = ?")) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, access);
            stmt.setInt(5, accountID);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Editing account failed, no rows affected.");
            }

            return new Account(accountID, name, email, password, access);
        }
    }

    public static Program editProgram(String programID, String programName, String college) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE programs SET ProgramName = ?, College = ? WHERE ProgramID = ?")) {

            stmt.setString(1, programName);
            stmt.setString(2, college);
            stmt.setString(3, programID);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Editing program failed, no rows affected.");
            }

            return new Program(programID, programName, college);
        }
    }
}
