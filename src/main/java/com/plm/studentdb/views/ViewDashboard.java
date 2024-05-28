package com.plm.studentdb.views;

import com.plm.studentdb.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.io.IOException;
import java.util.Objects;

public class ViewDashboard {
    public Button addWinButtonText;
    @FXML PieChart pieStatisticsChart;

    @FXML
    public void initialize() {
    }

    public void addWinButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/view-addstudrec.fxml")));
        Scene scene = new Scene(root);
        Stage primarystage = new Stage();
        primarystage.setScene(scene);
        primarystage.initModality(Modality.WINDOW_MODAL);
        primarystage.initOwner(addWinButtonText.getScene().getWindow());
        primarystage.setScene(new Scene(root,500,450));
        primarystage.show();
    }
}
