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
            String college = resultSet.getString("college");
            String course = resultSet.getString("course");
            int year = resultSet.getInt("year");
            double firstSemGwa = resultSet.getDouble("first_sem_gwa");
            double secondSemGwa = resultSet.getDouble("second_sem_gwa");
            double totalGwa = resultSet.getDouble("total_gwa");
            String status = resultSet.getString("status");
            int yearEnrolled = resultSet.getInt("year_enrolled");

            Student student = new Student(id, studentId, name, college, course, year, firstSemGwa, secondSemGwa, totalGwa, status, yearEnrolled);
            students.add(student);
        }
        return students;
    }
}
