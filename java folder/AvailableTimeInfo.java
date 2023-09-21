package com.example.maha;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AvailableTimeInfo {
    private final StringProperty time;
    private final StringProperty day;
    private final StringProperty date;

    public AvailableTimeInfo(String time, String day, String date) {
        this.time = new SimpleStringProperty(time);
        this.day = new SimpleStringProperty(day);
        this.date = new SimpleStringProperty(date);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getDay() {
        return day.get();
    }

    public StringProperty dayProperty() {
        return day;
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
