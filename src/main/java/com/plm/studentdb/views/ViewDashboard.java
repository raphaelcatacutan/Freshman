package com.plm.studentdb.views;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ViewDashboard {
    @FXML public StackPane stkDashboardQuickAdd;
    @FXML public StackPane stkDashboardQuickView;
    @FXML public StackPane stkDashboardQuickCourse;

    @FXML public ImageView imgDashboardQuickAdd;
    @FXML public ImageView imgDashboardQuickView;
    @FXML public ImageView imgDashboardQuickCourse;

    @FXML public VBox vbxDashboardQuickAdd;
    @FXML public VBox vbxDashboardQuickView;
    @FXML public VBox vbxDashboardQuickCourse;



    @FXML
    public void initialize() {
    }

    public void stkDashboardQuickAddHover() {
        // Create a ScaleTransition for VBox
        ScaleTransition scaleTransitionVBox = new ScaleTransition(Duration.seconds(0.2), vbxDashboardQuickAdd);
        scaleTransitionVBox.setFromX(1.0);
        scaleTransitionVBox.setFromY(1.0);
        scaleTransitionVBox.setToX(1.1);
        scaleTransitionVBox.setToY(1.1);

        // Create a ScaleTransition for ImageView
        ScaleTransition scaleTransitionImage = new ScaleTransition(Duration.seconds(0.2), imgDashboardQuickAdd);
        scaleTransitionImage.setFromX(1.0);
        scaleTransitionImage.setFromY(1.0);
        scaleTransitionImage.setToX(1.3);
        scaleTransitionImage.setToY(1.3);

        // Create a BackgroundFill for VBox background
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#ff8080"), new CornerRadii(30), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        vbxDashboardQuickAdd.setBackground(background);

        // Create a RotateTransition for ImageView
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.2), imgDashboardQuickAdd);
        rotateTransition.setByAngle(10); // Rotate by 10 degrees

        // Combine transitions
        ParallelTransition parallelTransition = new ParallelTransition(rotateTransition, scaleTransitionVBox, scaleTransitionImage);

        // Handle mouse enter event
        parallelTransition.playFromStart(); // Play the animation
    }


    public void stkDashboardQuickAddReverseHover() {
        // Create a ScaleTransition for VBox
        ScaleTransition scaleTransitionVBox = new ScaleTransition(Duration.seconds(0.2), vbxDashboardQuickAdd);
        scaleTransitionVBox.setFromX(1.1);
        scaleTransitionVBox.setFromY(1.1);
        scaleTransitionVBox.setToX(1.0);
        scaleTransitionVBox.setToY(1.0);

        // Create a ScaleTransition for ImageView
        ScaleTransition scaleTransitionImage = new ScaleTransition(Duration.seconds(0.2), imgDashboardQuickAdd);
        scaleTransitionImage.setFromX(1.3);
        scaleTransitionImage.setFromY(1.3);
        scaleTransitionImage.setToX(1.0);
        scaleTransitionImage.setToY(1.0);

        // Create a BackgroundFill for VBox background
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#ffc0c0"), new CornerRadii(30), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        vbxDashboardQuickAdd.setBackground(background);

        // Create a RotateTransition for ImageView
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.2), imgDashboardQuickAdd);
        rotateTransition.setByAngle(-10); // Rotate by 10 degrees

        // Combine transitions
        ParallelTransition parallelTransition = new ParallelTransition(rotateTransition, scaleTransitionVBox, scaleTransitionImage);

        // Handle mouse enter event
        parallelTransition.playFromStart(); // Play the animation
    }

    public void stkDashboardQuickViewHover() {
        // Create a ScaleTransition for VBox
        ScaleTransition scaleTransitionVBox = new ScaleTransition(Duration.seconds(0.2), vbxDashboardQuickView);
        scaleTransitionVBox.setFromX(1.0);
        scaleTransitionVBox.setFromY(1.0);
        scaleTransitionVBox.setToX(1.1);
        scaleTransitionVBox.setToY(1.1);

        // Create a ScaleTransition for ImageView
        ScaleTransition scaleTransitionImage = new ScaleTransition(Duration.seconds(0.2), imgDashboardQuickView);
        scaleTransitionImage.setFromX(1.0);
        scaleTransitionImage.setFromY(1.0);
        scaleTransitionImage.setToX(1.3);
        scaleTransitionImage.setToY(1.3);

        // Create a BackgroundFill for VBox background
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#fafaa2"), new CornerRadii(30), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        vbxDashboardQuickView.setBackground(background);

        // Create a RotateTransition for ImageView
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.2), imgDashboardQuickView);
        rotateTransition.setByAngle(-10); // Rotate by 10 degrees

        // Combine transitions
        ParallelTransition parallelTransition = new ParallelTransition(rotateTransition, scaleTransitionVBox, scaleTransitionImage);

        // Handle mouse enter event
        parallelTransition.playFromStart(); // Play the animation
    }

    public void stkDashboardQuickPrintHover() {
        // Create a ScaleTransition for VBox
        ScaleTransition scaleTransitionVBox = new ScaleTransition(Duration.seconds(0.2), vbxDashboardQuickView);
        scaleTransitionVBox.setFromX(1.1);
        scaleTransitionVBox.setFromY(1.1);
        scaleTransitionVBox.setToX(1.0);
        scaleTransitionVBox.setToY(1.0);

        // Create a ScaleTransition for ImageView
        ScaleTransition scaleTransitionImage = new ScaleTransition(Duration.seconds(0.2), imgDashboardQuickView);
        scaleTransitionImage.setFromX(1.3);
        scaleTransitionImage.setFromY(1.3);
        scaleTransitionImage.setToX(1.0);
        scaleTransitionImage.setToY(1.0);

        // Create a BackgroundFill for VBox background
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#f3f3d5"), new CornerRadii(30), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        vbxDashboardQuickView.setBackground(background);

        // Create a RotateTransition for ImageView
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.2), imgDashboardQuickView);
        rotateTransition.setByAngle(10); // Rotate by 10 degrees

        // Combine transitions
        ParallelTransition parallelTransition = new ParallelTransition(rotateTransition, scaleTransitionVBox, scaleTransitionImage);

        // Handle mouse enter event
        parallelTransition.playFromStart(); // Play the animation
    }

    public void stkDashboardQuickCourseHover() {
        // Create a ScaleTransition for VBox
        ScaleTransition scaleTransitionVBox = new ScaleTransition(Duration.seconds(0.2), vbxDashboardQuickCourse);
        scaleTransitionVBox.setFromX(1.0);
        scaleTransitionVBox.setFromY(1.0);
        scaleTransitionVBox.setToX(1.1);
        scaleTransitionVBox.setToY(1.1);

        // Create a ScaleTransition for ImageView
        ScaleTransition scaleTransitionImage = new ScaleTransition(Duration.seconds(0.2), imgDashboardQuickCourse);
        scaleTransitionImage.setFromX(1.0);
        scaleTransitionImage.setFromY(1.0);
        scaleTransitionImage.setToX(1.3);
        scaleTransitionImage.setToY(1.3);

        // Create a BackgroundFill for VBox background
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#74a2d0"), new CornerRadii(30), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        vbxDashboardQuickCourse.setBackground(background);

        // Create a RotateTransition for ImageView
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.2), imgDashboardQuickCourse);
        rotateTransition.setByAngle(5); // Rotate by 10 degrees

        // Combine transitions
        ParallelTransition parallelTransition = new ParallelTransition(rotateTransition, scaleTransitionVBox, scaleTransitionImage);

        // Handle mouse enter event
        parallelTransition.playFromStart(); // Play the animation
    }


    public void stkDashboardQuickCourseReverseHover() {
        // Create a ScaleTransition for VBox
        ScaleTransition scaleTransitionVBox = new ScaleTransition(Duration.seconds(0.2), vbxDashboardQuickCourse);
        scaleTransitionVBox.setFromX(1.1);
        scaleTransitionVBox.setFromY(1.1);
        scaleTransitionVBox.setToX(1.0);
        scaleTransitionVBox.setToY(1.0);

        // Create a ScaleTransition for ImageView
        ScaleTransition scaleTransitionImage = new ScaleTransition(Duration.seconds(0.2), imgDashboardQuickCourse);
        scaleTransitionImage.setFromX(1.3);
        scaleTransitionImage.setFromY(1.3);
        scaleTransitionImage.setToX(1.0);
        scaleTransitionImage.setToY(1.0);

        // Create a BackgroundFill for VBox background
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#a5cedb"), new CornerRadii(30), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        vbxDashboardQuickCourse.setBackground(background);

        // Create a RotateTransition for ImageView
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.2), imgDashboardQuickCourse);
        rotateTransition.setByAngle(-5); // Rotate by 10 degrees

        // Combine transitions
        ParallelTransition parallelTransition = new ParallelTransition(rotateTransition, scaleTransitionVBox, scaleTransitionImage);

        // Handle mouse enter event
        parallelTransition.playFromStart(); // Play the animation
    }
}
