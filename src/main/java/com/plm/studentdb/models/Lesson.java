package com.plm.studentdb.models;

import javafx.beans.property.*;

public class Lesson {
    private final IntegerProperty lessonID;
    private final IntegerProperty studentID;
    private final StringProperty courseID;
    private final IntegerProperty section;
    private final DoubleProperty grade;

    public Lesson(int lessonID, int studentID, String courseID, int section, double grade) {
        this.lessonID = new SimpleIntegerProperty(lessonID);
        this.studentID = new SimpleIntegerProperty(studentID);
        this.courseID = new SimpleStringProperty(courseID);
        this.section = new SimpleIntegerProperty(section);
        this.grade = new SimpleDoubleProperty(grade);
    }

    public int getLessonID() {
        return lessonID.get();
    }

    public IntegerProperty lessonIDProperty() {
        return lessonID;
    }

    public int getStudentID() {
        return studentID.get();
    }

    public IntegerProperty studentIDProperty() {
        return studentID;
    }

    public String getCourseID() {
        return courseID.get();
    }

    public StringProperty courseIDProperty() {
        return courseID;
    }

    public int getSection() {
        return section.get();
    }

    public IntegerProperty sectionProperty() {
        return section;
    }

    public double getGrade() {
        return grade.get();
    }

    public DoubleProperty gradeProperty() {
        return grade;
    }
}
