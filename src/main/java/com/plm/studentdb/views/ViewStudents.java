package com.plm.studentdb.views;

import com.plm.studentdb.models.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewStudents {
    @FXML TableView<Student> tbvStudents = new TableView<>();

    @FXML
    public void initialize() {
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

        // Add some sample data
        tbvStudents.getItems().addAll(
                new Student(1, 1, "John Doe", "Engineering College", "Computer Science", 2, 3.5, 3.7, 3.6, "Active", 2022),
                new Student(2, 2, "Jane Smith", "Science College", "Mathematics", 1, 3.4, 3.0, 3.2, "Active", 2023),
                new Student(3, 3, "Alice Johnson", "Business College", "Business Administration", 3, 3.8, 3.9, 3.85, "Active", 2021),
                new Student(4, 4, "Robert Brown", "Engineering College", "Electrical Engineering", 4, 3.2, 3.3, 3.25, "Active", 2020),
                new Student(5, 5, "Emily Davis", "Arts College", "Fine Arts", 2, 3.6, 3.5, 3.55, "Active", 2022),
                new Student(6, 6, "Michael Wilson", "Science College", "Physics", 3, 3.9, 4.0, 3.95, "Active", 2021),
                new Student(7, 7, "Sarah Miller", "Engineering College", "Mechanical Engineering", 1, 3.1, 3.2, 3.15, "Active", 2023),
                new Student(8, 8, "David Anderson", "Business College", "Economics", 4, 3.4, 3.5, 3.45, "Active", 2020),
                new Student(9, 9, "Laura Moore", "Science College", "Chemistry", 2, 3.3, 3.4, 3.35, "Active", 2022),
                new Student(10, 10, "James Taylor", "Engineering College", "Civil Engineering", 3, 3.7, 3.8, 3.75, "Active", 2021)
        );
        tbvStudents.getColumns().forEach(studentTableColumn -> {
            studentTableColumn.setReorderable(false);
            studentTableColumn.setResizable(false);
        });
        tbvStudents.getSelectionModel().clearSelection();

    }
}
