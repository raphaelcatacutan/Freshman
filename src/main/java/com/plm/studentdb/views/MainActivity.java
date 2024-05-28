package com.plm.studentdb.views;

import javafx.fxml.FXML;
import javafx.scene.Parent;

public class MainActivity {

    @FXML private Parent viewLogin;
    @FXML private Parent viewMain;

    @FXML private ViewLogin viewLoginController;
    @FXML private ViewMain viewMainController;

    public void initialize() {
        viewLoginController.setViewMain(viewMain);
    }

}
