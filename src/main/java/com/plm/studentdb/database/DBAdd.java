package com.plm.studentdb.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBAdd {
    public static void addStudentRecord(int studentId, String name, String course, int year, double finalGrade, double gwa, String status) {
        String insertQuery = "INSERT INTO studentdb.student_record (student_id, name, course, year, final_grade, gwa, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(insertQuery)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, name);
            pstmt.setString(3, course);
            pstmt.setInt(4, year);
            pstmt.setDouble(5, finalGrade);
            pstmt.setDouble(6, gwa);
            pstmt.setString(7, status);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
