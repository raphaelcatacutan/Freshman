package com.plm.studentdb.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Lesson {
    private final IntegerProperty lessonID;
    private final IntegerProperty studentID;
    private final IntegerProperty courseID;
    private final IntegerProperty section;
    private final DoubleProperty grade;

    public Lesson(int lessonID, int studentID, int courseID, int section, double grade) {
        this.lessonID = new SimpleIntegerProperty(lessonID);
        this.studentID = new SimpleIntegerProperty(studentID);
        this.courseID = new SimpleIntegerProperty(courseID);
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

    public int getCourseID() {
        return courseID.get();
    }

    public IntegerProperty courseIDProperty() {
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
