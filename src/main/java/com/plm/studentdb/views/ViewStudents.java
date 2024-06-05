package com.plm.studentdb.views;

import com.plm.studentdb.database.DBFind;
import com.plm.studentdb.database.DBView;
import com.plm.studentdb.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ViewStudents {
    @FXML public TableView<Student> tbvStudents = new TableView<>();
    @FXML public Parent studentsInformation;
    @FXML public Label btnViewStudentsAdd;
    @FXML public TextField txfStudentsSearch;

    @FXML public StudentsInformation studentsInformationController;

    public static ObservableList<Student> studentsListTable = FXCollections.observableArrayList();
    public static int filterStudentId = -1;

    @FXML public ImageView studentsBack;

    @FXML
    public void initialize() {
        // Table
        tbvStudents.setEditable(true);

        TableColumn<Student, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setPrefWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        idColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.17));

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setPrefWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.25));

        TableColumn<Student, String> courseColumn = new TableColumn<>("Course");
        courseColumn.setPrefWidth(100);
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        courseColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.24));

        TableColumn<Student, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setPrefWidth(100);
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.12));

        TableColumn<Student, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setPrefWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.15));

        tbvStudents.getColumns().addAll(idColumn, nameColumn, courseColumn, yearColumn, statusColumn);
        tbvStudents.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        tbvStudents.getColumns().forEach(studentTableColumn -> {
            studentTableColumn.setReorderable(false);
            studentTableColumn.setResizable(false);
        });
        tbvStudents.getSelectionModel().clearSelection();

        tbvStudents.setItems(studentsListTable);
        getData();

        // studentsInformationController.studentsMessageViewController = studentsMessageViewController;
        //studentsConfirmViewController.txfStudentsSearch = txfStudentsSearch;
    }

    private void getData() {
        if (filterStudentId >= 0) studentsListTable.setAll(DBFind.findStudentRecord(filterStudentId));
        else studentsListTable.addAll(DBView.viewStudentRecord());
    }

    @FXML private void search(KeyEvent event) {
        if (event.getCode() != KeyCode.ENTER) return;
        String input = txfStudentsSearch.getText();
        if (!input.isEmpty()) {
            try {
                filterStudentId = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                filterStudentId = -1;
            }
        } else {
            filterStudentId = -1;
        }
        getData();
    }

    @FXML private void delete() {
        if (studentsListTable.size() != 1) {
            //studentsMessageViewController.show("Invalid Target", "Please enter a valid Student ID to find within the database.");
            return;
        };
        //AppAnimations.popup(studentsConfirmViewController.anpStudentsConfirmView, 0.2);
    }

    @FXML private void showEdit() {
        if (studentsListTable.size() != 1) {
            //studentsMessageViewController.show("Invalid Target", "Please enter a valid Student ID to find within the database.");
            return;
        };

        //AppAnimations.popup(studentsInformationController, 0.2);
        //studentsInformationController.preFillForm(studentsListTable.getFirst());
        //studentsInformationController.isAdding = false;
    }

    @FXML private void showAdd() {
        //AppAnimations.popup(studentsInformationController, 0.2);
        //studentsInformationController.isAdding = true;
    }
}
