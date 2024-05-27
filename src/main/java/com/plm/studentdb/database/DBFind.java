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
                String clg=rs.getString("college");
                String crs = rs.getString("course");
                int yr = rs.getInt("year");
                double firstsemgwa = rs.getDouble("first_sem_gwa");
                double secndsemgwa = rs.getDouble("second_sem_gwa");
                double gwa = rs.getDouble("total_gwa");
                String stats = rs.getString("status");
                int yrenrld = rs.getInt("year_enrolled");
                System.out.println("Student Record exists within the database!");
                System.out.println(ID + "\t\t" + name + "\t\t\t" + clg + "\t\t\t"+ crs + "\t\t\t" +
                        yr + "\t\t\t" + firstsemgwa + "\t\t\t\t" + "\t\t\t" + secndsemgwa + "\t\t\t\t" + gwa + "\t\t\t\t" + stats + "\t\t\t\t" + yrenrld);
            }

            else{
                System.out.println("Student ID does not exist!!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }
}