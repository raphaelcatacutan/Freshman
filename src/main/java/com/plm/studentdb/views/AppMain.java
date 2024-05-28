package com.plm.studentdb.views;

import javafx.fxml.FXML;
import javafx.scene.Parent;

public class AppMain {

    @FXML private Parent viewLogin;
    @FXML private Parent viewMain;

    @FXML private ViewLogin viewLoginController;
    @FXML private MainView viewMainController;

    public void initialize() {
        viewLoginController.setViewMain(viewMain);
    }

}
