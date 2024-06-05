package com.plm.studentdb.views;

import com.plm.studentdb.database.DBAdd;
import com.plm.studentdb.database.DBEdit;
import com.plm.studentdb.database.DBRemove;
import com.plm.studentdb.models.Course;
import javafx.fxml.FXML;
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
        String courseCode = year + "" + semester + "-" + txfCurriculumFormsCode.getText();
        int sections = Integer.parseInt(txfCurriculumFormsSections.getText());
        String name = txfCurriculumFormsName.getText();
        int units = Integer.parseInt(txfCurriculumFormsUnits.getText());

        if (isAdding) {
            Course course = DBAdd.addCourseRecord(courseCode, units, sections, name);

            VBox vbox = ViewCurriculum.createCourseVBox(course);
            flwCurriculumFormList.getChildren().add(vbox);
            vbox.setOnMouseClicked(ev -> {
                this.showForms(course, vbox, false);
            });

            Dialogs.mainMessageDialog.show("Adding Successful", "The entered data has been successfully added to the database.");
        } else {
            Course course = DBEdit.editCourseRecord(focusedCourse.getId(), courseCode, units, sections, name);
            flwCurriculumFormList.getChildren().remove(focusedVBox);

            VBox vbox = ViewCurriculum.createCourseVBox(course);
            flwCurriculumFormList.getChildren().add(vbox);
            vbox.setOnMouseClicked(ev -> {
                this.showForms(course, vbox, false);
            });

            Dialogs.mainMessageDialog.show("Editing Successful", "The student data has been successfully updated to the database.");
        }

        closeForms();
    }

    @FXML
    public void deleteForm() {
        DBRemove.removeStudentRecord(focusedCourse.getId());
        flwCurriculumFormList.getChildren().remove(focusedVBox);
        closeForms();
    }

    @FXML
    public void showStudents() {
        curriculumStudentsController.showForms(focusedCourse);
    }


    public void showForms(Course course, VBox focusedVBox, boolean isAdding) {
        preFillForm(course);
        AppAnimations.popup(anpCurriculumForms, 0);
        this.isAdding = isAdding;
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
        } else {
            String code = course.getCourseCode();
            String[] parts = code.split("-");
            String yearSemesterPart = parts[0];
            int year = Integer.parseInt(yearSemesterPart.substring(0, 4)); // Extract the 4th character for the year
            int semester = Character.getNumericValue(yearSemesterPart.charAt(4)); // Extract the 5th character for the semester
            String courseShort = parts[1];

            txfCurriculumFormsYear.setText(String.valueOf(year));
            txfCurriculumFormsSemester.setText(String.valueOf(semester));
            txfCurriculumFormsCode.setText(courseShort);
            txfCurriculumFormsSections.setText(String.valueOf(course.getSections()));
            txfCurriculumFormsName.setText(course.getCourseName());
            txfCurriculumFormsUnits.setText(String.valueOf(course.getUnits()));
        }
    }
}
