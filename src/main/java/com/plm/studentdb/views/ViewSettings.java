package com.plm.studentdb.views;

import com.plm.studentdb.Main;
import com.plm.studentdb.database.DBAdd;
import com.plm.studentdb.database.DBFind;
import com.plm.studentdb.database.DBRemove;
import com.plm.studentdb.models.Account;
import com.plm.studentdb.models.Program;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;

import java.util.List;
import java.util.Objects;

public class ViewSettings {

    @FXML public ListView<Program> lsvProgramsList;
    @FXML public ListView<Account> lsvAccountsList;
    @FXML public Button btnProgramsListAdd;
    @FXML public Button btnAccountsListAdd;
    @FXML public Label lblProgramsListMessage;
    @FXML public Label lblAccountsListMessage;
    @FXML public ImageView imvViewSettingsBack;

    public static ObservableList<Program> programsListView = FXCollections.observableArrayList();
    public static ObservableList<Account> accountsListView = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        programsListView.setAll(DBFind.findPrograms(null, null, null, null));
        accountsListView.setAll(DBFind.findAccounts(null, null, null, null, null, null));

        lsvProgramsList.setItems(programsListView);
        lsvAccountsList.setItems(accountsListView);

        lsvProgramsList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Program program, boolean empty) {
                super.updateItem(program, empty);
                setGraphic(empty || program == null ? null : createProgramVBox(program));
            }
            @Override
            public void updateSelected(boolean selected) {
                if (selected) {
                    getListView().getSelectionModel().clearSelection();
                }
            }
        });
        lsvAccountsList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Account account, boolean empty) {
                super.updateItem(account, empty);
                setGraphic(empty || account == null ? null : createAccountVBox(account));
            }
            @Override
            public void updateSelected(boolean selected) {
                if (selected) {
                    getListView().getSelectionModel().clearSelection();
                }
            }
        });
    }

    @FXML
    public void addProgram() {
        boolean isAdding = false;
        for (Program p : programsListView) {
            if (!Objects.equals(p.getProgramID(), "NULL")) continue;
            isAdding = true;
            break;
        }
        if (isAdding) {
            Dialogs.mainMessageDialog.show("Invalid Action", "You have an unsaved program item. Please save it before adding a new one.");
            return;
        };
        Program program = DBAdd.addProgram("NULL", "NULL", "NULL");
        programsListView.addFirst(program);
    }

    @FXML
    public void addAccount() {
        boolean isAdding = false;
        for (Account a : accountsListView) {
            if (!Objects.equals(a.getName(), "NULL")) continue;
            isAdding = true;
            break;
        }
        if (isAdding) {
            Dialogs.mainMessageDialog.show("Invalid Action", "You have an unsaved account item. Please save it before adding a new one.");
            return;
        };
        Account account = DBAdd.addAccount("NULL", "NULL", "NULL", "NULL");
        accountsListView.addFirst(account);
    }

    public void getData() {
        // if (!ProgramConstants.accountAccess.contains("ADMIN")) return;
        programsListView.setAll(DBFind.findPrograms(null, null, null, null));
        accountsListView.setAll(DBFind.findAccounts(null, null, null, null, null, null));
    }

    public VBox createProgramVBox(Program program) {
        // Create the VBox
        VBox vbox = new VBox();
        vbox.setUserData(program);
        vbox.setAlignment(javafx.geometry.Pos.TOP_RIGHT);
        vbox.setPrefHeight(174.0);
        vbox.setPrefWidth(490.0);
        vbox.setSpacing(15.0);
        vbox.setStyle("-fx-background-color: #f3f3f3; -fx-background-radius: 10;");
        VBox.setMargin(vbox, new Insets(20, 20, 20, 20));

        DropShadow vboxDropShadow = new DropShadow();
        vboxDropShadow.setHeight(20.0);
        vboxDropShadow.setRadius(14.5);
        vboxDropShadow.setWidth(20.0);
        vboxDropShadow.setColor(new Color(0, 0, 0, 0.1));
        vbox.setEffect(vboxDropShadow);
        vbox.setPadding(new Insets(25.0, 25.0, 25.0, 25.0));

        // Create the first HBox
        HBox hbox1 = new HBox();
        hbox1.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        hbox1.setPrefHeight(0.0);
        hbox1.setPrefWidth(467.0);
        hbox1.setSpacing(15.0);
        hbox1.setPadding(new Insets(-30.0, 0, -30.0, 0));

        // First Button
        Button button1 = new Button();
        button1.setAlignment(javafx.geometry.Pos.CENTER);
        button1.setPrefHeight(20.0);
        button1.setPrefWidth(20.0);
        button1.setStyle("-fx-background-color: #e8e8e8; -fx-background-radius: 50;");
        button1.setTextFill(Color.web("#810013"));
        button1.setFont(new Font("Century Gothic", 13.0));
        button1.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        button1.setCursor(Cursor.HAND);

        ImageView imageView1 = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/icons/trash-bin.png"))));
        imageView1.setFitHeight(15.0);
        imageView1.setFitWidth(15.0);
        imageView1.setPreserveRatio(true);
        imageView1.setPickOnBounds(true);

        ColorAdjust colorAdjust1 = new ColorAdjust();
        colorAdjust1.setBrightness(0.6);
        colorAdjust1.setContrast(0.81);
        colorAdjust1.setHue(-0.03);
        colorAdjust1.setSaturation(1.0);
        imageView1.setEffect(colorAdjust1);
        button1.setGraphic(imageView1);

        DropShadow button1DropShadow = new DropShadow();
        button1DropShadow.setHeight(20.0);
        button1DropShadow.setRadius(9.5);
        button1DropShadow.setWidth(20.0);
        button1DropShadow.setColor(new Color(0, 0, 0, 0.15));
        button1.setEffect(button1DropShadow);

        HBox.setMargin(button1, new Insets(-20.0, 0, -30.0, 0));

        // Second Button
        Button button2 = new Button();
        button2.setAlignment(javafx.geometry.Pos.CENTER);
        button2.setPrefHeight(20.0);
        button2.setPrefWidth(20.0);
        button2.setStyle("-fx-background-color: #e8e8e8; -fx-background-radius: 50;");
        button2.setTextFill(Color.web("#1a3691"));
        button2.setFont(new Font("Century Gothic", 13.0));
        button2.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        button2.setCursor(Cursor.HAND);

        ImageView imageView2 = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/icons/pen.png"))));
        imageView2.setFitHeight(15.0);
        imageView2.setFitWidth(15.0);
        imageView2.setPreserveRatio(true);
        imageView2.setPickOnBounds(true);

        ColorAdjust colorAdjust2 = new ColorAdjust();
        colorAdjust2.setBrightness(0.28);
        colorAdjust2.setContrast(-0.23);
        colorAdjust2.setHue(-0.74);
        colorAdjust2.setSaturation(0.5);
        imageView2.setEffect(colorAdjust2);
        button2.setGraphic(imageView2);

        DropShadow button2DropShadow = new DropShadow();
        button2DropShadow.setHeight(20.0);
        button2DropShadow.setRadius(9.5);
        button2DropShadow.setWidth(20.0);
        button2DropShadow.setColor(new Color(0, 0, 0, 0.15));
        button2.setEffect(button2DropShadow);

        HBox.setMargin(button2, new Insets(-20.0, 0, -30.0, 0));

        hbox1.getChildren().addAll(button1, button2);

        // Create the second HBox
        HBox hbox2 = new HBox();
        hbox2.setPrefHeight(100.0);
        hbox2.setPrefWidth(200.0);
        hbox2.setSpacing(10.0);

        // First VBox in second HBox
        VBox vbox1 = new VBox();
        vbox1.setPrefHeight(79.0);
        vbox1.setPrefWidth(214.0);
        vbox1.setSpacing(5.0);

        Label label1 = new Label("Program Code");
        label1.getStyleClass().add("label-text");
        label1.setFont(new Font("Century Gothic", 12.0));
        label1.setGraphicTextGap(5.0);

        TextField textField1 = new TextField();
        textField1.setPrefHeight(26.0);
        textField1.setPrefWidth(173.0);
        textField1.setPromptText("BSCS");
        if (!Objects.equals(program.getProgramID(), "NULL")) textField1.setText(program.getProgramID());
        textField1.setDisable(!Objects.equals(program.getProgramID(), "NULL"));
        textField1.setStyle("-fx-background-color: #e8e8e8; -fx-background-radius: 10;");
        textField1.setFont(new Font("Century Gothic", 13.0));
        textField1.setPadding(new Insets(15.0));

        vbox1.getChildren().addAll(label1, textField1);

        // Second VBox in second HBox
        VBox vbox2 = new VBox();
        vbox2.setSpacing(5.0);

        Label label2 = new Label("Program Name");
        label2.getStyleClass().add("label-text");
        label2.setFont(new Font("Century Gothic", 12.0));
        label2.setGraphicTextGap(5.0);

        TextField textField2 = new TextField();
        textField2.setPrefHeight(26.0);
        textField2.setPrefWidth(441.0);
        textField2.setPromptText("Bachelor of Science in Computer Science");
        if (!Objects.equals(program.getProgramName(), "NULL")) textField2.setText(program.getProgramName());
        textField2.setStyle("-fx-background-color: #e8e8e8; -fx-background-radius: 10;");
        textField2.setFont(new Font("Century Gothic", 13.0));
        textField2.setPadding(new Insets(15.0));

        vbox2.getChildren().addAll(label2, textField2);

        hbox2.getChildren().addAll(vbox1, vbox2);

        // Create the third VBox
        VBox vbox3 = new VBox();
        vbox3.setSpacing(5.0);

        Label label3 = new Label("College Name");
        label3.getStyleClass().add("label-text");
        label3.setFont(new Font("Century Gothic", 12.0));
        label3.setGraphicTextGap(5.0);

        TextField textField3 = new TextField();
        textField3.setPromptText("College of Information Systems and Technology Management");
        if (!Objects.equals(program.getCollege(), "NULL")) textField3.setText(program.getCollege());
        textField3.setStyle("-fx-background-color: #e8e8e8; -fx-background-radius: 10;");
        textField3.setFont(new Font("Century Gothic", 13.0));
        textField3.setPadding(new Insets(15.0));

        vbox3.getChildren().addAll(label3, textField3);

        vbox.getChildren().addAll(hbox1, hbox2, vbox3);

        button1.setOnMouseClicked(ev -> {
            // Delete
            Dialogs.mainConfirmDialog.show("Confirm Deletion", "Are you sure you want to delete this program from the database?", () -> {
                DBRemove.removeProgram(program.getProgramID());
                programsListView.remove(program);
            });
        });
        button2.setOnMouseClicked(ev -> {
            // Edit
            String programID = textField1.getText();
            String programName = textField2.getText();
            String college = textField3.getText();

            if (programID.isEmpty()) {
                Dialogs.mainMessageDialog.show("Invalid Action", "Enter a valid Program ID to proceed with the intended action.");
                return;
            };
            Program addedProgram = DBAdd.addProgram(programID, programName, college);
            DBRemove.removeProgram("NULL");
            programsListView.remove(program);
            programsListView.addFirst(addedProgram);

            vbox.setUserData(addedProgram);

            Dialogs.mainMessageDialog.show("Program Saved", programID + " is saved in the Database. ProgramID is now read-only");
        });

        return vbox;
    }

    public VBox createAccountVBox(Account account) {
        // Root VBox
        VBox root = new VBox();
        root.setUserData(account);
        root.setPrefHeight(367.0);
        root.setPrefWidth(340.0);
        root.setSpacing(10.0);
        root.setStyle("-fx-background-color: #f3f3f3; -fx-background-radius: 10;");
        VBox.setMargin(root, new Insets(20, 20, 20, 20));

        // Create the first HBox
        HBox hbox1 = new HBox();
        hbox1.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        hbox1.setPrefHeight(0.0);
        hbox1.setPrefWidth(467.0);
        hbox1.setSpacing(15.0);
        hbox1.setPadding(new Insets(-30.0, 0, -30.0, 0));

        // First Button
        Button button1 = new Button();
        button1.setAlignment(javafx.geometry.Pos.CENTER);
        button1.setPrefHeight(20.0);
        button1.setPrefWidth(20.0);
        button1.setStyle("-fx-background-color: #e8e8e8; -fx-background-radius: 50;");
        button1.setTextFill(Color.web("#810013"));
        button1.setFont(new Font("Century Gothic", 13.0));
        button1.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        button1.setCursor(Cursor.HAND);

        ImageView imageView1 = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/icons/trash-bin.png"))));
        imageView1.setFitHeight(15.0);
        imageView1.setFitWidth(15.0);
        imageView1.setPreserveRatio(true);
        imageView1.setPickOnBounds(true);

        ColorAdjust colorAdjust1 = new ColorAdjust();
        colorAdjust1.setBrightness(0.6);
        colorAdjust1.setContrast(0.81);
        colorAdjust1.setHue(-0.03);
        colorAdjust1.setSaturation(1.0);
        imageView1.setEffect(colorAdjust1);
        button1.setGraphic(imageView1);

        DropShadow button1DropShadow = new DropShadow();
        button1DropShadow.setHeight(20.0);
        button1DropShadow.setRadius(9.5);
        button1DropShadow.setWidth(20.0);
        button1DropShadow.setColor(new Color(0, 0, 0, 0.15));
        button1.setEffect(button1DropShadow);

        HBox.setMargin(button1, new Insets(-20.0, 0, -30.0, 0));

        // Second Button
        Button button2 = new Button();
        button2.setAlignment(javafx.geometry.Pos.CENTER);
        button2.setPrefHeight(20.0);
        button2.setPrefWidth(20.0);
        button2.setStyle("-fx-background-color: #e8e8e8; -fx-background-radius: 50;");
        button2.setTextFill(Color.web("#1a3691"));
        button2.setFont(new Font("Century Gothic", 13.0));
        button2.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        button2.setCursor(Cursor.HAND);

        ImageView imageView2 = new ImageView(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/icons/pen.png"))));
        imageView2.setFitHeight(15.0);
        imageView2.setFitWidth(15.0);
        imageView2.setPreserveRatio(true);
        imageView2.setPickOnBounds(true);

        ColorAdjust colorAdjust2 = new ColorAdjust();
        colorAdjust2.setBrightness(0.28);
        colorAdjust2.setContrast(-0.23);
        colorAdjust2.setHue(-0.74);
        colorAdjust2.setSaturation(0.5);
        imageView2.setEffect(colorAdjust2);
        button2.setGraphic(imageView2);

        DropShadow button2DropShadow = new DropShadow();
        button2DropShadow.setHeight(20.0);
        button2DropShadow.setRadius(9.5);
        button2DropShadow.setWidth(20.0);
        button2DropShadow.setColor(new Color(0, 0, 0, 0.15));
        button2.setEffect(button2DropShadow);

        HBox.setMargin(button2, new Insets(-20.0, 0, -30.0, 0));

        hbox1.getChildren().addAll(button1, button2);
        root.getChildren().add(hbox1);

        // First VBox with Label and TextField (Name)
        VBox vbox1 = new VBox();
        vbox1.setPrefHeight(79.0);
        vbox1.setPrefWidth(214.0);
        vbox1.setSpacing(5.0);
        Label nameLabel = new Label("Name");
        nameLabel.setFont(Font.font("Century Gothic", 12.0));
        nameLabel.getStyleClass().add("label-text");
        vbox1.getChildren().add(nameLabel);
        TextField nameField = new TextField();
        nameField.setPrefHeight(26.0);
        nameField.setPrefWidth(173.0);
        nameField.setPromptText("Dela Cruz, Juan");
        if (!Objects.equals(account.getName(), "NULL")) nameField.setText(account.getName());
        nameField.setStyle("-fx-background-color: #e8e8e8; -fx-background-radius: 10;");
        nameField.setFont(Font.font("Century Gothic", 13.0));
        nameField.setPadding(new Insets(15.0));
        vbox1.getChildren().add(nameField);
        root.getChildren().add(vbox1);

        // Second VBox with Label and TextField (Email)
        VBox vbox2 = new VBox();
        vbox2.setSpacing(5.0);
        Label emailLabel = new Label("Email");
        emailLabel.setFont(Font.font("Century Gothic", 12.0));
        emailLabel.getStyleClass().add("label-text");
        vbox2.getChildren().add(emailLabel);
        TextField emailField = new TextField();
        emailField.setPrefHeight(26.0);
        emailField.setPrefWidth(441.0);
        emailField.setPromptText("jdelacruz@gmail.com");
        if (!Objects.equals(account.getEmail(), "NULL")) emailField.setText(account.getEmail());
        emailField.setStyle("-fx-background-color: #e8e8e8; -fx-background-radius: 10;");
        emailField.setFont(Font.font("Century Gothic", 13.0));
        emailField.setPadding(new Insets(15.0));
        vbox2.getChildren().add(emailField);
        root.getChildren().add(vbox2);

        // Third VBox with Label and TextField (Password)
        VBox vbox3 = new VBox();
        vbox3.setSpacing(5.0);
        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("Century Gothic", 12.0));
        passwordLabel.getStyleClass().add("label-text");
        vbox3.getChildren().add(passwordLabel);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("admin");
        if (!Objects.equals(account.getPassword(), "NULL")) passwordField.setText(account.getPassword());
        passwordField.setStyle("-fx-background-color: #e8e8e8; -fx-background-radius: 10;");
        passwordField.setFont(Font.font("Century Gothic", 13.0));
        passwordField.setPadding(new Insets(15.0));
        vbox3.getChildren().add(passwordField);
        root.getChildren().add(vbox3);

        // Fourth VBox with Label and TextField (College Access)
        VBox vbox4 = new VBox();
        vbox4.setLayoutX(35.0);
        vbox4.setLayoutY(188.0);
        vbox4.setSpacing(5.0);
        Label collegeAccessLabel = new Label("College Access");
        collegeAccessLabel.setFont(Font.font("Century Gothic", 12.0));
        collegeAccessLabel.getStyleClass().add("label-text");
        vbox4.getChildren().add(collegeAccessLabel);
        TextField collegeAccessField = new TextField();
        collegeAccessField.setPromptText("ALL");
        if (!Objects.equals(account.getAccess(), "NULL")) collegeAccessField.setText(account.getAccess());
        collegeAccessField.setStyle("-fx-background-color: #e8e8e8; -fx-background-radius: 10;");
        collegeAccessField.setFont(Font.font("Century Gothic", 13.0));
        collegeAccessField.setPadding(new Insets(15.0));
        vbox4.getChildren().add(collegeAccessField);
        root.getChildren().add(vbox4);

        // Setting root VBox padding and effect
        root.setPadding(new Insets(30.0));
        root.setEffect(new DropShadow(20.0, Color.rgb(0, 0, 0, 0.1)));
        root.setCursor(Cursor.DEFAULT);

        button1.setOnMouseClicked(ev -> {
            // Delete
            Dialogs.mainConfirmDialog.show("Confirm Deletion", "Are you sure you want to delete this account from the database?", () -> {
                DBRemove.removeAccount(account.getAccountID());
                accountsListView.remove(account);
            });
        });
        button2.setOnMouseClicked(ev -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String access = collegeAccessField.getText();

            Account addedAccount = DBAdd.addAccount(name, email, password, access);
            List<Account> accounts = DBFind.findAccounts(null, "NULL", "NULL", "NULL", "NULL", null);
            if (!accounts.isEmpty()) {
                Account removedAccount = accounts.getFirst();
                DBRemove.removeAccount(removedAccount.getAccountID());
            }
            accountsListView.remove(account);
            accountsListView.addFirst(addedAccount);

            root.setUserData(addedAccount);

            Dialogs.mainMessageDialog.show("Account Saved", name + " is saved in the Database with access to " + access);

        });

        return root;
    }
}
