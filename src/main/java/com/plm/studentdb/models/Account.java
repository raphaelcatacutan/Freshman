package com.plm.studentdb.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {
    private final IntegerProperty accountID;
    private final StringProperty email;
    private final StringProperty password;
    private final StringProperty access;

    public Account(int accountID, String email, String password, String access) {
        this.accountID = new SimpleIntegerProperty(accountID);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.access = new SimpleStringProperty(access);
    }

    public int getAccountID() {
        return accountID.get();
    }

    public IntegerProperty accountIDProperty() {
        return accountID;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getAccess() {
        return access.get();
    }

    public StringProperty accessProperty() {
        return access;
    }
}
