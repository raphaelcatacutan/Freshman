package com.plm.studentdb.views;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
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
}
