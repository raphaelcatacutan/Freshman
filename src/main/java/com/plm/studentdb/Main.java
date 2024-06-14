package com.plm.studentdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/app-main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Student Record Management");
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/icons/plm-logo.png"))));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}