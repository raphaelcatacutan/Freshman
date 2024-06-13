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

    public int getStudentID() {
        return studentID.get();
    }

    public IntegerProperty studentIDProperty() {
        return studentID;
    }

    public String getStudentName() {
        return studentName.get();
    }

    public StringProperty studentNameProperty() {
        return studentName;
    }

    public String getProgramID() {
        return programID.get();
    }

    public StringProperty programIDProperty() {
        return programID;
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public int getBlock() {
        return block.get();
    }

    public IntegerProperty blockProperty() {
        return block;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
