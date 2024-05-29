package com.plm.studentdb.database;

import com.plm.studentdb.models.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBEdit {
    public static Student editStudentRecord(int studentId, String name, String college, String course, int year, double firstSemGWA, double secondSemGWA, int yearEnrolled) {
        String selectQuery = "SELECT * FROM studentdb.student_record WHERE student_id = ?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(selectQuery)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            int id;

            if (!rs.next()) {
                System.out.println("Student record not found.");
                return null;
            } else {
                id = rs.getInt("id");
            }

            double totalGWA = (firstSemGWA + secondSemGWA) / 2;
            String status = totalGWA <= 3.00 ? "Regular" : "Irregular";

            String updateQuery = "UPDATE studentdb.student_record SET name = ?, college = ?, course = ?, year = ?, first_sem_gwa = ?, second_sem_gwa = ?, total_gwa = ?, status = ?, year_enrolled = ? WHERE student_id = ?";
            try (PreparedStatement updatePstmt = DBConnection.getConnection().prepareStatement(updateQuery)) {
                updatePstmt.setString(1, name);
                updatePstmt.setString(2, college);
                updatePstmt.setString(3, course);
                updatePstmt.setInt(4, year);
                updatePstmt.setDouble(5, firstSemGWA);
                updatePstmt.setDouble(6, secondSemGWA);
                updatePstmt.setDouble(7, totalGWA);
                updatePstmt.setString(8, status);
                updatePstmt.setInt(9, yearEnrolled);
                updatePstmt.setInt(10, studentId);
                updatePstmt.executeUpdate();

                return new Student(id, studentId, name, college, course, year, firstSemGWA, secondSemGWA, totalGWA, status, yearEnrolled);
            } catch (SQLException e) {
                throw new RuntimeException("Error updating student record: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }
}