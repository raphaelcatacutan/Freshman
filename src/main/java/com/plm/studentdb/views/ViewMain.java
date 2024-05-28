package com.plm.studentdb.views;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ViewMain {
    @FXML StackPane stkViews;

    @FXML AnchorPane viewDashboard;
    @FXML AnchorPane viewStudents;
    @FXML AnchorPane viewSettings;


    @FXML ImageView navDashboard;
    @FXML ImageView navStudents;
    @FXML ImageView navSettings;

    @FXML VBox vbxNavIndicator;

    AnchorPane[] views;
    AnchorPane currentView = viewDashboard;

    @FXML void initialize() {
        views = new AnchorPane[]{viewDashboard, viewStudents, viewSettings};
        navigateDashboard();
    }

    @FXML void navigateDashboard() {
        navigationBarTransition(-53);
        navigationViewTransition(viewDashboard);
    }

    @FXML void navigateStudents() {
        navigationBarTransition(0);
        navigationViewTransition(viewStudents);
    }

    @FXML void navigateSettings() {
        navigationBarTransition(53);
        navigationViewTransition(viewSettings);
    }

    void navigationBarTransition(double y) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), vbxNavIndicator);
        transition.setToY(y);
        transition.play();
    }

    void navigationViewTransition(AnchorPane view) {
        for (AnchorPane v: views) {
            if (v == view) {
                if (v == currentView) continue;
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), v);
                fadeIn.setOnFinished(ev -> {
                    v.toFront();
                    v.setOpacity(1);
                });
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);
                fadeIn.setDelay(Duration.seconds(0.5));
                fadeIn.play();
            } else {
                if (v != currentView) {
                    v.toBack();
                    v.setOpacity(0);
                    continue;
                };
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3), v);
                fadeOut.setOnFinished(ev -> {
                    v.toBack();
                    v.setOpacity(0);
                });
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.play();
            }
        }
        currentView = view;


    }


}