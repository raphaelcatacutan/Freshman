package com.plm.studentdb.database;
import com.plm.studentdb.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBView {
    public static List<Student> viewStudentRecord() {
        String selectQuery = "SELECT * FROM student";
        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)){
            ResultSet resultSet = p.executeQuery();

            return Mapper.generateStudentObservable(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }

    public static List<Account> viewAccountRecord() {
        String selectQuery = "SELECT * FROM accounts";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(selectQuery)) {
            ResultSet resultSet = pstmt.executeQuery();
            return Mapper.generateAccountObservable(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching account records: " + e.getMessage());
        }
    }

    public static List<Course> viewCourseRecord() {
        String selectQuery = "SELECT * FROM course";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(selectQuery)) {
            ResultSet resultSet = pstmt.executeQuery();
            return Mapper.generateCourseObservable(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching course records: " + e.getMessage());
        }
    }

    public static List<Class> viewClassRecord() {
        String selectQuery = "SELECT * FROM classes";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(selectQuery)) {
            ResultSet resultSet = pstmt.executeQuery();
            return Mapper.generateClassObservable(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching enrolled records: " + e.getMessage());
        }
    }

    public static List<Program> viewProgramRecord() {
        String selectQuery = "SELECT * FROM college";
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(selectQuery)) {
            ResultSet resultSet = pstmt.executeQuery();
            return Mapper.generateProgramObservable(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching program records: " + e.getMessage());
        }
    }
}
