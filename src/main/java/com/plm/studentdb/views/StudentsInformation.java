package com.plm.studentdb.views;

import com.plm.studentdb.database.DBAdd;
import com.plm.studentdb.database.DBEdit;
import com.plm.studentdb.database.DBRemove;
import com.plm.studentdb.models.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class StudentsInformation {
    @FXML AnchorPane anpStudentsInformation;

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
    }
}
