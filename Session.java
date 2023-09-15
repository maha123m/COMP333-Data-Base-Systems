package com.example.maha;
import java.util.Date;

public class Session {
    private int sessionId;
    private int studentID;
    private int plateNumber;
    private int trainerID;
    private int sessionCost;
    private int sessioncompleted;
    private String sessionDay;
    private String sessionTime;
    private Date sessionDate;
    private String sessionStatus;

    public Session(int sessionId, int studentID, int plateNumber, int trainerID, int sessionCost,
                   int sessioncompleted, String sessionDay, String sessionTime, Date sessionDate,
                   String sessionStatus) {
        this.sessionId = sessionId;
        this.studentID = studentID;
        this.plateNumber = plateNumber;
        this.trainerID = trainerID;
        this.sessionCost = sessionCost;
        this.sessioncompleted = sessioncompleted;
        this.sessionDay = sessionDay;
        this.sessionTime = sessionTime;
        this.sessionDate = sessionDate;
        this.sessionStatus = sessionStatus;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getStudentId() {
        return studentID;
    }

    public void setStudentId(int studentId) {
        this.studentID = studentId;
    }

    public int getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(int plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getTrainerId() {
        return trainerID;
    }

    public void setTrainerId(int trainerId) {
        this.trainerID= trainerId;
    }

    public int getSessionCost() {
        return sessionCost;
    }

    public void setSessionCost(int sessionCost) {
        this.sessionCost = sessionCost;
    }

    public int getSessionCompleted() {
        return sessioncompleted;
    }

    public void setSessionCompleted(int sessionCompleted) {
        this.sessioncompleted= sessionCompleted;
    }

    public String getSessionDay() {
        return sessionDay;
    }

    public void setSessionDay(String sessionDay) {
        this.sessionDay = sessionDay;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }


    public int getTrainerID() {
        return trainerID;
    }
}




