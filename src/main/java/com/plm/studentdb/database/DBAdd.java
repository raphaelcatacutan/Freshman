package com.plm.studentdb.database;

import com.plm.studentdb.models.*;
import com.plm.studentdb.models.Class;

import java.sql.*;

public class DBAdd {
    public static Student addStudentRecord(int studentId, String name, String program, int year, int block, String email) {
        String insertQuery = "INSERT INTO studentdb.student (student_id, full_name, program, year, block, email) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, name);
            pstmt.setString(3, program);
            pstmt.setInt(4, year);
            pstmt.setInt(5, block);
            pstmt.setString(6, email);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                rs.next();
                int id = rs.getInt("GENERATED_KEY");
                Student student = new Student(id, studentId, name, program, year, block, email);
                return student;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Account addAccountRecord(String username, String password, String programAccess) {
        String insertQuery = "INSERT INTO accounts (username, password, program_access) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, programAccess);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                rs.next();
                int id = rs.getInt(1);
                Account account = new Account(id, username, password, programAccess);
                return account;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Course addCourseRecord(String courseCode, int units, int sections, String courseName, int limit) {
        String insertQuery = "INSERT INTO course (course_code, units, sections, course_name, st_limit) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, courseCode);
            pstmt.setInt(2, units);
            pstmt.setInt(3, sections);
            pstmt.setString(4, courseName);
            pstmt.setInt(5, limit);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                rs.next();
                int id = rs.getInt(1);
                Course course = new Course(id, courseCode, units, sections, courseName, limit);
                return course;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Class addClassRecord(String studentNumber, String courseCode, int section, int year, int semester, double grade) {
        String insertQuery = "INSERT INTO classes (student_number, course_code, section, year, semester, grade) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, studentNumber);
            pstmt.setString(2, courseCode);
            pstmt.setInt(3, section);
            pstmt.setInt(4, year);
            pstmt.setInt(5, semester);
            pstmt.setDouble(6, grade);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                rs.next();
                int id = rs.getInt(1);
                Class enrolled = new Class(id, studentNumber, courseCode, section, year, semester, grade);
                return enrolled;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Program addProgramRecord(String collegeName, String program) {
        String insertQuery = "INSERT INTO college (college_name, program) VALUES (?, ?)";

        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, collegeName);
            pstmt.setString(2, program);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                rs.next();
                int id = rs.getInt(1);
                Program college = new Program(id, collegeName, program);
                return college;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
