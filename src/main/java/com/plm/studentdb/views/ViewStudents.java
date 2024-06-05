package com.plm.studentdb.views;

import com.plm.studentdb.database.DBFind;
import com.plm.studentdb.database.DBView;
import com.plm.studentdb.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class ViewStudents {
    @FXML public TableView<Student> tbvStudents = new TableView<>();
    @FXML public Parent studentsInformation;
    @FXML public Label btnViewStudentsAdd;
    @FXML public TextField txfStudentsSearch;

    @FXML public StudentsInformation studentsInformationController;

    public static ObservableList<Student> studentsListTable = FXCollections.observableArrayList();


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

        TableColumn<Student, String> courseColumn = new TableColumn<>("Program");
        courseColumn.setPrefWidth(100);
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("program"));
        courseColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.24));

        TableColumn<Student, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setPrefWidth(100);
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.12));

        TableColumn<Student, String> statusColumn = new TableColumn<>("Block");
        statusColumn.setPrefWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("block"));
        statusColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.15));

        tbvStudents.getColumns().addAll(idColumn, nameColumn, courseColumn, yearColumn, statusColumn);
        tbvStudents.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        tbvStudents.getColumns().forEach(studentTableColumn -> {
            studentTableColumn.setReorderable(false);
            studentTableColumn.setResizable(false);
        });
        tbvStudents.getSelectionModel().clearSelection();
        tbvStudents.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tbvStudents.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Student student = row.getItem();
                    studentsInformationController.show(student, 0.2, false);
                }
            });
            return row;
        });

        tbvStudents.setItems(studentsListTable);
        getData();
    }

    private void getData() {
        studentsListTable.setAll(DBView.viewStudentRecord());
    }

    @FXML private void search(KeyEvent event) {
        if (event.getCode() != KeyCode.ENTER) return;
        String input = txfStudentsSearch.getText();
        List<Student> students = DBFind.findStudentRecord(input);
        studentsListTable.setAll(students);
    }

    @FXML private void clearSearch() {
        txfStudentsSearch.setText("");
        getData();
    }

    @FXML private void showAdd() {
        studentsInformationController.show(null, 0.2, true);
    }
}
