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
            while(rs.next()){
                int ID = rs.getInt("student_id");
                String name = rs.getString("name");
                String crs = rs.getString("course");
                int yr = rs.getInt("year");
                double finGrade = rs.getDouble("final_grade");
                double gwa = rs.getDouble("gwa");
                String stats = rs.getString("status");
                System.out.println(ID + "\t\t" + name + "\t\t\t" + crs + "\t\t\t" + yr + "\t\t" + finGrade + "\t\t" + gwa + "\t\t" + stats);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }
}
