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
            String name = resultSet.getString("full_name");
            String program = resultSet.getString("program");
            int year = resultSet.getInt("year");
            int block = resultSet.getInt("block");
            String email = resultSet.getString("email");

            Student student = new Student(id, studentId, name, program, year, block, email);
            students.add(student);
        }
        return students;
    }

    public static List<Account> generateAccountObservable(ResultSet resultSet) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String programAccess = resultSet.getString("program_access");

            Account account = new Account(id, username, password, programAccess);
            accounts.add(account);
        }
        return accounts;
    }

    public static List<Course> generateCourseObservable(ResultSet resultSet) throws SQLException {
        List<Course> courses = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String courseCode = resultSet.getString("course_code");
            int units = resultSet.getInt("units");
            int sections = resultSet.getInt("sections");
            String courseName = resultSet.getString("course_name");

            Course course = new Course(id, courseCode, units, sections, courseName);
            courses.add(course);
        }
        return courses;
    }

    public static List<Class> generateClassObservable(ResultSet resultSet) throws SQLException {
        List<Class> enrolledList = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String studentNumber = resultSet.getString("student_number");
            String courseCode = resultSet.getString("course_code");
            int section = resultSet.getInt("section");
            int year = resultSet.getInt("year");
            int semester = resultSet.getInt("semester");
            double grade = resultSet.getDouble("grade");

            Class enrolled = new Class(id, studentNumber, courseCode, section, year, semester, grade);
            enrolledList.add(enrolled);
        }
        return enrolledList;
    }

    public static List<Program> generateProgramObservable(ResultSet resultSet) throws SQLException {
        List<Program> programs = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String collegeName = resultSet.getString("college_name");
            String program = resultSet.getString("program");

            Program college = new Program(id, collegeName, program);
            programs.add(college);
        }
        return programs;
    }

}
