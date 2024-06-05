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
        String selectQuery = "SELECT * FROM student WHERE student_id = ?";
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

    public static List<Student> findStudentRecord(String studentInfo) {
        // Define the base query
        String selectQuery = "SELECT * FROM student WHERE ";

        // Determine the type of input and construct the query accordingly
        if (studentInfo.contains("@")) {
            // Filter by email with wildcard
            selectQuery += "email LIKE ?";
        } else if (studentInfo.matches("\\d-\\d")) {
            // Filter by year and block without wildcard
            String[] parts = studentInfo.split("-");
            selectQuery += "year = ? AND block = ?";
        } else if (studentInfo.matches("\\d+")) {
            // Filter by student_id with wildcard
            selectQuery += "student_id LIKE ?";
        } else {
            // Filter by full_name or program with wildcard
            selectQuery += "(full_name LIKE ? OR program LIKE ?)";
        }

        // Prepare and execute the query
        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)) {
            if (studentInfo.contains("@")) {
                p.setString(1, "%" + studentInfo + "%");
            } else if (studentInfo.matches("\\d-\\d")) {
                String[] parts = studentInfo.split("-");
                p.setInt(1, Integer.parseInt(parts[0]));
                p.setInt(2, Integer.parseInt(parts[1]));
            } else if (studentInfo.matches("\\d+")) {
                p.setString(1, studentInfo + "%");
            } else {
                p.setString(1, "%" + studentInfo + "%");
                p.setString(2, "%" + studentInfo + "%");
            }

            try (ResultSet rs = p.executeQuery()) {
                List<Student> students = Mapper.generateStudentObservable(rs);
                return students;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }


}