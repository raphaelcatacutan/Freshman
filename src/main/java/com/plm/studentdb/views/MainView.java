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
                if (v != currentView) AppAnimations.navigateIn(v);
            } else {
                if (v == currentView) AppAnimations.navigateOut(v);
                else {
                    v.toBack();
                    v.setOpacity(0);
                }
            }
        }
        currentView = view;
    }
}