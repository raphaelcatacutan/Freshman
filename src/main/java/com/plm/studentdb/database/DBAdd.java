package com.plm.studentdb.database;

import com.plm.studentdb.models.Student;
import javafx.beans.property.IntegerProperty;

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
}
