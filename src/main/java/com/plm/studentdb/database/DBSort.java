package com.plm.studentdb.database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBSort {
    public static void sortStudentRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Sort by student ID");
        System.out.println("2. Sort by name");
        System.out.println("3. Sort by course");
        System.out.println("4. Sort by year");
        System.out.println("5. Sort by final grade");
        System.out.println("6. Sort by GWA");
        System.out.println("7. Sort by status");
        System.out.print("Select sorting option:");
        int choice = scanner.nextInt();
        String order = getOrder(choice);
        if (order == null) {
            System.out.println("Invalid choice.");
            return;
        }
        String selectQuery = "SELECT * FROM student_record ORDER BY "+ order;
        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)) {
            p.getConnection().prepareStatement(selectQuery);
            ResultSet rs = p.executeQuery();
            System.out.println("\tID" + "\t\t\t" + "\t  Name" + "\t\t\t\t" + "   Course" + "\t\t" +
                    "   Year" + "\t\t" + " Final Grade" + "\t" + "    GWA" + "\t\t\t" + "Status");
            while (rs.next()) {
                int ID = rs.getInt("student_id");
                String name = rs.getString("name");
                String crs = rs.getString("course");
                int yr = rs.getInt("year");
                double finGrade = rs.getDouble("final_grade");
                double gwa = rs.getDouble("gwa");
                String stats = rs.getString("status");
                System.out.println(ID + "\t\t" + name + "\t\t\t" + crs + "\t\t\t" + yr + "\t\t\t" + finGrade + "\t\t\t" + gwa + "\t\t\t" + stats);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }

    }

    private static String getOrder(int choice) {
        switch (choice) {
            case 1:
                return "student_id";
            case 2:
                return "name";
            case 3:
                return "course";
            case 4:
                return "year";
            case 5:
                return "final_grade";
            case 6:
                return "gwa";
            case 7:
                return "status";
            default:
                return null;
        }
    }
}
