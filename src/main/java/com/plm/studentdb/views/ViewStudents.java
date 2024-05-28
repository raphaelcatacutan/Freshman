package com.plm.studentdb.views;

import com.plm.studentdb.database.DBView;
import com.plm.studentdb.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewStudents {
    @FXML public TableView<Student> tbvStudents = new TableView<>();
    @FXML public Parent studentsAddView;
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
        nameColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.27));

        TableColumn<Student, String> courseColumn = new TableColumn<>("Course");
        courseColumn.setPrefWidth(100);
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        courseColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.2));

        TableColumn<Student, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setPrefWidth(100);
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.07));

        TableColumn<Student, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setPrefWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.minWidthProperty().bind(tbvStudents.widthProperty().multiply(0.19));

        tbvStudents.getColumns().addAll(idColumn, nameColumn, courseColumn, yearColumn, statusColumn);
        tbvStudents.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        tbvStudents.getColumns().forEach(studentTableColumn -> {
            studentTableColumn.setReorderable(false);
            studentTableColumn.setResizable(false);
        });
        tbvStudents.getSelectionModel().clearSelection();


        tbvStudents.setItems(studentsListTable);
        getData();

        // Add Forms
        studentsAddView.setVisible(false);
    }

    private void getData() {
        System.out.println("Getting Records");
        studentsListTable.addAll(DBView.viewStudentRecord());
    }
}
