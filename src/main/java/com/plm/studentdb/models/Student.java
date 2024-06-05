package com.plm.studentdb.models;

import javafx.beans.property.*;

public class Student {
    private final IntegerProperty id;
    private final IntegerProperty studentId;
    private final StringProperty name;
    private final StringProperty program;
    private final IntegerProperty year;
    private final IntegerProperty block;
    private final StringProperty email;

    public Student(IntegerProperty id, IntegerProperty studentId, StringProperty name, StringProperty program, IntegerProperty year, IntegerProperty block, StringProperty email) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.program = program;
        this.year = year;
        this.block = block;
        this.email = email;
    }

    public Student(int id, int studentId, String name, String program, int year, int block, String email) {
        this.id = new SimpleIntegerProperty(id);
        this.studentId = new SimpleIntegerProperty(studentId);
        this.name = new SimpleStringProperty(name);
        this.program = new SimpleStringProperty(program);
        this.year = new SimpleIntegerProperty(year);
        this.block = new SimpleIntegerProperty(block);
        this.email = new SimpleStringProperty(email);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getStudentId() {
        return studentId.get();
    }

    public IntegerProperty studentIdProperty() {
        return studentId;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getProgram() {
        return program.get();
    }

    public StringProperty programProperty() {
        return program;
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
}

