package com.plm.studentdb.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBRemove {
    public static void removeStudentRecord() {
        Scanner scanner = new Scanner(System.in);
        int studentId;

        // Input validation for student ID
        while (true) {
            System.out.print("Enter student ID to remove: ");
            try {
                studentId = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for student ID.");
            }
        }

        // Getting the existing record
        String selectQuery = "SELECT * FROM studentdb.student_record WHERE student_id = ?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(selectQuery)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Student record not found.");
                return;
            }

            // Display the current record
            System.out.println("Current record:");
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("College: " + rs.getString("college"));
            System.out.println("Course: " + rs.getString("course"));
            System.out.println("Year: " + rs.getInt("year"));
            System.out.println("First Sem GWA: " + rs.getDouble("first_sem_gwa"));
            System.out.println("Second Sem GWA: " + rs.getDouble("second_sem_gwa"));
            System.out.println("Total GWA: " + rs.getDouble("total_gwa"));
            System.out.println("Status: " + rs.getString("status"));
            System.out.println("Year Enrolled: " + rs.getInt("year_enrolled"));

            // Confirm deletion
            System.out.print("Are you sure you want to delete this record? (yes/no): ");
            String confirmation = scanner.nextLine();
            if (!confirmation.equalsIgnoreCase("yes")) {
                System.out.println("Deletion cancelled.");
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