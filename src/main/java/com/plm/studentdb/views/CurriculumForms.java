package com.plm.studentdb.views;

import com.plm.studentdb.models.Course;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class CurriculumForms {
    @FXML AnchorPane anpCurriculumForms;

    @FXML
    void closeForms() {
        preFillForm(null);
        AppAnimations.popdown(anpCurriculumForms, 0);
    }

    void preFillForm(Course course) {

    }
}
