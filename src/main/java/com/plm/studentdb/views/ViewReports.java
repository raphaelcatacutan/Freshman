package com.plm.studentdb.views;

import com.plm.studentdb.Main;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import com.dlsc.pdfviewfx.PDFView;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class ViewReports  {

    @FXML private MenuButton mnbCollege;
    @FXML private MenuButton mnbProgram;
    @FXML private MenuButton mnbBlock;
    @FXML private MenuButton mnbYear;

    @FXML private AnchorPane anpPDFView;

    public void initialize() {
        PDFView pdfView = new PDFView();
        pdfView.setShowToolBar(false);
        pdfView.setShowThumbnails(true);
        pdfView.setShowSearchResults(false);
        pdfView.setShowAll(false);

        anpPDFView.getChildren().add(pdfView);
        AnchorPane.setBottomAnchor(pdfView, 0.0);
        AnchorPane.setTopAnchor(pdfView, 0.0);
        AnchorPane.setRightAnchor(pdfView, 0.0);
        AnchorPane.setLeftAnchor(pdfView, 0.0);

        pdfView.load(Objects.requireNonNull(Main.class.getResourceAsStream("reports/example-pdf-portrait.pdf")));
    }
}


