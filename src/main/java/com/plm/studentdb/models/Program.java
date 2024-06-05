package com.plm.studentdb.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Program {
    private final IntegerProperty id;
    private final StringProperty collegeName;
    private final StringProperty program;

    public Program(int id, String collegeName, String program) {
        this.id = new SimpleIntegerProperty(id);
        this.collegeName = new SimpleStringProperty(collegeName);
        this.program = new SimpleStringProperty(program);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getCollegeName() {
        return collegeName.get();
    }

    public StringProperty collegeNameProperty() {
        return collegeName;
    }

    public String getProgram() {
        return program.get();
    }

    public StringProperty programProperty() {
        return program;
    }
}