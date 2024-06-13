package com.plm.studentdb.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static List<Student> generateStudentObservable(ResultSet resultSet) throws SQLException {
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            int studentID = resultSet.getInt("StudentID");
            String studentName = resultSet.getString("StudentName");
            String programID = resultSet.getString("ProgramID");
            int year = resultSet.getInt("Year");
            int block = resultSet.getInt("Block");
            String email = resultSet.getString("Email");
            String password = resultSet.getString("Password");

            Student student = new Student(studentID, studentName, programID, year, block, email, password);
            students.add(student);
        }
        return students;
    }


    public static List<Account> generateAccountObservable(ResultSet resultSet) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        while (resultSet.next()) {
            int accountID = resultSet.getInt("AccountID");
            String name = resultSet.getString("Name");
            String email = resultSet.getString("Email");
            String password = resultSet.getString("Password");
            String access = resultSet.getString("Access");

            Account account = new Account(accountID, name, email, password, access);
            accounts.add(account);
        }
        return accounts;
    }


    public static List<Course> generateCourseObservable(ResultSet resultSet) throws SQLException {
        List<Course> courses = new ArrayList<>();
        while (resultSet.next()) {
            String courseID = resultSet.getString("CourseID");
            String courseName = resultSet.getString("CourseName");
            int year = resultSet.getInt("Year");
            int semester = resultSet.getInt("Semester");
            int units = resultSet.getInt("Units");
            int sections = resultSet.getInt("Sections");
            int capacity = resultSet.getInt("Capacity");

            Course course = new Course(courseID, courseName, year, semester, units, sections, capacity);
            courses.add(course);
        }
        return courses;
    }

    public static List<Lesson> generateLessonObservable(ResultSet resultSet) throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        while (resultSet.next()) {
            int lessonID = resultSet.getInt("LessonID");
            int studentID = resultSet.getInt("StudentID");
            String courseID = resultSet.getString("CourseID");
            int section = resultSet.getInt("Section");
            double grade = resultSet.getDouble("Grade");

            Lesson lesson = new Lesson(lessonID, studentID, courseID, section, grade);
            lessons.add(lesson);
        }
        return lessons;
    }

    public static List<Program> generateProgramObservable(ResultSet resultSet) throws SQLException {
        List<Program> programs = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String collegeName = resultSet.getString("college_name");
            String program = resultSet.getString("program");

            Program college = new Program(id, collegeName, program);
            programs.add(college);
        }
        return programs;
    }

}
