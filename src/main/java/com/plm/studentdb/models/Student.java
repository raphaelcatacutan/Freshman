package com.plm.studentdb.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final IntegerProperty studentID;
    private final StringProperty studentName;
    private final StringProperty programID;
    private final IntegerProperty year;
    private final IntegerProperty block;
    private final StringProperty email;
    private final StringProperty password;

    public Student(int studentID, String studentName, String programID, int year, int block, String email, String password) {
        this.studentID = new SimpleIntegerProperty(studentID);
        this.studentName = new SimpleStringProperty(studentName);
        this.programID = new SimpleStringProperty(programID);
        this.year = new SimpleIntegerProperty(year);
        this.block = new SimpleIntegerProperty(block);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
    }
}
