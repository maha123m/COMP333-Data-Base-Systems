package com.example.maha;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentInfoQuery {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty city;
    private final StringProperty street;
    private final StringProperty phone;
    private final StringProperty wallet;

    public StudentInfoQuery(String firstName, String lastName, String city, String street, String phone, String wallet) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.city = new SimpleStringProperty(city);
        this.street = new SimpleStringProperty(street);
        this.phone = new SimpleStringProperty(phone);
        this.wallet = new SimpleStringProperty(wallet);
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

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getWallet() {
        return wallet.get();
    }

    public StringProperty walletProperty() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet.set(wallet);
    }
}

