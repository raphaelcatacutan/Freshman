package com.plm.studentdb.views;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainView {
    @FXML StackPane stkViews;

    @FXML Parent viewDashboard;
    @FXML Parent viewStudents;
    @FXML Parent viewSettings;

    @FXML ImageView navDashboard;
    @FXML ImageView navStudents;
    @FXML ImageView navSettings;

    @FXML ViewDashboard viewDashboardController;
    @FXML ViewStudents viewStudentsController;
    @FXML ViewSettings viewSettingsController;

    @FXML VBox vbxNavIndicator;

    Parent[] views = new Parent[3];
    Parent currentView = viewDashboard;

    @FXML void initialize() {
        views = new Parent[]{viewDashboard, viewStudents, viewSettings};
        navigateDashboard();

        viewDashboardController.vbxDashboardQuickAdd.setOnMouseClicked(ev -> {
            navigateStudents();
            viewStudentsController.studentsAddView.setVisible(true);
        });
    }

    @FXML void navigateDashboard() {
        navigationBarTransition(0);
        navigationViewTransition(viewDashboard);
    }

    @FXML void navigateStudents() {
        navigationBarTransition(53);
        navigationViewTransition(viewStudents);
    }

    @FXML void navigateSettings() {
        navigationBarTransition(106);
        navigationViewTransition(viewSettings);
    }

    void navigationBarTransition(double y) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), vbxNavIndicator);
        transition.setToY(y);
        transition.play();
    }

    void navigationViewTransition(Parent view) {
        for (Parent v: views) {
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