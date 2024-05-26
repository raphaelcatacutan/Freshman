package com.plm.studentdb.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBEdit {
    public static void editStudentRecord() {
        Scanner scanner = new Scanner(System.in);
        int studentId;

        // Input validation for student ID
        while (true) {
            System.out.print("Enter student ID to edit: ");
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

            // Prompt user to edit fields
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new college: ");
            String newCollege = scanner.nextLine();
            System.out.print("Enter new course: ");
            String newCourse = scanner.nextLine();

            int newYear;
            while (true) {
                System.out.print("Enter new year: ");
                try {
                    newYear = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer for year.");
                }
            }

            double newFirstSemGWA;
            while (true) {
                System.out.print("Enter new first semester GWA: ");
                try {
                    newFirstSemGWA = Double.parseDouble(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number for first semester GWA.");
                }
            }

            double newSecondSemGWA;
            while (true) {
                System.out.print("Enter new second semester GWA: ");
                try {
                    newSecondSemGWA = Double.parseDouble(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number for second semester GWA.");
                }
            }

            double newTotalGWA = (newFirstSemGWA + newSecondSemGWA) / 2;
            String newStatus = newTotalGWA <= 3.00 ? "Regular" : "Irregular";

            int newYearEnrolled;
            while (true) {
                System.out.print("Enter new year enrolled: ");
                try {
                    newYearEnrolled = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer for year enrolled.");
                }
            }

            // Update record in the MYSQL database
            String updateQuery = "UPDATE studentdb.student_record SET name = ?, college = ?, course = ?, year = ?, first_sem_gwa = ?, second_sem_gwa = ?, total_gwa = ?, status = ?, year_enrolled = ? WHERE student_id = ?";
            try (PreparedStatement updatePstmt = DBConnection.getConnection().prepareStatement(updateQuery)) {
                updatePstmt.setString(1, newName);
                updatePstmt.setString(2, newCollege);
                updatePstmt.setString(3, newCourse);
                updatePstmt.setInt(4, newYear);
                updatePstmt.setDouble(5, newFirstSemGWA);
                updatePstmt.setDouble(6, newSecondSemGWA);
                updatePstmt.setDouble(7, newTotalGWA);
                updatePstmt.setString(8, newStatus);
                updatePstmt.setInt(9, newYearEnrolled);
                updatePstmt.setInt(10, studentId);
                updatePstmt.executeUpdate();
                System.out.println("Record updated successfully.");
            } catch (SQLException e) {
                throw new RuntimeException("Error updating student record: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        editStudentRecord();
    }
}