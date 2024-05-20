module com.plm.studentdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.plm.studentdb to javafx.fxml;
    exports com.plm.studentdb;
    exports com.plm.studentdb.views;
    opens com.plm.studentdb.views to javafx.fxml;
}