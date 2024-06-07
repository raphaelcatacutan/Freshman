package com.plm.studentdb.views;

import com.plm.studentdb.database.DBRemove;
import com.plm.studentdb.database.DBView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MainConfirm {
    @FXML AnchorPane anpStudentsConfirmView;
    @FXML Label mainConfirmTitle;
    @FXML Label mainConfirmContent;

    public Runnable runThis;


    @FXML void show(String title, String content, Runnable action) {
        mainConfirmTitle.setText(title);
        mainConfirmContent.setText(content);
        runThis = action;
        AppAnimations.popup(anpStudentsConfirmView, 0);
    }

    @FXML void action() {
        runThis.run();
        hideConfirmView();
    }

    @FXML void hideConfirmView() {
        AppAnimations.popdown(anpStudentsConfirmView, 0);
    }
}
