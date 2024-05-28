package com.plm.studentdb.models;

public class Student {
    private int id;
    private int studentId;
    private String name;
    private String college;
    private String course;
    private int year;
    private double firstSemGwa;
    private double secondSemGwa;
    private double totalGwa;
    private String status;
    private int yearEnrolled;

    // Constructor
    public Student(int id, int studentId, String name, String college, String course, int year, double firstSemGwa, double secondSemGwa, double totalGwa, String status, int yearEnrolled) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.college = college;
        this.course = course;
        this.year = year;
        this.firstSemGwa = firstSemGwa;
        this.secondSemGwa = secondSemGwa;
        this.totalGwa = totalGwa;
        this.status = status;
        this.yearEnrolled = yearEnrolled;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getFirstSemGwa() {
        return firstSemGwa;
    }

    public void setFirstSemGwa(double firstSemGwa) {
        this.firstSemGwa = firstSemGwa;
    }

    public double getSecondSemGwa() {
        return secondSemGwa;
    }

    public void setSecondSemGwa(double secondSemGwa) {
        this.secondSemGwa = secondSemGwa;
    }

    public double getTotalGwa() {
        return totalGwa;
    }

    public void setTotalGwa(double totalGwa) {
        this.totalGwa = totalGwa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getYearEnrolled() {
        return yearEnrolled;
    }

    public void setYearEnrolled(int yearEnrolled) {
        this.yearEnrolled = yearEnrolled;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", college='" + college + '\'' +
                ", course='" + course + '\'' +
                ", year=" + year +
                ", firstSemGwa=" + firstSemGwa +
                ", secondSemGwa=" + secondSemGwa +
                ", totalGwa=" + totalGwa +
                ", status='" + status + '\'' +
                ", yearEnrolled=" + yearEnrolled +
                '}';
    }
}
