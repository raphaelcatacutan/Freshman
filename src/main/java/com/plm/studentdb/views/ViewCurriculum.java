package com.plm.studentdb.views;

import com.plm.studentdb.database.DBFind;
import com.plm.studentdb.database.DBView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;
import java.util.Random;

public class ViewCurriculum {
    @FXML FlowPane flwViewCurriculumList;

    @FXML Parent curriculumForms;
    @FXML Parent curriculumStudents;

    @FXML TextField txfCourseSearch;

    @FXML CurriculumForms curriculumFormsController;
    @FXML CurriculumStudents curriculumStudentsController;

    public static final String[] colors = {
            "#6EABD8", "#7CC47D", "#C96161", "#B9B9E7", "#CCB8B1", "#ACD5D5", "#C3988C"
    };

    @FXML
    public void initialize() {
        List<Course> courses = DBView.viewCourseRecord();

        for (Course course: courses) {
            VBox vbox = createCourseVBox(course);
            flwViewCurriculumList.getChildren().add(vbox);
            vbox.setOnMouseClicked(ev -> {
                curriculumFormsController.showForms(course, vbox, false, 0);
            });
        }

        curriculumFormsController.flwCurriculumFormList = flwViewCurriculumList;
        curriculumFormsController.curriculumStudentsController = curriculumStudentsController;
        curriculumStudentsController.curriculumFormsController = curriculumFormsController;
    }

    @FXML
    public void showForms() {
        curriculumFormsController.showForms(null, null, true, 0);
    }

    @FXML
    public void search(KeyEvent event) {
        if (event.getCode() != KeyCode.ENTER) return;
        String input = txfCourseSearch.getText();
        flwViewCurriculumList.getChildren().clear();

        List<Course> courses = input.isEmpty() ? DBView.viewCourseRecord() : DBFind.findLikeCourse(input);

        for (Course course: courses) {
            VBox vbox = createCourseVBox(course);
            flwViewCurriculumList.getChildren().add(vbox);
            vbox.setOnMouseClicked(ev -> {
                curriculumFormsController.showForms(course, vbox, false, 0);
            });
        }
    }

    public static VBox createCourseVBox(Course course) {
        int index = new Random().nextInt(7);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setLayoutX(401.0);
        vbox.setLayoutY(40.0);
        vbox.setPrefHeight(251.0);
        vbox.setPrefWidth(288.0);
        vbox.setSpacing(5.0);
        vbox.setStyle("-fx-background-color: #f4f4f4; -fx-background-radius: 20;");
        vbox.setPadding(new Insets(30.0, 30.0, 30.0, 30.0));
        vbox.setCursor(Cursor.HAND);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(new Color(0, 0, 0, 0.2310030609369278));
        vbox.setEffect(dropShadow);

        Label label1 = new Label(abbreviate(course.getCourseName()));
        label1.setAlignment(Pos.CENTER);
        label1.setPrefHeight(80.0);
        label1.setPrefWidth(80.0);
        label1.setStyle("-fx-background-color: " + colors[index] + "; -fx-background-radius: 60;");
        label1.getStyleClass().add("label-text");
        label1.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label1.setFont(new Font("Century Gothic Bold", 21.0));
        label1.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));

        Label label2 = new Label(course.getCourseCode());
        label2.getStyleClass().add("label-text");
        label2.setFont(new Font("Century Gothic Bold", 26.0));

        Label label3 = new Label(course.getCourseName());
        label3.getStyleClass().add("label-text");
        label3.setFont(new Font("Century Gothic", 15.0));
        label3.setEllipsisString("");

        Label label4 = new Label(course.getSections() + " sections | " + course.getUnits() + " units");
        label4.getStyleClass().add("label-text");
        label4.setFont(new Font("Century Gothic", 13.0));

        vbox.getChildren().addAll(label1, label2, label3, label4);

        return vbox;
    }

    public static String abbreviate(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        String[] words = name.split("\\s+");
        StringBuilder abbreviation = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty() && Character.isLetter(word.charAt(0))) {
                abbreviation.append(word.charAt(0));
            }
            if (abbreviation.length() >= 3) {
                break;
            }
        }

        if (abbreviation.length() > 3) {
            return abbreviation.substring(0, 3).toUpperCase();
        }

        return abbreviation.toString().toUpperCase();
    }

}
