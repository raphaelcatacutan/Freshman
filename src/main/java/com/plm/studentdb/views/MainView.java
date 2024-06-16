package com.plm.studentdb.views;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainView {
    @FXML public AnchorPane anpMainView;
    @FXML public StackPane stkViews;
    @FXML public Pane pneNavigationIndicator;

    @FXML public Parent viewDashboard;
    @FXML public Parent viewStudents;
    @FXML public Parent viewCurriculum;
    @FXML public Parent viewReports;
    @FXML public Parent viewSettings;

    @FXML public AnchorPane anpViewDashboard;
    @FXML public AnchorPane anpViewStudents;
    @FXML public AnchorPane anpViewCurriculum;
    @FXML public AnchorPane anpViewReports;
    @FXML public AnchorPane anpViewSettings;

    @FXML public ViewDashboard viewDashboardController;
    @FXML public ViewStudents viewStudentsController;
    @FXML public ViewCurriculum viewCurriculumController;
    @FXML public ViewReports viewReportsController;
    @FXML public ViewSettings viewSettingsController;

    @FXML public HBox hbxNavigationBar;

    public Parent mainLogin;

    AnchorPane[] views = new AnchorPane[5];
    AnchorPane currentView = anpViewDashboard;
    AnchorPane lastView = anpViewDashboard;

    @FXML void initialize() {
        views = new AnchorPane[]{anpViewDashboard, anpViewStudents, anpViewCurriculum, anpViewReports, anpViewSettings};

        navigateDashboard();

        viewDashboardController.stkDashboardQuickAdd.setOnMouseClicked(ev -> {
            navigateStudents();
            viewStudentsController.studentsInformationController.showForms(null, 0.8, true, false);
        });
        viewDashboardController.stkDashboardQuickView.setOnMouseClicked(ev -> {
            navigateStudents();
        });
        viewDashboardController.stkDashboardQuickCourse.setOnMouseClicked(ev -> {
            navigateCurriculum();
            viewCurriculumController.curriculumFormsController.showForms(null, null, true, 0.8);
        });
        viewSettingsController.imvViewSettingsBack.setOnMouseClicked(ev -> {
            navigationViewTransition(lastView);
        });

    }

    @FXML void navigateDashboard() {
        navigationViewTransition(anpViewDashboard);
        if (viewStudentsController.studentsInformation.isVisible()) AppAnimations.popdown(viewStudentsController.studentsInformation, 0);
        AppAnimations.navigationBarTransition(pneNavigationIndicator, -195);
    }

    @FXML void navigateStudents() {
        navigationViewTransition(anpViewStudents);
        viewStudentsController.getData();
        AppAnimations.navigationBarTransition(pneNavigationIndicator, -65);
    }

    @FXML void navigateCurriculum() {
        navigationViewTransition(anpViewCurriculum);
        AppAnimations.navigationBarTransition(pneNavigationIndicator, 65);
    }

    @FXML void navigateReports() {
        navigationViewTransition(anpViewReports);
        viewReportsController.getData();
        AppAnimations.navigationBarTransition(pneNavigationIndicator, 195);
    }

    @FXML void navigateSettings() {
        navigationViewTransition(anpViewSettings);
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
        lastView = currentView;
        currentView = view;
    }
}