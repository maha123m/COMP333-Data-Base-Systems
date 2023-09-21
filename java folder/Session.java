package com.example.maha;
import java.util.Date;

public class Session {
        private int sessionId;
        private int studentId;
        private int plateNumber;
        private int trainerId;
        private int sessionCost;
        private int sessionCompleted;
        private String sessionDay;
        private String sessionTime;
        private Date sessionDate;
        private String sessionStatus;

        public Session(int sessionId, int studentId, int plateNumber, int trainerId, int sessionCost,
                       int sessionCompleted, String sessionDay, String sessionTime, Date sessionDate,
                       String sessionStatus) {
            this.sessionId = sessionId;
            this.studentId = studentId;
            this.plateNumber = plateNumber;
            this.trainerId = trainerId;
            this.sessionCost = sessionCost;
            this.sessionCompleted = sessionCompleted;
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
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        public int getPlateNumber() {
            return plateNumber;
        }

        public void setPlateNumber(int plateNumber) {
            this.plateNumber = plateNumber;
        }

        public int getTrainerId() {
            return trainerId;
        }

        public void setTrainerId(int trainerId) {
            this.trainerId = trainerId;
        }

        public int getSessionCost() {
            return sessionCost;
        }

        public void setSessionCost(int sessionCost) {
            this.sessionCost = sessionCost;
        }

        public int getSessionCompleted() {
            return sessionCompleted;
        }

        public void setSessionCompleted(int sessionCompleted) {
            this.sessionCompleted = sessionCompleted;
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
    }




