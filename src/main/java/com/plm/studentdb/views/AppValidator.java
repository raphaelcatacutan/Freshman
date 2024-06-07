package com.plm.studentdb.views;

import javafx.scene.control.TextField;

public class AppValidator {
    public static void restrictToInteger(TextField textField, int maxDigits) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*") || newValue.length() > maxDigits) {
                textField.setText(oldValue);
            }
        });
    }
}
