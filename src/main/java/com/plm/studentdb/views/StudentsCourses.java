package com.plm.studentdb.views;

import com.plm.studentdb.database.DBFind;
import com.plm.studentdb.models.Course;
import com.plm.studentdb.models.Class;
import com.plm.studentdb.models.Student;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.util.List;

public class StudentsCourses {
    @FXML AnchorPane anpStudentsCourses;
    @FXML TextField txfStudentsCoursesSearch;
    @FXML ListView<Course> lstStudentCoursesList;

    @FXML public void initialize() {

    }

    @FXML
    private void search(KeyEvent event) {
        if (event.getCode() != KeyCode.ENTER) return;
        String input = txfStudentsCoursesSearch.getText();
        List<Course> courses;
        // lstStudentCoursesList // add all courses with this listview
        // each courses will be displayed by CheckBox createCustomCheckBox(String courseCode)
    }

    @FXML
    private void clearSearch() {
        txfStudentsCoursesSearch.setText("");
        // TODO Add all couses
    }

    @FXML
    void closeForms() {
        preFillForm(null);
        AppAnimations.popdown(anpStudentsCourses, 0.2);
    }

    void preFillForm(Student student) {

    }

    void showData(int student_id, String couseCode) {
        List<Course> courses = DBFind.findCourse(couseCode);
        List<Class> enrolled = DBFind.findClass(student_id);

        for (Course course: courses) {
            CheckBox checkBox = generateCourseCheckBox(couseCode);
        }
    }

    void showForms(Student student, double delay) {
        preFillForm(student);
        AppAnimations.popup(anpStudentsCourses, delay);
    }


    public static CheckBox generateCourseCheckBox(String courseCode) {
        CheckBox checkBox = new CheckBox(courseCode);
        checkBox.setMnemonicParsing(false);
        checkBox.setSelected(true);
        checkBox.setFont(new Font("Century Gothic", 17.0));
        return checkBox;
    }
}
