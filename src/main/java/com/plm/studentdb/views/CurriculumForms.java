package com.plm.studentdb.views;

import com.plm.studentdb.database.DBAdd;
import com.plm.studentdb.database.DBEdit;
import com.plm.studentdb.database.DBRemove;
import com.plm.studentdb.models.Course;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class CurriculumForms {
    @FXML AnchorPane anpCurriculumForms;

    @FXML TextField txfCurriculumFormsYear;
    @FXML TextField txfCurriculumFormsSemester;
    @FXML TextField txfCurriculumFormsCode;
    @FXML TextField txfCurriculumFormsSections;
    @FXML TextField txfCurriculumFormsName;
    @FXML TextField txfCurriculumFormsUnits;
    @FXML TextField txfCurriculumFormsLimit;

    @FXML Button curriculumFormsDelete;
    @FXML Button curriculumFormsStudents;

    public boolean isAdding;
    public Course focusedCourse;
    public VBox focusedVBox;
    public FlowPane flwCurriculumFormList;
    public CurriculumStudents curriculumStudentsController;

    @FXML
    public void closeForms() {
        preFillForm(null);
        AppAnimations.popdown(anpCurriculumForms, 0);
        focusedCourse = null;
        focusedVBox = null;
    }

    @FXML
    void confirmForm() {
        int year = Integer.parseInt(txfCurriculumFormsYear.getText());
        int semester = Integer.parseInt(txfCurriculumFormsSemester.getText());
        String courseCode = txfCurriculumFormsCode.getText();
        int sections = Integer.parseInt(txfCurriculumFormsSections.getText());
        String name = txfCurriculumFormsName.getText();
        int units = Integer.parseInt(txfCurriculumFormsUnits.getText());
        int capacity = Integer.parseInt(txfCurriculumFormsLimit.getText());

        if (isAdding) {
            Course course = DBAdd.addCourse(courseCode, name, year, semester, units, sections, capacity);

            VBox vbox = ViewCurriculum.createCourseVBox(course);
            flwCurriculumFormList.getChildren().add(vbox);
            vbox.setOnMouseClicked(ev -> {
                this.showForms(course, vbox, false, 0);
            });

            Dialogs.mainMessageDialog.show("Adding Successful", "The entered data has been successfully added to the database.");
        } else {
            Course course = DBEdit.editCourse(focusedCourse.getCourseID(), name, year, semester, units, sections, capacity);
            flwCurriculumFormList.getChildren().remove(focusedVBox);

            VBox vbox = ViewCurriculum.createCourseVBox(course);
            flwCurriculumFormList.getChildren().add(vbox);
            vbox.setOnMouseClicked(ev -> {
                this.showForms(course, vbox, false, 0);
            });

            Dialogs.mainMessageDialog.show("Editing Successful", "The student data has been successfully updated to the database.");
        }

        closeForms();
    }

    @FXML
    public void deleteForm() {
        Runnable delete = () -> {
            DBRemove.removeCourse(focusedCourse.getCourseID());
            flwCurriculumFormList.getChildren().remove(focusedVBox);
            closeForms();
        };
        Dialogs.mainConfirmDialog.show("Delete a Course", "Are you sure you want to delete this course from the database?", delete);

    }

    @FXML
    public void showStudents() {
        curriculumStudentsController.showForms(focusedCourse);
    }


    public void showForms(Course course, VBox focusedVBox, boolean isAdding, double delay) {
        preFillForm(course);
        AppAnimations.popup(anpCurriculumForms, delay);
        this.isAdding = isAdding;
        curriculumFormsDelete.setVisible(!isAdding);
        curriculumFormsStudents.setVisible(!isAdding);
        if (isAdding) {
            this.focusedCourse = null;
            this.focusedVBox = null;
        }
        else {
            this.focusedCourse = course;
            this.focusedVBox = focusedVBox;
        }
    }

    void preFillForm(Course course) {
        if (course == null) {
            txfCurriculumFormsYear.setText(null);
            txfCurriculumFormsSemester.setText(null);
            txfCurriculumFormsCode.setText(null);
            txfCurriculumFormsSections.setText(null);
            txfCurriculumFormsName.setText(null);
            txfCurriculumFormsUnits.setText(null);
            txfCurriculumFormsLimit.setText(null);
        } else {
            txfCurriculumFormsYear.setText(String.valueOf(course.getYear()));
            txfCurriculumFormsSemester.setText(String.valueOf(course.getSemester()));
            txfCurriculumFormsCode.setText(course.getCourseName());
            txfCurriculumFormsSections.setText(String.valueOf(course.getSections()));
            txfCurriculumFormsName.setText(course.getCourseName());
            txfCurriculumFormsUnits.setText(String.valueOf(course.getUnits()));
            txfCurriculumFormsLimit.setText(String.valueOf(course.getCapacity()));
        }
    }
}
