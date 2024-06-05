package com.plm.studentdb.views;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainView {
    @FXML StackPane stkViews;
    @FXML Pane pneNavigationIndicator;

    @FXML Parent viewDashboard;
    @FXML Parent viewStudents;
    @FXML Parent viewCurriculum;
    @FXML Parent viewReports;
    @FXML Parent viewSettings;

    @FXML AnchorPane anpViewDashboard;
    @FXML AnchorPane anpViewStudents;
    @FXML AnchorPane anpViewCurriculum;
    @FXML AnchorPane anpViewReports;
    @FXML AnchorPane anpViewSettings;

    @FXML ViewDashboard viewDashboardController;
    @FXML ViewStudents viewStudentsController;
    @FXML ViewCurriculum viewCurriculumController;
    @FXML ViewReports viewReportsController;
    @FXML ViewSettings viewSettingsController;

    AnchorPane[] views = new AnchorPane[3];
    AnchorPane currentView = anpViewDashboard;

    @FXML void initialize() {
        views = new AnchorPane[]{anpViewDashboard, anpViewStudents, anpViewCurriculum, anpViewReports, anpViewSettings};
        navigateDashboard();

        viewDashboardController.stkDashboardQuickAdd.setOnMouseClicked(ev -> {
            navigateStudents();
            //AppAnimations.popup(viewStudentsController.studentsAddView, 1);
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
        //if (viewStudentsController.studentsAddView.isVisible()) AppAnimations.popdown(viewStudentsController.studentsAddView, 0);
        AppAnimations.navigationBarTransition(pneNavigationIndicator, -260);
    }

    @FXML void navigateStudents() {
        navigationViewTransition(anpViewStudents);
        AppAnimations.navigationBarTransition(pneNavigationIndicator, -132);
    }

    @FXML void navigateCurriculum() {
        navigationViewTransition(anpViewCurriculum);
        AppAnimations.navigationBarTransition(pneNavigationIndicator, -3);
    }

    @FXML void navigateReports() {
        navigationViewTransition(anpViewReports);
        AppAnimations.navigationBarTransition(pneNavigationIndicator, 128);
    }

    @FXML void navigateSettings() {
        navigationViewTransition(anpViewSettings);
        AppAnimations.navigationBarTransition(pneNavigationIndicator, 257);
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