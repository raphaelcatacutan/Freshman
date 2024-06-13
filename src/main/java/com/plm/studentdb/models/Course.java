package com.plm.studentdb.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {
    private final IntegerProperty courseID;
    private final StringProperty courseName;
    private final IntegerProperty year;
    private final IntegerProperty semester;
    private final IntegerProperty units;
    private final IntegerProperty sections;
    private final IntegerProperty capacity;

    public Course(int courseID, String courseName, int year, int semester, int units, int sections, int capacity) {
        this.courseID = new SimpleIntegerProperty(courseID);
        this.courseName = new SimpleStringProperty(courseName);
        this.year = new SimpleIntegerProperty(year);
        this.semester = new SimpleIntegerProperty(semester);
        this.units = new SimpleIntegerProperty(units);
        this.sections = new SimpleIntegerProperty(sections);
        this.capacity = new SimpleIntegerProperty(capacity);
    }

    public int getCourseID() {
        return courseID.get();
    }

    public IntegerProperty courseIDProperty() {
        return courseID;
    }

    public String getCourseName() {
        return courseName.get();
    }

    public StringProperty courseNameProperty() {
        return courseName;
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public int getSemester() {
        return semester.get();
    }

    public IntegerProperty semesterProperty() {
        return semester;
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

    public int getCapacity() {
        return capacity.get();
    }

    public IntegerProperty capacityProperty() {
        return capacity;
    }
}