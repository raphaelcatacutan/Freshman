package com.plm.studentdb.views;

import com.plm.studentdb.Main;
import com.plm.studentdb.database.DBFind;
import com.plm.studentdb.models.Program;
import com.plm.studentdb.utils.ProgramUtils;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import com.dlsc.pdfviewfx.PDFView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class ViewReports  {

    @FXML public MenuButton mnbProgram;
    @FXML public MenuButton mnbBlock;
    @FXML public MenuButton mnbYear;

    @FXML private AnchorPane anpPDFView;

    public PDFView pdfView = new PDFView();

    public String program = null;
    public Integer block = null;
    public Integer year = null;

    public String currentFile = null;

    @FXML
    public void initialize() {
        pdfView.setShowToolBar(false);
        pdfView.setShowThumbnails(true);
        pdfView.setShowSearchResults(false);
        pdfView.setShowAll(true);

        anpPDFView.getChildren().add(pdfView);
        AnchorPane.setBottomAnchor(pdfView, 0.0);
        AnchorPane.setTopAnchor(pdfView, 0.0);
        AnchorPane.setRightAnchor(pdfView, 0.0);
        AnchorPane.setLeftAnchor(pdfView, 0.0);

        mnbBlock.getItems().forEach(e -> {
            e.setOnAction(i -> {
                mnbBlock.setText(e.getText());
                mnbBlock.setTextFill(Color.web("#000080"));
                mnbBlock.getParent().requestFocus();
                block = Integer.parseInt(String.valueOf(e.getText().charAt(6)));
                generateOfficerReport();
            });
        });
        mnbYear.getItems().forEach(e -> {
            e.setOnAction(i -> {
                mnbYear.setText(e.getText());
                mnbYear.setTextFill(Color.web("#000080"));
                mnbYear.getParent().requestFocus();
                year = Integer.parseInt(String.valueOf(e.getText().charAt(0)));
                generateOfficerReport();
            });
        });
    }

    @FXML
    public void clearFilters() {
        mnbBlock.setText("Block");
        mnbProgram.setText("Program");
        mnbYear.setText("Year");

        program = null;
        year = null;
        block = null;

        mnbProgram.setTextFill(Color.BLACK);
        mnbYear.setTextFill(Color.BLACK);
        mnbBlock.setTextFill(Color.BLACK);

        pdfView.unload();
        currentFile = null;
    }

    @FXML
    public void exportFile() {
        try {
            File current = new File(currentFile);
            Path source = Paths.get(currentFile);
            Path target = Paths.get(ProgramUtils.DOWNLOADS_DIRECTORY_PATH + current.getName());

            Files.move(source, target);
            Desktop.getDesktop().open(target.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Failed to move file to Downloads folder", e);
        }

    }

    public void generateOfficerReport() {
        Map<String, String> queries = new HashMap<>();

        StringBuilder query = new StringBuilder("SELECT * FROM students WHERE 1=1 ");

        if (program != null) {
            query.append("AND ProgramID = '").append(program).append("' ");
        }
        if (year != null) {
            query.append("AND Year = ").append(year).append(" ");
        }
        if (block != null) {
            query.append("AND Block = ").append(block).append(" ");
        }

        queries.put("main", String.valueOf(query));
        queries.put("Students", String.valueOf(query));
        String output = ProgramUtils.generateReport(ProgramUtils.STUDENTS_LIST, queries);

        pdfView.unload();
        currentFile = output;
        if (Objects.isNull(output)) return;
        try {
            File file = new File(output);
            pdfView.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void onNavigate() {
        clearFilters();
        mnbProgram.getItems().clear();
        List<Program> programs = DBFind.findPrograms(null, null, null, null);
        for (Program p: programs) {
            MenuItem e = new MenuItem(p.getProgramID());
            e.setOnAction(i -> {
                mnbProgram.setText(e.getText());
                mnbProgram.setTextFill(Color.web("#000080"));
                mnbProgram.getParent().requestFocus();
                program = e.getText();
                generateOfficerReport();
            });
            mnbProgram.getItems().add(e);
        }
    }
}


