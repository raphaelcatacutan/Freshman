package com.plm.studentdb.models;

import javafx.beans.property.*;

public class Student {
    private final IntegerProperty id;
    private final IntegerProperty studentId;
    private final StringProperty name;
    private final StringProperty college;
    private final StringProperty course;
    private final IntegerProperty year;
    private final DoubleProperty firstSemGwa;
    private final DoubleProperty secondSemGwa;
    private final DoubleProperty totalGwa;
    private final StringProperty status;
    private final IntegerProperty yearEnrolled;

    // Constructor
    public Student(int id, int studentId, String name, String college, String course, int year, double firstSemGwa, double secondSemGwa, double totalGwa, String status, int yearEnrolled) {
        this.id = new SimpleIntegerProperty(id);
        this.studentId = new SimpleIntegerProperty(studentId);
        this.name = new SimpleStringProperty(name);
        this.college = new SimpleStringProperty(college);
        this.course = new SimpleStringProperty(course);
        this.year = new SimpleIntegerProperty(year);
        this.firstSemGwa = new SimpleDoubleProperty(firstSemGwa);
        this.secondSemGwa = new SimpleDoubleProperty(secondSemGwa);
        this.totalGwa = new SimpleDoubleProperty(totalGwa);
        this.status = new SimpleStringProperty(status);
        this.yearEnrolled = new SimpleIntegerProperty(yearEnrolled);
    }

    // Getters and Setters
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getStudentId() {
        return studentId.get();
    }

    public IntegerProperty studentIdProperty() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId.set(studentId);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCollege() {
        return college.get();
    }

    public StringProperty collegeProperty() {
        return college;
    }

    public void setCollege(String college) {
        this.college.set(college);
    }

    public String getCourse() {
        return course.get();
    }

    public StringProperty courseProperty() {
        return course;
    }

    public void setCourse(String course) {
        this.course.set(course);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public double getFirstSemGwa() {
        return firstSemGwa.get();
    }

    public DoubleProperty firstSemGwaProperty() {
        return firstSemGwa;
    }

    public void setFirstSemGwa(double firstSemGwa) {
        this.firstSemGwa.set(firstSemGwa);
    }

    public double getSecondSemGwa() {
        return secondSemGwa.get();
    }

    public DoubleProperty secondSemGwaProperty() {
        return secondSemGwa;
    }

    public void setSecondSemGwa(double secondSemGwa) {
        this.secondSemGwa.set(secondSemGwa);
    }

    public double getTotalGwa() {
        return totalGwa.get();
    }

    public DoubleProperty totalGwaProperty() {
        return totalGwa;
    }

    public void setTotalGwa(double totalGwa) {
        this.totalGwa.set(totalGwa);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public int getYearEnrolled() {
        return yearEnrolled.get();
    }

    public IntegerProperty yearEnrolledProperty() {
        return yearEnrolled;
    }

    public void setYearEnrolled(int yearEnrolled) {
        this.yearEnrolled.set(yearEnrolled);
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
