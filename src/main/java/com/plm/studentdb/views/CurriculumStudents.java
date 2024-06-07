package com.plm.studentdb.views;

import com.plm.studentdb.database.DBAdd;
import com.plm.studentdb.database.DBFind;
import com.plm.studentdb.database.DBRemove;
import com.plm.studentdb.database.DBView;
import com.plm.studentdb.models.Class;
import com.plm.studentdb.models.Course;
import com.plm.studentdb.models.Student;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;


public class CurriculumStudents {
    @FXML AnchorPane anpCurriculumStudents;
    @FXML VBox vbxCurriculumStudentsList;
    @FXML TextField txfCurriculumStudentsSection;
    @FXML Label curriculumStudentsNumber;

    public Course focusedCourse;
    public CurriculumForms curriculumFormsController;

    @FXML
    public void closeForms() {
        AppAnimations.popdown(anpCurriculumStudents, 0);
        vbxCurriculumStudentsList.getChildren().clear();
        txfCurriculumStudentsSection.setText(null);
    }

    public void showForms(Course focusedCourse) {
        AppAnimations.popup(anpCurriculumStudents, 0.2);

        this.focusedCourse = focusedCourse;

        String code = focusedCourse.getCourseCode();
        String[] parts = code.split("-");
        String yearSemesterPart = parts[0];
        int year = Integer.parseInt(yearSemesterPart.substring(0, 4)); // Extract the 4th character for the year
        int semester = Character.getNumericValue(yearSemesterPart.charAt(4)); // Extract the 5th character for the semester
        String courseShort = parts[1];

        List<Class> Classes = DBFind.findClass(courseShort, year, semester);
        List<Student> students = DBView.viewStudentRecord();
        int numberOfStudents = 0;

        for (Student student: students) {
            boolean isEnrolled = false;
            for (Class aClass: Classes) {
                isEnrolled = Integer.parseInt(aClass.getStudentNumber()) == student.getStudentId();
                if (isEnrolled) {
                    numberOfStudents++;
                    break;
                };
            }
            CheckBox checkBox = generateStudentCheckBox(student.getName(), isEnrolled);
            checkBox.setUserData(student);
            vbxCurriculumStudentsList.getChildren().add(checkBox);
        }
        curriculumStudentsNumber.setText("(" + numberOfStudents + ") of Students Enrolled");
    }

    public void confirmForms() {
        ObservableList<Node> students = vbxCurriculumStudentsList.getChildren();
        int section = Integer.parseInt(txfCurriculumStudentsSection.getText());

        int selected = 0;
        for (Node v: students) {
            CheckBox checkbox = (CheckBox) v;
            if (checkbox.isSelected()) selected++;
        }
        if (selected > focusedCourse.getLimit()) {
            Dialogs.mainMessageDialog.show("Limit Reached", "Number of student enrolled in limited to " + focusedCourse.getLimit() + " students");
            return;
        }

        // Editing
        for (Node v: students) {
            CheckBox checkbox = (CheckBox) v;
            Student student = (Student) checkbox.getUserData();

            String code = focusedCourse.getCourseCode();
            String[] parts = code.split("-");
            String yearSemesterPart = parts[0];
            int year = Integer.parseInt(yearSemesterPart.substring(0, 4)); // Extract the 4th character for the year
            int semester = Character.getNumericValue(yearSemesterPart.charAt(4)); // Extract the 5th character for the semester
            String courseShort = parts[1];

            List<Class> classes = DBFind.findClass(String.valueOf(student.getStudentId()), courseShort, year, semester);

            if (checkbox.isSelected()) {
                if (!classes.isEmpty()) continue;
                DBAdd.addClassRecord(String.valueOf(student.getStudentId()), courseShort, section, year, semester, 0.0);
            }
            else {
                if (classes.isEmpty()) continue;
                DBRemove.removeClassRecord(classes.getFirst().getId());
            }
        }

        closeForms();
    }

    public static CheckBox generateStudentCheckBox(String studentName, boolean isSelected) {
        CheckBox checkBox = new CheckBox(studentName);
        checkBox.setMnemonicParsing(false);
        checkBox.setSelected(true);
        checkBox.setSelected(isSelected);
        checkBox.setFont(new Font("Century Gothic", 17.0));
        return checkBox;
    }
}
