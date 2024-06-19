package com.plm.studentdb.database;

import com.plm.studentdb.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAdd {
    public static Student addStudent(int studentID, String studentName, String programID, int year, int block, String email, String password) {
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement("INSERT INTO students (StudentID, StudentName, ProgramID, Year, Block, Email, Password) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setInt(1, studentID);
            stmt.setString(2, studentName);
            stmt.setString(3, programID);
            stmt.setInt(4, year);
            stmt.setInt(5, block);
            stmt.setString(6, email);
            stmt.setString(7, password);

            stmt.executeUpdate();

            return new Student(studentID, studentName, programID, year, block, email, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Account addAccount(String name, String email, String password, String access) {
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement("INSERT INTO accounts (Name, Email, Password, Access) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, access);

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int accountID = generatedKeys.getInt(1);
                return new Account(accountID, name, email, password, access);
            } else {
                throw new SQLException("Creating account failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Course addCourse(String courseID, String courseName, int year, int semester, int units, int sections, int capacity) {
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement("INSERT INTO course (CourseID, CourseName, Year, Semester, Units, Sections, Capacity) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, courseID);
            stmt.setString(2, courseName);
            stmt.setInt(3, year);
            stmt.setInt(4, semester);
            stmt.setInt(5, units);
            stmt.setInt(6, sections);
            stmt.setInt(7, capacity);

            stmt.executeUpdate();

            return new Course(courseID, courseName, year, semester, units, sections, capacity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Lesson addLesson(int studentID, String courseID, int section, double grade) {
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement("INSERT INTO lessons (StudentID, CourseID, Section, Grade) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, studentID);
            stmt.setString(2, courseID);
            stmt.setInt(3, section);
            stmt.setDouble(4, grade);

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int lessonID = generatedKeys.getInt(1);
                return new Lesson(lessonID, studentID, courseID, section, grade);
            } else {
                throw new SQLException("Creating lesson failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Program addProgram(String programID, String programName, String college) {
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement("INSERT INTO programs (ProgramID, ProgramName, College) VALUES (?, ?, ?)")) {

            stmt.setString(1, programID);
            stmt.setString(2, programName);
            stmt.setString(3, college);

            stmt.executeUpdate();

            return new Program(programID, programName, college);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
