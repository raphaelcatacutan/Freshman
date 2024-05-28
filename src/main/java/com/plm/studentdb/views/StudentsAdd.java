package com.plm.studentdb.views;

import com.plm.studentdb.database.DBAdd;
import com.plm.studentdb.models.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class StudentsAdd {

    @FXML AnchorPane anpStudentsAddView;

    @FXML TextField txfStudentsViewId;
    @FXML TextField txfStudentsViewName;
    @FXML TextField txfStudentsViewCollege;
    @FXML TextField txfStudentsViewCourse;
    @FXML TextField txfStudentsViewYear;
    @FXML TextField txfStudentsViewGwa1;
    @FXML TextField txfStudentsViewGwa2;
    @FXML TextField txfStudentsViewYearEnrolled;

    public StudentsMessage studentsMessageViewController;

    @FXML void closeAddView() {
        anpStudentsAddView.setVisible(false);
    }

    @FXML void addStudent() {
        // TODO Make sure input can be parsed
        int id = Integer.parseInt(txfStudentsViewId.getText());
        String name = txfStudentsViewName.getText();
        String college = txfStudentsViewCollege.getText();
        String course = txfStudentsViewCourse.getText();
        int year = Integer.parseInt(txfStudentsViewYear.getText());
        double gwa1 = Double.parseDouble(txfStudentsViewGwa1.getText());
        double gwa2 = Double.parseDouble(txfStudentsViewGwa2.getText());
        int yearEnrolled = Integer.parseInt(txfStudentsViewYearEnrolled.getText());

        txfStudentsViewId.setText(null);
        txfStudentsViewName.setText(null);
        txfStudentsViewCollege.setText(null);
        txfStudentsViewCourse.setText(null);
        txfStudentsViewYear.setText(null);
        txfStudentsViewGwa1.setText(null);
        txfStudentsViewGwa2.setText(null);
        txfStudentsViewYearEnrolled.setText(null);

        Student student = DBAdd.addStudentRecord(id, name, college, course, year, gwa1, gwa2, yearEnrolled);

        ViewStudents.studentsListTable.add(student);

        closeAddView();
        studentsMessageViewController.show("ADD STUDENT RECORD", "The entered data has been successfully added to the database.");
    }
}
