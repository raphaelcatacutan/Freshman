package com.plm.studentdb.views;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class StudentsMessage {
    @FXML public AnchorPane anpStudentsMessageView;
    @FXML public Label lblStudentsMessageTitle;
    @FXML public Label lblStudentsMessageContent;

    public void show(String title, String content) {
        lblStudentsMessageTitle.setText(title);
        lblStudentsMessageContent.setText(content);
        anpStudentsMessageView.setVisible(true);
    }
    public void close() {
        anpStudentsMessageView.setVisible(false);
    }
}
