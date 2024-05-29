package com.plm.studentdb.database;

import com.plm.studentdb.models.Mapper;
import com.plm.studentdb.models.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBRemove {

    public static Student removeStudentRecord(int studentId) {
        String selectQuery = "SELECT * FROM studentdb.student_record WHERE student_id = ?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(selectQuery)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Student record not found.");
                return null;
            }

            // Remove the record from the database
            String deleteQuery = "DELETE FROM studentdb.student_record WHERE student_id = ?";
            try (PreparedStatement deletePstmt = DBConnection.getConnection().prepareStatement(deleteQuery)) {
                deletePstmt.setInt(1, studentId);
                deletePstmt.executeUpdate();
                System.out.println("Record deleted successfully.");
                List<Student> students = Mapper.generateStudentObservable(rs);
                if (!students.isEmpty()) return students.getFirst();
                else return null;
            } catch (SQLException e) {
                throw new RuntimeException("Error deleting student record: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }
}