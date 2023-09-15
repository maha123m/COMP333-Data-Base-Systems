package com.example.maha;

public class TrainnerToStudent {
    private int studentId;
    private int trainerId;

    public TrainnerToStudent(int studentId, int trainerId) {
        this.studentId = studentId;
        this.trainerId = trainerId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
}
