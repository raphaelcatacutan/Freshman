package com.plm.studentdb.database;

import com.plm.studentdb.models.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBEdit {
    public static Student editStudentRecord(int id, int studentId, String name, String program, int year, int block, String email) {
        String updateQuery = "UPDATE studentdb.student SET student_id=?, full_name=?, program=?, year=?, block=?, email=? WHERE id=?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(updateQuery)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, name);
            pstmt.setString(3, program);
            pstmt.setInt(4, year);
            pstmt.setInt(5, block);
            pstmt.setString(6, email);
            pstmt.setInt(7, id);
            pstmt.executeUpdate();

            Student student = new Student(id, studentId, name, program, year, block, email);
            return student;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Account editAccountRecord(int id, String username, String password, String programAccess) {
        String updateQuery = "UPDATE accounts SET username=?, password=?, program_access=? WHERE id=?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(updateQuery)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, programAccess);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();

            Account account = new Account(id, username, password, programAccess);
            return account;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Course editCourseRecord(int id, String courseCode, int units, int sections, String courseName) {
        String updateQuery = "UPDATE course SET course_code=?, units=?, sections=?, course_name=? WHERE id=?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(updateQuery)) {
            pstmt.setString(1, courseCode);
            pstmt.setInt(2, units);
            pstmt.setInt(3, sections);
            pstmt.setString(4, courseName);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();

            Course course = new Course(id, courseCode, units, sections, courseName);
            return course;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Enrolled editEnrolledRecord(int id, String studentNumber, String courseCode, int section, int year, double grade) {
        String updateQuery = "UPDATE enrolled SET student_number=?, course_code=?, section=?, year=?, grade=? WHERE id=?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(updateQuery)) {
            pstmt.setString(1, studentNumber);
            pstmt.setString(2, courseCode);
            pstmt.setInt(3, section);
            pstmt.setInt(4, year);
            pstmt.setDouble(5, grade);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();

            Enrolled enrolled = new Enrolled(id, studentNumber, courseCode, section, year, grade);
            return enrolled;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Program editProgramRecord(int id, String collegeName, String program) {
        String updateQuery = "UPDATE college SET college_name=?, program=? WHERE id=?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(updateQuery)) {
            pstmt.setString(1, collegeName);
            pstmt.setString(2, program);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();

            Program college = new Program(id, collegeName, program);
            return college;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}