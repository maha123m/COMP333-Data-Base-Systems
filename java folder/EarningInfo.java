package com.example.maha;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EarningInfo {
    private final StringProperty firstName;
    private final StringProperty totalSession;
    private final StringProperty totalCost;

    public EarningInfo(String firstName, String totalSession, String totalCost) {
        this.firstName = new SimpleStringProperty(firstName);
        this.totalSession = new SimpleStringProperty(totalSession);
        this.totalCost = new SimpleStringProperty(totalCost);
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

    public String getTotalSession() {
        return totalSession.get();
    }

    public StringProperty totalSessionProperty() {
        return totalSession;
    }

    public void setTotalSession(String totalSession) {
        this.totalSession.set(totalSession);
    }

    public String getTotalCost() {
        return totalCost.get();
    }

    public StringProperty totalCostProperty() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost.set(totalCost);
    }
}

