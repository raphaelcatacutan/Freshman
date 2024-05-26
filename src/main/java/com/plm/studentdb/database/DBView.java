package com.plm.studentdb.database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBView {
    public static void viewStudentRecord(){
        String selectQuery = "SELECT * FROM student_record";
        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)){
            p.getConnection().prepareStatement(selectQuery);
            ResultSet rs=p.executeQuery();
            System.out.println("\tID" + "\t\t\t" + "\t  Name" + "\t\t\t\t" + "   Course" + "\t\t" +
                    "   Year" + "\t\t" + " Final Grade" + "\t\t" + "    GWA" + "\t\t\t\t" + "Status");
            while(rs.next()){
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
                System.out.println(ID + "\t\t" + name + "\t\t\t" + clg + "\t\t\t"+ crs + "\t\t\t" +
                        yr + "\t\t\t" + firstsemgwa + "\t\t\t\t" + "\t\t\t" + secndsemgwa + "\t\t\t\t" + gwa + "\t\t\t\t" + stats + "\t\t\t\t" + yrenrld);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }
}
