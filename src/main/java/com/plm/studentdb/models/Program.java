package com.plm.studentdb.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Program {
    private final StringProperty programID;
    private final StringProperty programName;
    private final StringProperty college;

    public Program(String programID, String programName, String college) {
        this.programID = new SimpleStringProperty(programID);
        this.programName = new SimpleStringProperty(programName);
        this.college = new SimpleStringProperty(college);
    }

    public String getProgramID() {
        return programID.get();
    }

    public StringProperty programIDProperty() {
        return programID;
    }

    public String getProgramName() {
        return programName.get();
    }

    public StringProperty programNameProperty() {
        return programName;
    }

    public String getCollege() {
        return college.get();
    }

    public StringProperty collegeProperty() {
        return college;
    }
}
