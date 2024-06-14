package com.plm.studentdb.views;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainView {
    @FXML AnchorPane anpMainView;
    @FXML StackPane stkViews;
    @FXML Pane pneNavigationIndicator;

    @FXML Parent viewDashboard;
    @FXML Parent viewStudents;
    @FXML Parent viewCurriculum;
    @FXML Parent viewReports;
    @FXML Parent viewDatabase;

    @FXML AnchorPane anpViewDashboard;
    @FXML AnchorPane anpViewStudents;
    @FXML AnchorPane anpViewCurriculum;
    @FXML AnchorPane anpViewReports;
    @FXML AnchorPane anpViewDatabase;

    @FXML ViewDashboard viewDashboardController;
    @FXML ViewStudents viewStudentsController;
    @FXML ViewCurriculum viewCurriculumController;
    @FXML ViewReports viewReportsController;
    @FXML ViewDatabase viewDatabaseController;

    public Parent mainLogin;

    AnchorPane[] views = new AnchorPane[5];
    AnchorPane currentView = anpViewDashboard;

    @FXML void initialize() {
        views = new AnchorPane[]{anpViewDashboard, anpViewStudents, anpViewCurriculum, anpViewReports, anpViewDatabase};

        navigateDashboard();

        viewDashboardController.stkDashboardQuickAdd.setOnMouseClicked(ev -> {
            navigateStudents();
            viewStudentsController.studentsInformationController.showForms(null, 0.8, true);
        });
        viewDashboardController.stkDashboardQuickView.setOnMouseClicked(ev -> {
            navigateStudents();
        });
        viewDashboardController.stkDashboardQuickCourse.setOnMouseClicked(ev -> {
            navigateCurriculum();
            viewCurriculumController.curriculumFormsController.showForms(null, null, true, 0.8);
        });

    }

    @FXML void navigateDashboard() {
        navigationViewTransition(anpViewDashboard);
        if (viewStudentsController.studentsInformation.isVisible()) AppAnimations.popdown(viewStudentsController.studentsInformation, 0);
        AppAnimations.navigationBarTransition(pneNavigationIndicator, -260);
    }

    @FXML void navigateStudents() {
        navigationViewTransition(anpViewStudents);
        AppAnimations.navigationBarTransition(pneNavigationIndicator, -130);
    }

    @FXML void navigateCurriculum() {
        navigationViewTransition(anpViewCurriculum);
        AppAnimations.navigationBarTransition(pneNavigationIndicator, 0);
    }

    @FXML void navigateReports() {
        navigationViewTransition(anpViewReports);
        viewReportsController.onNavigate();
        AppAnimations.navigationBarTransition(pneNavigationIndicator, 130);
    }

    @FXML void navigateDatabase() {
        navigationViewTransition(anpViewDatabase);
        AppAnimations.navigationBarTransition(pneNavigationIndicator, 260);
    }

    @FXML void logout() {
        mainLogin.toFront();
        anpMainView.toBack();
        navigateDashboard();
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