package com.plm.studentdb.views;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class AppAnimations {
    public static void popup(Node target, double delay) {
        if (target.isVisible()) return;
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

        target.toFront();

        fadeTransition.play();
        scaleTransition.play();

        target.setVisible(true);
    }

    public static void popdown(Node target, double delay) {
        if (!target.isVisible()) return;
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
            target.toBack();
            target.setVisible(false);
        });
    }

    public static void login() {

    }

    public static void navigateIn(AnchorPane view) {
        if (view.getOpacity() > 0) return;
        // Create FadeTransition for fading in
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), view);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setDelay(Duration.seconds(0.5));

        // Play both transitions together
        fadeIn.setOnFinished(ev -> {
            view.toFront();
            view.setOpacity(1);
        });
        fadeIn.play();
    }

    public static void navigateOut(AnchorPane view) {
        if (view.getOpacity() <= 0) return;
        // Create FadeTransition for fading out
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), view);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        // Play both transitions together
        fadeOut.setOnFinished(ev -> {
            view.toBack();
            view.setOpacity(0);
        });
        fadeOut.play();
    }

    public static void navigationBarTransition(Pane navigationIndictor, double y) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), navigationIndictor);
        transition.setToX(y);
        transition.play();
    }
}
