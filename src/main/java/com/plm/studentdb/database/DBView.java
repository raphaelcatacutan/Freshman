package com.plm.studentdb.database;
import com.plm.studentdb.models.Mapper;
import com.plm.studentdb.models.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBView {
    public static List<Student> viewStudentRecord() {
        String selectQuery = "SELECT * FROM student_record";
        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)){
            ResultSet resultSet = p.executeQuery();

            return Mapper.generateStudentObservable(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }
}
