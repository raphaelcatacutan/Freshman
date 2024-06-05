package com.plm.studentdb.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static List<Student> generateStudentObservable(ResultSet resultSet) throws SQLException {
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int studentId = resultSet.getInt("student_id");
            String name = resultSet.getString("name");
            String program = resultSet.getString("program");
            int year = resultSet.getInt("year");
            int block = resultSet.getInt("block");
            String email = resultSet.getString("email");

            Student student = new Student(id, studentId, name, program, year, block, email);
            students.add(student);
        }
        return students;
    }

}
