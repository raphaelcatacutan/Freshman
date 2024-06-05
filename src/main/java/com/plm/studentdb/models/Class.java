package com.plm.studentdb.models;

import javafx.beans.property.*;

public class Class {
    private final IntegerProperty id;
    private final StringProperty studentNumber;
    private final StringProperty courseCode;
    private final IntegerProperty section;
    private final IntegerProperty year;
    private final IntegerProperty semester;
    private final DoubleProperty grade;

    public Class(int id, String studentNumber, String courseCode, int section, int year, int semester, double grade) {
        this.id = new SimpleIntegerProperty(id);
        this.studentNumber = new SimpleStringProperty(studentNumber);
        this.courseCode = new SimpleStringProperty(courseCode);
        this.section = new SimpleIntegerProperty(section);
        this.year = new SimpleIntegerProperty(year);
        this.semester = new SimpleIntegerProperty(semester);
        this.grade = new SimpleDoubleProperty(grade);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getStudentNumber() {
        return studentNumber.get();
    }

    public StringProperty studentNumberProperty() {
        return studentNumber;
    }

    public String getCourseCode() {
        return courseCode.get();
    }

    public StringProperty courseCodeProperty() {
        return courseCode;
    }

    public int getSection() {
        return section.get();
    }

    public IntegerProperty sectionProperty() {
        return section;
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public double getGrade() {
        return grade.get();
    }

    public DoubleProperty gradeProperty() {
        return grade;
    }

    public int getSemester() {
        return semester.get();
    }

    public IntegerProperty semesterProperty() {
        return semester;
    }
}
