package com.plm.studentdb.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBAdd {
    public static void addStudentRecord(int studentId, String name, String college, String course, int year, double firstSemGwa, double secondSemGwa, double totalGwa, String status, int yearEnrolled) {
        String insertQuery = "INSERT INTO studentdb.student_record (student_id, name, college, course, year, first_sem_gwa, second_sem_gwa, total_gwa, status, year_enrolled) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(insertQuery)) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
