package com.plm.studentdb.database;
import com.plm.studentdb.models.Course;
import com.plm.studentdb.models.Class;
import com.plm.studentdb.models.Mapper;
import com.plm.studentdb.models.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBFind {
    public static Student findStudentRecord(int studentId){
        // Check if the student ID exists in the database
        String selectQuery = "SELECT * FROM student WHERE student_id = ?";
        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)) {
            p.setInt(1, studentId);

            try (ResultSet rs = p.executeQuery();) {
                List<Student> students = Mapper.generateStudentObservable(rs);
                if (!students.isEmpty()) return students.getFirst();
                else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }

    public static List<Student> findStudentRecord(String studentInfo) {
        String selectQuery = "SELECT * FROM student WHERE ";

        if (studentInfo.contains("@")) {
            selectQuery += "email LIKE ?";
        } else if (studentInfo.matches("\\d-\\d")) {
            String[] parts = studentInfo.split("-");
            selectQuery += "year = ? AND block = ?";
        } else if (studentInfo.matches("\\d+")) {
            selectQuery += "student_id LIKE ?";
        } else {
            selectQuery += "(full_name LIKE ? OR program LIKE ?)";
        }

        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)) {
            if (studentInfo.contains("@")) {
                p.setString(1, "%" + studentInfo + "%");
            } else if (studentInfo.matches("\\d-\\d")) {
                String[] parts = studentInfo.split("-");
                p.setInt(1, Integer.parseInt(parts[0]));
                p.setInt(2, Integer.parseInt(parts[1]));
            } else if (studentInfo.matches("\\d+")) {
                p.setString(1, studentInfo + "%");
            } else {
                p.setString(1, "%" + studentInfo + "%");
                p.setString(2, "%" + studentInfo + "%");
            }

            try (ResultSet rs = p.executeQuery()) {
                List<Student> students = Mapper.generateStudentObservable(rs);
                return students;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student record: " + e.getMessage());
        }
    }

    public static List<Class> findClass(String student_number) {
        String selectQuery = "SELECT * FROM classes WHERE student_number = ?";

        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)) {
            p.setString(1, student_number);

            try (ResultSet rs = p.executeQuery()) {
                List<Class> enrolled = Mapper.generateClassObservable(rs);
                return enrolled;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching enrolled record: " + e.getMessage());
        }
    }

    public static List<Class> findClass(int student_id, int year, int semester) {
        String selectQuery = "SELECT * FROM classes WHERE student_number = ? AND year = ? AND semester = ?";

        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)) {
            p.setInt(1, student_id);
            p.setInt(2, year);
            p.setInt(3, semester);

            try (ResultSet rs = p.executeQuery()) {
                List<Class> enrolled = Mapper.generateClassObservable(rs);
                return enrolled;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching enrolled record: " + e.getMessage());
        }
    }

    public static List<Class> findClass(String code, int year, int semester) {
        String selectQuery = "SELECT * FROM classes WHERE course_code = ? AND year = ? AND semester = ?";

        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)) {
            p.setString(1, code);
            p.setInt(2, year);
            p.setInt(3, semester);

            try (ResultSet rs = p.executeQuery()) {
                List<Class> classes = Mapper.generateClassObservable(rs);
                return classes;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching enrolled record: " + e.getMessage());
        }
    }

    public static Class findClass(int id) {
        String selectQuery = "SELECT * FROM classes WHERE id = ?";

        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)) {
            p.setInt(1, id);

            try (ResultSet rs = p.executeQuery()) {
                List<Class> classes = Mapper.generateClassObservable(rs);
                return classes.getFirst();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching enrolled record: " + e.getMessage());
        }
    }

    public static List<Class> findClass(String student_number, String code, int year, int semester) {
        String selectQuery = "SELECT * FROM classes WHERE student_number = ? AND course_code = ? AND year = ? AND semester = ?";

        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)) {
            p.setString(1, student_number);
            p.setString(2, code);
            p.setInt(3, year);
            p.setInt(4,semester);

            try (ResultSet rs = p.executeQuery()) {
                List<Class> classes = Mapper.generateClassObservable(rs);
                return classes;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching enrolled record: " + e.getMessage());
        }
    }

    public static List<Course> findCourse(String courseCode) {
        String selectQuery = "SELECT * FROM course WHERE course_code = ?";

        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)) {
            p.setString(1, courseCode);

            try (ResultSet rs = p.executeQuery()) {
                List<Course> courses = Mapper.generateCourseObservable(rs);
                return courses;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching enrolled record: " + e.getMessage());
        }
    }

    public static List<Course> findLikeCourse(String courseCode) {
        String selectQuery = "SELECT * FROM course WHERE course_code LIKE ?";

        try (PreparedStatement p = DBConnection.getConnection().prepareStatement(selectQuery)) {
            p.setString(1, "%" + courseCode + "%");

            try (ResultSet rs = p.executeQuery()) {
                List<Course> courses = Mapper.generateCourseObservable(rs);
                return courses;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching enrolled record: " + e.getMessage());
        }
    }
}