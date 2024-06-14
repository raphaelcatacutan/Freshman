package com.plm.studentdb.views;

import com.plm.studentdb.utils.ProgramConstants;
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
    @FXML public Parent viewDatabase;

    @FXML public AnchorPane anpViewDashboard;
    @FXML public AnchorPane anpViewStudents;
    @FXML public AnchorPane anpViewCurriculum;
    @FXML public AnchorPane anpViewReports;
    @FXML public AnchorPane anpViewDatabase;

    @FXML public ViewDashboard viewDashboardController;
    @FXML public ViewStudents viewStudentsController;
    @FXML public ViewCurriculum viewCurriculumController;
    @FXML public ViewReports viewReportsController;
    @FXML public ViewDatabase viewDatabaseController;

    @FXML public HBox hbxNavigationBar;

    public Parent mainLogin;

    AnchorPane[] views = new AnchorPane[5];
    AnchorPane currentView = anpViewDashboard;

    @FXML void initialize() {
        views = new AnchorPane[]{anpViewDashboard, anpViewStudents, anpViewCurriculum, anpViewReports, anpViewDatabase};

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

    }

    @FXML void navigateDashboard() {
        navigationViewTransition(anpViewDashboard);
        if (viewStudentsController.studentsInformation.isVisible()) AppAnimations.popdown(viewStudentsController.studentsInformation, 0);
        AppAnimations.navigationBarTransition(pneNavigationIndicator, -260);
    }

    @FXML void navigateStudents() {
        navigationViewTransition(anpViewStudents);
        viewStudentsController.getData();
        AppAnimations.navigationBarTransition(pneNavigationIndicator, -130);
    }

    @FXML void navigateCurriculum() {
        navigationViewTransition(anpViewCurriculum);
        AppAnimations.navigationBarTransition(pneNavigationIndicator, 0);
    }

    @FXML void navigateReports() {
        navigationViewTransition(anpViewReports);
        viewReportsController.getData();
        AppAnimations.navigationBarTransition(pneNavigationIndicator, 130);
    }

    @FXML void navigateDatabase() {
        if (ProgramConstants.accountAccess.contains("ALL")) {
            AppAnimations.navigationBarTransition(pneNavigationIndicator, 260);
            navigationViewTransition(anpViewDatabase);
        } else {
            Dialogs.mainMessageDialog.show("Invalid Action", "Your Account requires an ADMIN Access to Open Database. Request access to the administrator or use an admin account");
        }
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