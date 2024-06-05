package com.plm.studentdb.views;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AppAnimations {
    public static void popup(Node target, double delay) {
        target.setOpacity(0);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.25), target);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setDelay(Duration.seconds(delay));

        ScaleTransition scaleUpTransition = new ScaleTransition(Duration.seconds(0.25), target);
        scaleUpTransition.setFromX(0);
        scaleUpTransition.setFromY(0);
        scaleUpTransition.setToX(1.1);
        scaleUpTransition.setToY(1.1);
        scaleUpTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleUpTransition.setDelay(Duration.seconds(delay));

        ScaleTransition scaleDownTransition = new ScaleTransition(Duration.seconds(0.15), target);
        scaleDownTransition.setFromX(1.1);
        scaleDownTransition.setFromY(1.1);
        scaleDownTransition.setToX(1);
        scaleDownTransition.setToY(1);
        scaleDownTransition.setInterpolator(Interpolator.EASE_OUT);

        SequentialTransition scaleTransition = new SequentialTransition(scaleUpTransition, scaleDownTransition);

        fadeTransition.play();
        scaleTransition.play();

        target.setVisible(true);
    }

    public static void popdown(Node target, double delay) {
        target.setOpacity(0);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.25), target);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setDelay(Duration.seconds(delay));

        ScaleTransition scaleUpTransition = new ScaleTransition(Duration.seconds(0.25), target);
        scaleUpTransition.setFromX(1);
        scaleUpTransition.setFromY(1);
        scaleUpTransition.setToX(0);
        scaleUpTransition.setToY(0);
        scaleUpTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleUpTransition.setDelay(Duration.seconds(delay));

        fadeTransition.play();
        scaleUpTransition.play();

        scaleUpTransition.setOnFinished(ev -> {
            target.setVisible(false);
        });
    }

    public static void login() {

    }

    public static void navigateIn(AnchorPane view) {
        // Create FadeTransition for fading in
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), view);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setDelay(Duration.seconds(0.5));

        // Create TranslateTransition for floating in from below
        TranslateTransition translateIn = new TranslateTransition(Duration.seconds(0.5), view);
        translateIn.setFromY(100); // Start from below
        translateIn.setToY(0); // Move to original position
        translateIn.setDelay(Duration.seconds(0.5));

        // Play both transitions together
        fadeIn.setOnFinished(ev -> {
            view.toFront();
            view.setOpacity(1);
        });
        translateIn.play();
        fadeIn.play();
    }

    public static void navigateOut(AnchorPane view) {
        // Create FadeTransition for fading out
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), view);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        // Create TranslateTransition for floating out upwards
        TranslateTransition translateOut = new TranslateTransition(Duration.seconds(0.5), view);
        translateOut.setFromY(0); // Start from original position
        translateOut.setToY(-100); // Move upwards

        // Play both transitions together
        fadeOut.setOnFinished(ev -> {
            view.toBack();
            view.setOpacity(0);
        });
        translateOut.play();
        fadeOut.play();
    }
}
