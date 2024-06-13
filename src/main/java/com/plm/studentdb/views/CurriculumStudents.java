package com.plm.studentdb.views;

import com.plm.studentdb.database.DBAdd;
import com.plm.studentdb.database.DBFind;
import com.plm.studentdb.database.DBRemove;
import com.plm.studentdb.models.Course;
import com.plm.studentdb.models.Lesson;
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

        List<Lesson> lessons = DBFind.findLessons(null, null, focusedCourse.getCourseID(), null, null, null);
        List<Student> students = DBFind.findStudents(null, null, null, null, null, null, null, null);
        int numberOfStudents = 0;

        for (Student student: students) {
            boolean isEnrolled = false;
            for (Lesson lesson: lessons) {
                if (lesson.getStudentID() != student.getStudentID()) continue;
                numberOfStudents++;
                break;
            }
            CheckBox checkBox = generateStudentCheckBox(student.getStudentName(), isEnrolled);
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
        if (selected > focusedCourse.getCapacity()) {
            Dialogs.mainMessageDialog.show("Limit Reached", "Number of student enrolled in limited to " + focusedCourse.getCapacity() + " students");
            return;
        }

        // Editing
        for (Node v: students) {
            CheckBox checkbox = (CheckBox) v;
            Student student = (Student) checkbox.getUserData();

            List<Lesson> classes = DBFind.findLessons(null, student.getStudentID(), focusedCourse.getCourseID(), null, null, null);

            if (checkbox.isSelected()) {
                if (!classes.isEmpty()) continue;
                DBAdd.addLesson(student.getStudentID(), focusedCourse.getCourseID(), section, 0.0);
            }
            else {
                if (classes.isEmpty()) continue;
                DBRemove.removeLesson(classes.getFirst().getLessonID());
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
