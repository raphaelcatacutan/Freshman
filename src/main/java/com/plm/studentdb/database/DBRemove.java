package com.plm.studentdb.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBRemove {

    public static void removeStudentRecord(int studentId) {
        String selectQuery = "SELECT * FROM studentdb.student_record WHERE student_id = ?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(selectQuery)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Student record not found.");
                return;
            }

            // Remove the record from the database
            String deleteQuery = "DELETE FROM studentdb.student_record WHERE student_id = ?";
            try (PreparedStatement deletePstmt = DBConnection.getConnection().prepareStatement(deleteQuery)) {
                deletePstmt.setInt(1, studentId);
                deletePstmt.executeUpdate();
                System.out.println("Record deleted successfully.");
            } catch (SQLException e) {
                throw new RuntimeException("Error deleting student record: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }
}