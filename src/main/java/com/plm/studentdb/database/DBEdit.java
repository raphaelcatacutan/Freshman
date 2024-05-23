package com.plm.studentdb.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DBEdit {
    public static void editStudentRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID to edit: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        // Getting the existing record
        String selectQuery = "SELECT * FROM studentdb.student_record WHERE student_id = ?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(selectQuery)) {
            pstmt.setInt(1, studentId);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }

        // If record not found, handle appropriately
        // If record found, prompt user to edit fields
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new course: ");
        String newCourse = scanner.nextLine();
        System.out.print("Enter new year: ");
        int newYear = scanner.nextInt();
        System.out.print("Enter new final grade: ");
        double newFinalGrade = scanner.nextDouble();
        System.out.print("Enter new GWA: ");
        double newGWA = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter new status: ");
        String newStatus = scanner.nextLine();

        // Update record in the MYSQL database
        String updateQuery = "UPDATE studentdb.student_record SET name = ?, course = ?, year = ?, final_grade = ?, gwa = ?, status = ? WHERE student_id = ?";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(updateQuery)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, newCourse);
            pstmt.setInt(3, newYear);
            pstmt.setDouble(4, newFinalGrade);
            pstmt.setDouble(5, newGWA);
            pstmt.setString(6, newStatus);
            pstmt.setInt(7, studentId);
            pstmt.executeUpdate();
            System.out.println("Record updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating student record: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}