package com.plm.studentdb.database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBFind {
    public static void findStudentRecord(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID to find within the database: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a valid student ID (e.g. 202312345)");
            scanner.nextLine();
            return;
        }

        int studentId = scanner.nextInt();
        scanner.nextLine();

        // Check if the student ID exists in the database
        String selectQuery = "SELECT * FROM student_record WHERE student_id = ?";
        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)) {
            p.setInt(1, studentId);
            ResultSet rs = p.executeQuery();
            if(rs.next()) {
                int ID = rs.getInt("student_id");
                String name = rs.getString("name");
                String crs = rs.getString("course");
                int yr = rs.getInt("year");
                double finGrade = rs.getDouble("final_grade");
                double gwa = rs.getDouble("gwa");
                String stats = rs.getString("status");
                System.out.println("Student Record exists within the database!");
                System.out.println("Student ID: " + ID + "\nStudent Name: " + name + "\nCourse and Year: " + crs + yr +
                        "\nFinal Grade: " + finGrade + "\nGWA: " + gwa + "\nStatus: " + stats);
            }

            else{
                System.out.println("Student ID does not exist!!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }
}