package com.plm.studentdb.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ViewDashboard {
    @FXML PieChart pieStatisticsChart;

    @FXML
    public void initialize() {
        // Create the data for the PieChart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Category A", 30),
                new PieChart.Data("Category B", 25),
                new PieChart.Data("Category C", 45)
        );

        pieStatisticsChart.setData(pieChartData);

        for (PieChart.Data data : pieChartData) {
            Tooltip tooltip = new Tooltip(data.getName() + ": " + (int) data.getPieValue());
            Tooltip.install(data.getNode(), tooltip);

            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                tooltip.show(data.getNode(), event.getScreenX(), event.getScreenY() + 10);
            });
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                tooltip.hide();
            });
        }
    }
}
