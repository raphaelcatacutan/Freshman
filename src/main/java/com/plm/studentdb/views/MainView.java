package com.plm.studentdb.views;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainView {
    @FXML StackPane stkViews;

    @FXML Parent viewDashboard;
    @FXML Parent viewStudents;

    @FXML AnchorPane anpViewDashboard;
    @FXML AnchorPane anpViewStudents;

    @FXML ViewDashboard viewDashboardController;
    @FXML ViewStudents viewStudentsController;

    AnchorPane[] views = new AnchorPane[3];
    AnchorPane currentView = anpViewDashboard;

    @FXML void initialize() {
        views = new AnchorPane[]{anpViewDashboard, anpViewStudents};
        navigateDashboard();

        viewDashboardController.stkDashboardQuickAdd.setOnMouseClicked(ev -> {
            navigateStudents();
            AppAnimations.popup(viewStudentsController.studentsAddView, 1);
        });
        viewDashboardController.stkDashboardQuickView.setOnMouseClicked(ev -> {
            navigateStudents();
        });
        viewStudentsController.studentsBack.setOnMouseClicked(ev -> {
            navigateDashboard();
        });
    }

    @FXML void navigateDashboard() {
        navigationViewTransition(anpViewDashboard);
        if (viewStudentsController.studentsAddView.isVisible()) AppAnimations.popdown(viewStudentsController.studentsAddView, 0);
    }

    @FXML void navigateStudents() {
        navigationViewTransition(anpViewStudents);
    }

    public void navigationViewTransition(AnchorPane view) {
        for (AnchorPane v: views) {
            if (v == view) {
                if (v == currentView) continue;

                // Create FadeTransition for fading in
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), v);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);
                fadeIn.setDelay(Duration.seconds(0.5));

                // Create TranslateTransition for floating in from below
                TranslateTransition translateIn = new TranslateTransition(Duration.seconds(0.5), v);
                translateIn.setFromY(100); // Start from below
                translateIn.setToY(0); // Move to original position
                translateIn.setDelay(Duration.seconds(0.5));

                // Play both transitions together
                fadeIn.setOnFinished(ev -> {
                    v.toFront();
                    v.setOpacity(1);
                });
                translateIn.play();
                fadeIn.play();

            } else {
                if (v != currentView) {
                    v.toBack();
                    v.setOpacity(0);
                    continue;
                }

                // Create FadeTransition for fading out
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), v);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);

                // Create TranslateTransition for floating out upwards
                TranslateTransition translateOut = new TranslateTransition(Duration.seconds(0.5), v);
                translateOut.setFromY(0); // Start from original position
                translateOut.setToY(-100); // Move upwards

                // Play both transitions together
                fadeOut.setOnFinished(ev -> {
                    v.toBack();
                    v.setOpacity(0);
                });
                translateOut.play();
                fadeOut.play();
            }
        }
        currentView = view;
    }
}