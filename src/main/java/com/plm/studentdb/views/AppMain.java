package com.plm.studentdb.views;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class AppMain {

    @FXML private Parent mainLogin;
    @FXML private Parent mainView;
    @FXML private Parent mainMessage;
    @FXML private Parent mainConfirm;

    @FXML private MainLogin mainLoginController;
    @FXML private MainView mainViewController;
    @FXML private MainMessage mainMessageController;
    @FXML private MainConfirm mainConfirmController;

    @FXML public Pane pneBackgroundFade;

    public void initialize() {
        mainLoginController.mainView = mainView;
        mainLoginController.pneBackgroundFade = pneBackgroundFade;
        mainLoginController.mainMessageController = mainMessageController;
    }

}
