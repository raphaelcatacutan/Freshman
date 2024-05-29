package com.plm.studentdb.database;
import com.plm.studentdb.models.Mapper;
import com.plm.studentdb.models.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DBFind {
    public static Student findStudentRecord(int studentId){
        // Check if the student ID exists in the database
        String selectQuery = "SELECT * FROM student_record WHERE student_id = ?";
        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)) {
            p.setInt(1, studentId);

            try (ResultSet rs = p.executeQuery();) {
                List<Student> students = Mapper.generateStudentObservable(rs);
                if (!students.isEmpty()) return students.getFirst();
                else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }
}