package com.plm.studentdb.models;

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
}
