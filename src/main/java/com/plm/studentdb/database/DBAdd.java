package com.plm.studentdb.database;

import com.plm.studentdb.models.Student;

import java.sql.*;

public class DBAdd {

    public static Student addStudentRecord(int studentId, String name, String college, String course, int year, double firstSemGwa, double secondSemGwa, int yearEnrolled) {
        double totalGwa = (firstSemGwa + secondSemGwa) / 2;
        String status = totalGwa <= 3.00 ? "Regular" : "Irregular";
        String insertQuery = "INSERT INTO studentdb.student_record (student_id, name, college, course, year, first_sem_gwa, second_sem_gwa, total_gwa, status, year_enrolled) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, name);
            pstmt.setString(3, college);
            pstmt.setString(4, course);
            pstmt.setInt(5, year);
            pstmt.setDouble(6, firstSemGwa);
            pstmt.setDouble(7, secondSemGwa);
            pstmt.setDouble(8, totalGwa);
            pstmt.setString(9, status);
            pstmt.setInt(10, yearEnrolled);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                rs.next();
                int id = rs.getInt(1);  // Fetch the generated ID
                Student student = new Student(id, studentId, name, college, course, year, firstSemGwa, secondSemGwa, totalGwa, status, yearEnrolled);
                return student;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addGrade(int studentId, int subjectId, double grade) {
        String insertGradeQuery = "INSERT INTO studentdb.grades (student_id, subject_id, grade) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(insertGradeQuery)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, subjectId);
            pstmt.setDouble(3, grade);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
