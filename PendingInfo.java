package com.example.maha;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PendingInfo {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty amount;

    public PendingInfo(String firstName, String lastName, String amount) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.amount = new SimpleStringProperty(amount);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }
}
