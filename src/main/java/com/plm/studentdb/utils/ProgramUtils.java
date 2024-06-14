package com.plm.studentdb.utils;

import com.plm.studentdb.Main;
import com.plm.studentdb.database.DBConnection;
import com.plm.studentdb.views.Dialogs;
import javafx.application.Platform;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProgramUtils {

    public static final String LOCALAPPDATA = System.getenv("LOCALAPPDATA");
    public static final String AUTHOR = "PLMDev";
    public static final String APPNAME = "Students";

    public static final String APP_DIRECTORY_PATH = LOCALAPPDATA + File.separator + AUTHOR + File.separator + APPNAME + "\\";
    public static final String DOWNLOADS_DIRECTORY_PATH = System.getProperty("user.home") + "/Downloads/";

    public static final String STUDENTS_LIST = "students-list";

    static {
        File directory = new File(APP_DIRECTORY_PATH);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Directories created: " + APP_DIRECTORY_PATH);
            } else {
                System.err.println("Failed to create directories: " + APP_DIRECTORY_PATH);
            }
        }
    }

    public static String generateReport(String JRXMLname, Map<String, String> queries) {
        String outputPath = APP_DIRECTORY_PATH + JRXMLname + ".pdf";
        try {
            String pathToJRXML = Objects.requireNonNull(Main.class.getResource("reports/" + JRXMLname + ".jrxml")).toURI().getPath();
            JasperDesign jDesign = JRXmlLoader.load(pathToJRXML);
            queries.keySet().forEach(dataset -> {
                if (dataset.equals("main")) {
                    JRDesignQuery mainQuery = (JRDesignQuery) jDesign.getQuery();
                    mainQuery.setText(queries.get(dataset));
                    jDesign.setQuery(mainQuery);
                } else {
                    JRDesignDataset jDataset = (JRDesignDataset) jDesign.getDatasetMap().get(dataset);
                    if (jDataset == null) return;
                    JRDesignQuery query = (JRDesignQuery) jDataset.getQuery();
                    query.setText(queries.get(dataset));
                }
            });

            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, DBConnection.getConnection());
            if (jPrint.getPages().isEmpty()) {
                Platform.runLater(() -> {
                    Dialogs.mainMessageDialog.show("Unexpected Error", "The document cannot be generated since one or more query resulted to an empty set");
                });
                return null;
            };
            JasperExportManager.exportReportToPdfFile(jPrint, outputPath);
        } catch (Exception e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                Dialogs.mainMessageDialog.show("Unexpected Error", "There is an error generating your report. Your document might be empty and cannot be generated since one or more query resulted to an empty ResultSet");
            });
        }

        return outputPath;
    }
}
