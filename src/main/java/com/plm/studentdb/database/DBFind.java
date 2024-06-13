package com.plm.studentdb.database;
import com.plm.studentdb.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBFind {
    public static List<Course> findCourses(Integer courseID, String courseName, Integer year, Integer semester, Integer units, Integer sections, Integer capacity, String query) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM course WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (courseID != null) {
            sql.append("AND CourseID = ? ");
            params.add(courseID);
        }
        if (courseName != null) {
            sql.append("AND CourseName = ? ");
            params.add(courseName);
        }
        if (year != null) {
            sql.append("AND Year = ? ");
            params.add(year);
        }
        if (semester != null) {
            sql.append("AND Semester = ? ");
            params.add(semester);
        }
        if (units != null) {
            sql.append("AND Units = ? ");
            params.add(units);
        }
        if (sections != null) {
            sql.append("AND Sections = ? ");
            params.add(sections);
        }
        if (capacity != null) {
            sql.append("AND Capacity = ? ");
            params.add(capacity);
        }

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) param);
                } else if (param instanceof String) {
                    stmt.setString(i + 1, (String) param);
                }
            }

            ResultSet resultSet = stmt.executeQuery();
            return Mapper.generateCourseObservable(resultSet);
        }
    }

    public static List<Lesson> findLessons(Integer lessonID, Integer studentID, Integer courseID, Integer section, Double grade, String query) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM lessons WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (lessonID != null) {
            sql.append("AND LessonID = ? ");
            params.add(lessonID);
        }
        if (studentID != null) {
            sql.append("AND StudentID = ? ");
            params.add(studentID);
        }
        if (courseID != null) {
            sql.append("AND CourseID = ? ");
            params.add(courseID);
        }
        if (section != null) {
            sql.append("AND Section = ? ");
            params.add(section);
        }
        if (grade != null) {
            sql.append("AND Grade = ? ");
            params.add(grade);
        }

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) param);
                } else if (param instanceof Double) {
                    stmt.setDouble(i + 1, (Double) param);
                }
            }

            ResultSet resultSet = stmt.executeQuery();
            return Mapper.generateLessonObservable(resultSet);
        }
    }

    public static List<Student> findStudents(Integer studentID, String studentName, String programID, Integer year, Integer block, String email, String password, String query) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM students WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (studentID != null) {
            sql.append("AND StudentID = ? ");
            params.add(studentID);
        }
        if (studentName != null) {
            sql.append("AND StudentName = ? ");
            params.add(studentName);
        }
        if (programID != null) {
            sql.append("AND ProgramID = ? ");
            params.add(programID);
        }
        if (year != null) {
            sql.append("AND Year = ? ");
            params.add(year);
        }
        if (block != null) {
            sql.append("AND Block = ? ");
            params.add(block);
        }
        if (email != null) {
            sql.append("AND Email = ? ");
            params.add(email);
        }
        if (password != null) {
            sql.append("AND Password = ? ");
            params.add(password);
        }

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) param);
                } else if (param instanceof String) {
                    stmt.setString(i + 1, (String) param);
                }
            }

            ResultSet resultSet = stmt.executeQuery();
            return Mapper.generateStudentObservable(resultSet);
        }
    }

    public static List<Account> findAccounts(Integer accountID, String name, String email, String password, String access, String query) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM accounts WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (accountID != null) {
            sql.append("AND AccountID = ? ");
            params.add(accountID);
        }
        if (name != null) {
            sql.append("AND Name = ? ");
            params.add(name);
        }
        if (email != null) {
            sql.append("AND Email = ? ");
            params.add(email);
        }
        if (password != null) {
            sql.append("AND Password = ? ");
            params.add(password);
        }
        if (access != null) {
            sql.append("AND Access = ? ");
            params.add(access);
        }

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) param);
                } else if (param instanceof String) {
                    stmt.setString(i + 1, (String) param);
                }
            }

            ResultSet resultSet = stmt.executeQuery();
            return Mapper.generateAccountObservable(resultSet);
        }
    }

    public static List<Program> findPrograms(String programID, String programName, String college, String query) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM programs WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (programID != null) {
            sql.append("AND ProgramID = ? ");
            params.add(programID);
        }
        if (programName != null) {
            sql.append("AND ProgramName = ? ");
            params.add(programName);
        }
        if (college != null) {
            sql.append("AND College = ? ");
            params.add(college);
        }

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof String) {
                    stmt.setString(i + 1, (String) param);
                }
            }

            ResultSet resultSet = stmt.executeQuery();
            return Mapper.generateProgramObservable(resultSet);
        }
    }


    public static List<Student> findStudents(String query) {
        String selectQuery = "SELECT * FROM students WHERE ";

        if (query.contains("@")) {
            selectQuery += "Email LIKE ?";
        } else if (query.matches("\\d+-\\d+")) {
            String[] parts = query.split("-");
            selectQuery += "Year = ? AND Block = ?";
        } else if (query.matches("\\d+")) {
            selectQuery += "StudentID LIKE ?";
        } else {
            selectQuery += "(StudentName LIKE ? OR ProgramID LIKE ?)";
        }

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement p = connection.prepareStatement(selectQuery)) {

            if (query.contains("@")) {
                p.setString(1, "%" + query + "%");
            } else if (query.matches("\\d+-\\d+")) {
                String[] parts = query.split("-");
                p.setInt(1, Integer.parseInt(parts[0]));
                p.setInt(2, Integer.parseInt(parts[1]));
            } else if (query.matches("\\d+")) {
                p.setString(1, query + "%");
            } else {
                p.setString(1, "%" + query + "%");
                p.setString(2, "%" + query + "%");
            }

            try (ResultSet rs = p.executeQuery()) {
                return Mapper.generateStudentObservable(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }
}