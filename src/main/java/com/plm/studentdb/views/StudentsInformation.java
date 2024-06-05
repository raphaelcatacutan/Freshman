package com.plm.studentdb.views;

import com.plm.studentdb.database.DBAdd;
import com.plm.studentdb.database.DBEdit;
import com.plm.studentdb.database.DBFind;
import com.plm.studentdb.database.DBRemove;
import com.plm.studentdb.models.Class;
import com.plm.studentdb.models.Student;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

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

    @FXML TextField txfStudentInformationGradeYearSem;
    @FXML Label lblStudentInformationGWA;
    @FXML Label lblStudentInformationStatus;

    public boolean isAdding = true;
    public Student focusedStudent;

    void showForms(Student student, double delay, boolean isAdding) {
        preFillForm(student);
        scrStudentsInformation.setVvalue(0);
        AppAnimations.popup(anpStudentsInformation, delay);
        this.isAdding = isAdding;
        if (isAdding) focusedStudent = null;
        else focusedStudent = student;
    }

    @FXML
    void closeForms() {
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

            //flwStudentsInformationGrades.getChildren()

        }

        closeForms();
    }

    @FXML void deleteStudent() {
        DBRemove.removeStudentRecord(focusedStudent.getId());
        ViewStudents.studentsListTable.remove(focusedStudent);
        closeForms();
    }

    public void preFillForm(Student student) {
        if (student == null) {
            txfStudentInformationName.setText(null);
            txfStudentInformationID.setText(null);
            txfStudentInformationProgram.setText(null);
            txfStudentInformationEmail.setText(null);
            txfStudentInformationYearBlock.setText(null);
            txfStudentInformationGradeYearSem.setText(null);
            flwStudentsInformationGrades.getChildren().clear();
        } else {
            txfStudentInformationName.setText(student.getName());
            txfStudentInformationID.setText(String.valueOf(student.getStudentId()));
            txfStudentInformationProgram.setText(student.getProgram());
            txfStudentInformationEmail.setText(student.getEmail());
            txfStudentInformationYearBlock.setText(student.getYear() + "-" + student.getBlock());
            txfStudentInformationGradeYearSem.setText(null);
            flwStudentsInformationGrades.getChildren().clear();
        }
    }

    @FXML
    public void showGrades(KeyEvent event) {
        if (event.getCode() != KeyCode.ENTER) return;
        if (focusedStudent == null && isAdding) {
            Dialogs.mainMessageDialog.show("Invalid Action", "You can't check the grades of a student who hasn't been added to the database yet.");
            return;
        }
        String yearSem = txfStudentInformationGradeYearSem.getText();
        int year = Integer.parseInt(yearSem.substring(0, 4)); // Extract the first 4 characters and convert to integer
        int semester = Character.getNumericValue(yearSem.charAt(4)); // Extract the 5th character and convert to integer
        double totalGrade = 0;
        double totalUnits = 0;
        double gwa;

        List<Class> classes = DBFind.findClass(focusedStudent.getStudentId(), year, semester);
        for (Class aClass: classes) {
            HBox grade = generateClassHBox(aClass);
            flwStudentsInformationGrades.getChildren().add(grade);
            if (aClass.getCourseCode().contains("NSTP")) continue;
            int units = DBFind.findCourse(aClass.getYear() + "-" + aClass.getCourseCode()).getFirst().getUnits();
            totalGrade += aClass.getGrade() * units;
            totalUnits += units;
        }
        gwa = totalGrade / totalUnits;
        lblStudentInformationGWA.setText(String.valueOf(Double.isNaN(gwa) ? "0.00": gwa));
        lblStudentInformationStatus.setText(gwa <= 3.00 || Double.isNaN(gwa) ? "Regular" : "Irregular");
    }

    public static HBox generateClassHBox(Class enrolled) {
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

        Label label1 = new Label(enrolled.getCourseCode());
        label1.setGraphicTextGap(5.0);
        label1.setPrefWidth(270.0);
        label1.getStyleClass().add("label-text");
        label1.setFont(new Font("Century Gothic Bold", 25.0));
        label1.setWrapText(true);

        Label label2 = new Label(enrolled.getYear() + " - Section " + enrolled.getSection());
        label2.setGraphicTextGap(5.0);
        label2.setLayoutX(220.0);
        label2.setLayoutY(84.0);
        label2.getStyleClass().add("label-text");
        label2.setWrapText(true);
        label2.setFont(new Font("Century Gothic", 14.0));

        vBox.getChildren().addAll(label1, label2);
        vBox.setCursor(javafx.scene.Cursor.HAND);

        TextField textField = new TextField(String.valueOf(enrolled.getGrade()));
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
