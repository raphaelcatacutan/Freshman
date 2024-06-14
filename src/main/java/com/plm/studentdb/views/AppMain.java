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
        mainViewController.mainLogin = mainLogin;
        mainLoginController.pneBackgroundFade = pneBackgroundFade;
        Dialogs.mainMessageDialog = mainMessageController;
        Dialogs.mainConfirmDialog = mainConfirmController;

        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            throwable.printStackTrace();

            Dialogs.mainMessageDialog.show("Unexpected Error", "Make sure all the details you have entered are valid.");
        });

    }

}
