package com.plm.studentdb.views;

import com.plm.studentdb.database.DBFind;
import com.plm.studentdb.database.DBView;
import com.plm.studentdb.models.Student;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class ViewStudents {
    @FXML public TableView<Student> tbvStudents = new TableView<>();
    @FXML public Parent studentsAddView;
    @FXML public Parent studentsMessageView;
    @FXML public Parent studentsConfirmView;
    @FXML public Label btnViewStudentsAdd;
    @FXML public TextField txfStudentsSearch;

    @FXML public StudentsForms studentsAddViewController;
    @FXML public StudentsMessage studentsMessageViewController;
    @FXML public StudentsConfirm studentsConfirmViewController;

    public static ObservableList<Student> studentsListTable = FXCollections.observableArrayList();
    public static int filterStudentId = -1;

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

        studentsAddViewController.studentsMessageViewController = studentsMessageViewController;
        studentsConfirmViewController.txfStudentsSearch = txfStudentsSearch;
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
        if (studentsListTable.size() != 1) return;
        studentsConfirmViewController.anpStudentsConfirmView.setVisible(true);
    }

    @FXML private void showEdit() {
        if (studentsListTable.size() != 1) return;
        studentsAddViewController.anpStudentsAddView.setVisible(true);
        studentsAddViewController.preFillForm(studentsListTable.getFirst());
        studentsAddViewController.isAdding = false;
    }

    @FXML private void showAdd() {
        studentsAddViewController.anpStudentsAddView.setVisible(true);
        studentsAddViewController.isAdding = true;
    }

    public void showFormEditor(double delay) {
        studentsAddView.setOpacity(0);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.25), studentsAddView);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setDelay(Duration.seconds(delay));

        ScaleTransition scaleUpTransition = new ScaleTransition(Duration.seconds(0.25), studentsAddView);
        scaleUpTransition.setFromX(0);
        scaleUpTransition.setFromY(0);
        scaleUpTransition.setToX(1.1);
        scaleUpTransition.setToY(1.1);
        scaleUpTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleUpTransition.setDelay(Duration.seconds(delay));

        ScaleTransition scaleDownTransition = new ScaleTransition(Duration.seconds(0.15), studentsAddView);
        scaleDownTransition.setFromX(1.1);
        scaleDownTransition.setFromY(1.1);
        scaleDownTransition.setToX(1);
        scaleDownTransition.setToY(1);
        scaleDownTransition.setInterpolator(Interpolator.EASE_OUT);

        SequentialTransition scaleTransition = new SequentialTransition(scaleUpTransition, scaleDownTransition);

        fadeTransition.play();
        scaleTransition.play();

        studentsAddView.setVisible(true);
    }
}
