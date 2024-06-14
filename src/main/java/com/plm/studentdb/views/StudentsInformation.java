package com.plm.studentdb.views;

import com.plm.studentdb.database.DBAdd;
import com.plm.studentdb.database.DBEdit;
import com.plm.studentdb.database.DBFind;
import com.plm.studentdb.database.DBRemove;
import com.plm.studentdb.models.Lesson;
import com.plm.studentdb.models.Student;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

    @FXML Button studentsInformationDelete;
    @FXML Button studentsInformationSave;

    @FXML TextField txfStudentInformationGradeYearSem; // Intentionally Separated
    @FXML Label lblStudentInformationGWA;
    @FXML Label lblStudentInformationStatus;
    @FXML AnchorPane studentsInformationClose;

    public boolean isAdding = true;
    public Student focusedStudent;

    void showForms(Student student, double delay, boolean isAdding, boolean isViewing) {
        preFillForm(student, isViewing);
        scrStudentsInformation.setVvalue(0);
        AppAnimations.popup(anpStudentsInformation, delay);
        this.isAdding = isAdding;
        studentsInformationDelete.setVisible(!isAdding && !isViewing);
        studentsInformationSave.setVisible(!isViewing);
        studentsInformationClose.setVisible(!isViewing);
        if (isAdding) focusedStudent = null;
        else focusedStudent = student;
    }

    @FXML
    public void initialize() {
        AppValidator.restrictToInteger(txfStudentInformationGradeYearSem, 5);
    }

    @FXML
    void closeForms() {
        preFillForm(null, true);
        AppAnimations.popdown(anpStudentsInformation, 0);
    }

    @FXML void confirmForm() {
        String studentName = txfStudentInformationName.getText();
        int studentID = Integer.parseInt(txfStudentInformationID.getText());
        String program = txfStudentInformationProgram.getText();
        String email = txfStudentInformationEmail.getText();
        String yearBlock = txfStudentInformationYearBlock.getText();
        int year = Integer.parseInt(yearBlock.split("-")[0]);
        int block = Integer.parseInt(yearBlock.split("-")[1]);
        String password = txfStudentInformationID.getText();

        if (isAdding) {
            Student student = DBAdd.addStudent(studentID, studentName, program, year, block, email, password);

            ViewStudents.studentsListTable.add(student);
            Dialogs.mainMessageDialog.show("Adding Successful", "The entered data has been successfully added to the database.");
        } else {
            Student student = DBEdit.editStudent(focusedStudent.getStudentID(), studentName, program, year, block, email, focusedStudent.getPassword());
            ViewStudents.studentsListTable.remove(focusedStudent);
            ViewStudents.studentsListTable.add(student);
            Dialogs.mainMessageDialog.show("Editing Successful", "The student data has been successfully updated to the database.");

            for (Node node: flwStudentsInformationGrades.getChildren()) {
                Lesson lesson = (Lesson) node.getUserData();
                double grade = Double.parseDouble(((TextField) node.lookup("#grades")).getText());
                DBEdit.editLesson(lesson.getLessonID(), focusedStudent.getStudentID(), lesson.getCourseID(), lesson.getSection(), grade);
            }

        }

        closeForms();
    }

    @FXML void deleteStudent() {
        Runnable delete = () -> {
            DBRemove.removeStudent(focusedStudent.getStudentID());
            ViewStudents.studentsListTable.remove(focusedStudent);
            closeForms();
        };
        Dialogs.mainConfirmDialog.show("Delete a Student", "Are you sure you want to delete this student from the database?", delete);
    }

    public void preFillForm(Student student, boolean isViewing) {
        if (student == null) {
            txfStudentInformationName.setText(null);
            txfStudentInformationID.setText(null);
            txfStudentInformationProgram.setText(null);
            txfStudentInformationEmail.setText(null);
            txfStudentInformationYearBlock.setText(null);
        } else {
            txfStudentInformationName.setText(student.getStudentName());
            txfStudentInformationID.setText(String.valueOf(student.getStudentID()));
            txfStudentInformationProgram.setText(student.getProgramID());
            txfStudentInformationEmail.setText(student.getEmail());
            txfStudentInformationYearBlock.setText(student.getYear() + "-" + student.getBlock());
        }

        txfStudentInformationName.setEditable(!isViewing);
        txfStudentInformationID.setEditable(!isViewing);
        txfStudentInformationProgram.setEditable(!isViewing);
        txfStudentInformationEmail.setEditable(!isViewing);
        txfStudentInformationYearBlock.setEditable(!isViewing);

        txfStudentInformationGradeYearSem.setText("");
        flwStudentsInformationGrades.getChildren().clear();
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

        List<Lesson> lessons = DBFind.findLessons(null, focusedStudent.getStudentID(), year + "" + semester + "%", null, null, null);
        for (Lesson lesson: lessons) {
            HBox grade = generateClassHBox(lesson);
            flwStudentsInformationGrades.getChildren().add(grade);
            if (lesson.getCourseID().contains("NSTP")) continue;
            int units = DBFind.findCourses(lesson.getCourseID(), null, null, null, null, null, null, null).getFirst().getUnits();
            totalGrade += lesson.getGrade() * units;
            totalUnits += units;
        }
        gwa = totalGrade / totalUnits;
        lblStudentInformationGWA.setText(String.valueOf(Double.isNaN(gwa) ? "0.00": gwa));
        lblStudentInformationStatus.setText(gwa <= 3.00 || Double.isNaN(gwa) ? "Regular" : "Irregular");
    }

    public static HBox generateClassHBox(Lesson lesson) {
        HBox hBox = new HBox();
        hBox.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);
        hBox.setLayoutX(361.0);
        hBox.setLayoutY(40.0);
        hBox.setPrefHeight(140.0);
        hBox.setPrefWidth(283.0);
        hBox.setStyle("-fx-background-color: #f4f4f4; -fx-background-radius: 20;");
        hBox.setUserData(lesson);

        VBox vBox = new VBox();
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setPrefHeight(184.0);
        vBox.setPrefWidth(184.0);

        Label label1 = new Label(lesson.getCourseID());
        label1.setGraphicTextGap(5.0);
        label1.setPrefWidth(270.0);
        label1.getStyleClass().add("label-text");
        label1.setId("courseCode");
        label1.setFont(new Font("Century Gothic Bold", 25.0));

        Label label2 = new Label("Change this");
        label2.setGraphicTextGap(5.0);
        label2.setLayoutX(220.0);
        label2.setLayoutY(84.0);
        label2.getStyleClass().add("label-text");
        label2.setId("yearSection");
        label2.setFont(new Font("Century Gothic", 14.0));

        vBox.getChildren().addAll(label1, label2);
        vBox.setCursor(javafx.scene.Cursor.HAND);

        TextField textField = new TextField(String.valueOf(lesson.getGrade()));
        textField.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        textField.setPrefHeight(62.0);
        textField.setPrefWidth(119.0);
        textField.setStyle("-fx-background-color: none;");
        textField.getStyleClass().add("label-text");
        textField.setId("grades");
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
