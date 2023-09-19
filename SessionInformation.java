package com.example.maha;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SessionInformation {
    private final StringProperty sessionDate;
    private final StringProperty sessionDay;
    private final StringProperty sessionTime;

    public SessionInformation(String sessionDate, String sessionDay, String sessionTime) {
        this.sessionDate = new SimpleStringProperty(sessionDate);
        this.sessionDay = new SimpleStringProperty(sessionDay);
        this.sessionTime = new SimpleStringProperty(sessionTime);
    }

    public String getSessionDate() {
        return sessionDate.get();
    }

    public StringProperty sessionDateProperty() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate.set(sessionDate);
    }

    public String getSessionDay() {
        return sessionDay.get();
    }

    public StringProperty sessionDayProperty() {
        return sessionDay;
    }

    public void setSessionDay(String sessionDay) {
        this.sessionDay.set(sessionDay);
    }

    public String getSessionTime() {
        return sessionTime.get();
    }

    public StringProperty sessionTimeProperty() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime.set(sessionTime);
    }
}
