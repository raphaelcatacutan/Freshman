package com.plm.studentdb.database;

import com.plm.studentdb.models.Student;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBEdit {
    public static Student editStudentRecord(int id, int studentId, String name, String program, int year, int block, String email) {
        String updateQuery = "UPDATE studentdb.student SET studentId=?, name=?, program=?, year=?, block=?, email=? WHERE id=?";
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

}