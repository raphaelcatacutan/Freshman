package com.plm.studentdb.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBRemove {
    public static void removeStudentRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID to delete: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid student ID.");
            scanner.nextLine();
            return;
        }

        int studentId = scanner.nextInt();
        scanner.nextLine();

        // Check if the student ID exists in the database
        String selectQuery = "SELECT * FROM student_record WHERE student_id = ?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(selectQuery)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Student ID not found.");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }

        // If record found, proceed with deletion
        String deleteQuery = "DELETE FROM student_record WHERE student_id = ?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(deleteQuery)) {
            pstmt.setInt(1, studentId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("Error: Record could not be deleted.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting student record: " + e.getMessage());
        }
    }
}