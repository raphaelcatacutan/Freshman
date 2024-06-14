package com.plm.studentdb.views;

import com.plm.studentdb.database.DBFind;
import com.plm.studentdb.models.Account;
import com.plm.studentdb.models.Program;
import com.plm.studentdb.models.Student;
import com.plm.studentdb.utils.ProgramConstants;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class MainLogin {
    @FXML private AnchorPane anpMainLogin;
    @FXML private TextField txfUserEmail;
    @FXML private PasswordField txfUserPassword;
    @FXML public Button logInButton;

    private final String predefinedUsername = "admin@plmcs.edu.ph"; //old: admin@plm.edu.ph
    private final String predefinedPassword = "PLMCS@admin2024"; //old: 123456

    public Parent mainView;
    public Pane pneBackgroundFade;

    @FXML public void initialize() {
        txfUserEmail.requestFocus();
    }

    @FXML
    public void login() {
        String enteredEmail = txfUserEmail.getText();
        String enteredPassword = txfUserPassword.getText();
        boolean byPassLogin = false;

        List<Account> accounts = DBFind.findAccounts(null, null, enteredEmail, enteredPassword, null, null);
        List<Student> students = DBFind.findStudents(null, null, null, null, null, enteredEmail, enteredPassword, null);

        ProgramConstants.accountAccess.clear();

        if (!accounts.isEmpty()) {
            Account account = accounts.getFirst();
            ProgramConstants.accountID = account.getAccountID();
            ProgramConstants.accountName = account.getName();

            if (account.getAccess().equals("ALL")) {
                ProgramConstants.accountAccess.add("ALL");
            }
            else {
                // FIXME What if the college does not exist?
                List<Program> programs = DBFind.findPrograms(null, null, account.getAccess(), null);
                programs.forEach(p -> ProgramConstants.accountAccess.add(p.getProgramID()));
            }

            byPassLogin = true;
        }
        else if (!students.isEmpty()) {
            Student student = students.getFirst();
            ProgramConstants.accountID = student.getStudentID();
            ProgramConstants.accountName = student.getStudentName();
            ProgramConstants.accountAccess.add("STUDENT");
            byPassLogin = true;
        }

        if (byPassLogin) {
            mainView.toFront();
            anpMainLogin.toBack();
            txfUserEmail.setText(null);
            txfUserPassword.setText(null);
            AppAnimations.login();
            Dialogs.mainMessageDialog.close();
        } else {
            Dialogs.mainMessageDialog.show("Invalid Credentials", "Please check your username and password and try again.");
        }
    }
}