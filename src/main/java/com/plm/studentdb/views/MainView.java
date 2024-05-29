package com.plm.studentdb.views;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.Optional;

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
        viewDashboardController.vbxDashboardQuickView.setOnMouseClicked(ev -> {
            navigateStudents();
        });
    }

    @FXML void navigateDashboard() {
        navigationBarTransition(0);
        navigationViewTransition(viewDashboard);
        viewStudentsController.studentsAddView.setVisible(false);
    }

    @FXML void navigateStudents() {
        navigationBarTransition(53);
        navigationViewTransition(viewStudents);
    }

    @FXML void navigateSettings() {
        navigationBarTransition(106);
        navigationViewTransition(viewSettings);
        viewStudentsController.studentsAddView.setVisible(false);
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

@FXML
void logout() {
    logoutConfirmation();
}

void logoutConfirmation() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("System Logout");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to logout?");

    Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            doLogout();
        }
    }

void doLogout() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/plm/studentdb/views/app-main.fxml"));
        Parent loginView = loader.load();
        Stage stage = (Stage) stkViews.getScene().getWindow();
        stage.setScene(new Scene(loginView));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}