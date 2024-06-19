package com.plm.studentdb.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBRemove {

    public static void removeCourse(String courseID) {
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement("DELETE FROM course WHERE CourseID = ?")) {

            stmt.setString(1, courseID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeLesson(int lessonID) {
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement("DELETE FROM lessons WHERE LessonID = ?")) {

            stmt.setInt(1, lessonID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeStudent(int studentID) {
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement("DELETE FROM students WHERE StudentID = ?")) {

            stmt.setInt(1, studentID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeAccount(int accountID) {
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement("DELETE FROM accounts WHERE AccountID = ?")) {

            stmt.setInt(1, accountID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeProgram(String programID) {
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement("DELETE FROM programs WHERE ProgramID = ?")) {

            stmt.setString(1, programID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
