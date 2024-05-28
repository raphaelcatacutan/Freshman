package com.plm.studentdb.views;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class StudentsAdd {

    @FXML AnchorPane anpStudentsAddView;

    @FXML void closeAddView() {
        anpStudentsAddView.setVisible(false);
    }
}
