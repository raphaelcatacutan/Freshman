package com.plm.studentdb.views;

import com.plm.studentdb.database.DBAdd;
import com.plm.studentdb.database.DBEdit;
import com.plm.studentdb.database.DBRemove;
import com.plm.studentdb.models.Student;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StudentsInformation {
    @FXML AnchorPane anpStudentsInformation;
    @FXML ScrollPane scrStudentsInformation;
    @FXML HBox hbxStudentInformationGrades;
    @FXML FlowPane flwStudentsInformationGrades;

    @FXML TextField txfStudentInformationName;
    @FXML TextField txfStudentInformationID;
    @FXML TextField txfStudentInformationProgram;
    @FXML TextField txfStudentInformationEmail;
    @FXML TextField txfStudentInformationYearBlock;

    public MainMessage mainMessageController;
    public boolean isAdding = true;
    public Student focusedStudent;

    void show(Student student, double delay, boolean isAdding) {
        preFillForm(student);
        scrStudentsInformation.setVvalue(0);
        AppAnimations.popup(anpStudentsInformation, delay);
        this.isAdding = isAdding;
        if (isAdding) focusedStudent = null;
        else focusedStudent = student;
    }

    @FXML
    void closeAddView() {
        preFillForm(null);
        AppAnimations.popdown(anpStudentsInformation, 0);
    }

    @FXML void confirmForm() {
        String full_name = txfStudentInformationName.getText();
        int student_id = Integer.parseInt(txfStudentInformationID.getText());
        String program = txfStudentInformationProgram.getText();
        String email = txfStudentInformationEmail.getText();
        String yearBlock = txfStudentInformationYearBlock.getText();
        int year = Integer.parseInt(yearBlock.split("-")[0]);
        int block = Integer.parseInt(yearBlock.split("-")[1]);

        if (isAdding) {
            Student student = DBAdd.addStudentRecord(student_id, full_name, program, year, block, email);

            ViewStudents.studentsListTable.add(student);
            Dialogs.mainMessageDialog.show("Adding Successful", "The entered data has been successfully added to the database.");
        } else {
            Student student = DBEdit.editStudentRecord(focusedStudent.getId(), student_id, full_name, program, year, block, email);
            ViewStudents.studentsListTable.remove(focusedStudent);
            ViewStudents.studentsListTable.add(student);
            Dialogs.mainMessageDialog.show("Editing Successful", "The student data has been successfully updated to the database.");
        }

        closeAddView();
    }

    @FXML void deleteStudent() {
        DBRemove.removeStudentRecord(focusedStudent.getId());
        ViewStudents.studentsListTable.remove(focusedStudent);
        closeAddView();
    }

    public void preFillForm(Student student) {
        if (student == null) {
            txfStudentInformationName.setText(null);
            txfStudentInformationID.setText(null);
            txfStudentInformationProgram.setText(null);
            txfStudentInformationEmail.setText(null);
            txfStudentInformationYearBlock.setText(null);
        } else {
            txfStudentInformationName.setText(student.getName());
            txfStudentInformationID.setText(String.valueOf(student.getStudentId()));
            txfStudentInformationProgram.setText(student.getProgram());
            txfStudentInformationEmail.setText(student.getEmail());
            txfStudentInformationYearBlock.setText(student.getYear() + "-" + student.getBlock());
        }
        HBox grade1 = generateHBox();
        //flwStudentsInformationGrades.getChildren().add(grade1);
    }

    public void showGrades() {

    }

    public static HBox generateHBox() {
        HBox hBox = new HBox();
        hBox.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);
        hBox.setLayoutX(361.0);
        hBox.setLayoutY(40.0);
        hBox.setPrefHeight(140.0);
        hBox.setPrefWidth(283.0);
        hBox.setStyle("-fx-background-color: #f4f4f4; -fx-background-radius: 20;");

        VBox vBox = new VBox();
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setPrefHeight(184.0);
        vBox.setPrefWidth(184.0);

        Label label1 = new Label("RPH 004");
        label1.setGraphicTextGap(5.0);
        label1.setPrefWidth(259.0);
        label1.getStyleClass().add("label-text");
        label1.setFont(new Font("Century Gothic Bold", 30.0));

        Label label2 = new Label("Readings in Philippine History");
        label2.setGraphicTextGap(5.0);
        label2.setLayoutX(220.0);
        label2.setLayoutY(84.0);
        label2.getStyleClass().add("label-text");
        label2.setText("Readings in Philippine History");
        label2.setWrapText(true);
        label2.setFont(new Font("Century Gothic", 14.0));

        vBox.getChildren().addAll(label1, label2);
        vBox.setCursor(javafx.scene.Cursor.HAND);

        TextField textField = new TextField("1.75");
        textField.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        textField.setPrefHeight(62.0);
        textField.setPrefWidth(119.0);
        textField.setStyle("-fx-background-color: none;");
        textField.getStyleClass().add("label-text");
        textField.setFont(new Font("Century Gothic Bold", 34.0));
        textField.setPadding(new Insets(5.0));

        hBox.getChildren().addAll(vBox, textField);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setHeight(70.13);
        dropShadow.setRadius(30.47);
        dropShadow.setWidth(53.75);
        dropShadow.setColor(Color.color(0, 0, 0, 0.15805472433567047));

        hBox.setEffect(dropShadow);
        hBox.setPadding(new Insets(30.0));

        return hBox;
    }
}
