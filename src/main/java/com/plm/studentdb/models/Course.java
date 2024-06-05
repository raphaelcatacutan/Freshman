package com.plm.studentdb.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {
    private final IntegerProperty id;
    private final StringProperty courseCode;
    private final IntegerProperty units;
    private final IntegerProperty sections;
    private final StringProperty courseName;

    public Course(int id, String courseCode, int units, int sections, String courseName) {
        this.id = new SimpleIntegerProperty(id);
        this.courseCode = new SimpleStringProperty(courseCode);
        this.units = new SimpleIntegerProperty(units);
        this.sections = new SimpleIntegerProperty(sections);
        this.courseName = new SimpleStringProperty(courseName);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getCourseCode() {
        return courseCode.get();
    }

    public StringProperty courseCodeProperty() {
        return courseCode;
    }

    public int getUnits() {
        return units.get();
    }

    public IntegerProperty unitsProperty() {
        return units;
    }

    public int getSections() {
        return sections.get();
    }

    public IntegerProperty sectionsProperty() {
        return sections;
    }

    public String getCourseName() {
        return courseName.get();
    }

    public StringProperty courseNameProperty() {
        return courseName;
    }
}