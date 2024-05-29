package com.plm.studentdb.views;

import com.plm.studentdb.database.DBFind;
import com.plm.studentdb.database.DBRemove;
import com.plm.studentdb.database.DBView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class StudentsConfirm {
    @FXML AnchorPane anpStudentsConfirmView;
    public TextField txfStudentsSearch;


    @FXML void hideConfirmView() {
        anpStudentsConfirmView.setVisible(false);
    }

    @FXML void deleteStudent() {
        DBRemove.removeStudentRecord(ViewStudents.studentsListTable.getFirst().getStudentId());
        txfStudentsSearch.setText(null);
        ViewStudents.studentsListTable.setAll(DBView.viewStudentRecord());
        hideConfirmView();
    }
}
