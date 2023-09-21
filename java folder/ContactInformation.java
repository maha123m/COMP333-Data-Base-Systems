package com.example.maha;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ContactInformation {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty phone1;
    private final StringProperty phone2;

    public ContactInformation(String firstName, String lastName, String phone1, String phone2) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phone1 = new SimpleStringProperty(phone1);
        this.phone2 = new SimpleStringProperty(phone2);
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

    public String getPhone1() {
        return phone1.get();
    }

    public StringProperty phone1Property() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1.set(phone1);
    }

    public String getPhone2() {
        return phone2.get();
    }

    public StringProperty phone2Property() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2.set(phone2);
    }
}

