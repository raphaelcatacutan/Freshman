package com.plm.studentdb.views;

import com.plm.studentdb.models.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class StudentsInformation {
    @FXML AnchorPane anpStudentsInformation;

    @FXML TextField txfStudentInformationName;
    @FXML TextField txfStudentInformationID;
    @FXML TextField txfStudentInformationCollege;
    @FXML TextField txfStudentInformationProgram;
    @FXML TextField txfStudentInformationYearBlock;

    public MainMessage mainMessageController;
    public boolean isAdding = true;

    @FXML
    void closeAddView() {
        AppAnimations.popdown(anpStudentsInformation, 0);
    }


    public void preFillForm(Student student) {
        if (student == null) {
            txfStudentInformationName.setText(null);
            txfStudentInformationID.setText(null);
            txfStudentInformationCollege.setText(null);
            txfStudentInformationProgram.setText(null);
            txfStudentInformationYearBlock.setText(null);
        } else {
            txfStudentInformationName.setText(student.getName());
            txfStudentInformationID.setText(String.valueOf(student.getStudentId()));
            txfStudentInformationCollege.setText(student.getCollege());
            txfStudentInformationProgram.setText(student.getCourse());
            txfStudentInformationYearBlock.setText(student.getYear() + "-1");
        }
    }
}
