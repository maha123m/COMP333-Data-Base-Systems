package com.example.maha;

public class StudentAssignment {
	
	private int studentID;
    private String newTrainerFirstName;
    private String newTrainerLastName;

    public StudentAssignment(int studentID, String newTrainerFirstName, String newTrainerLastName) {
        this.studentID = studentID;
        this.newTrainerFirstName = newTrainerFirstName;
        this.newTrainerLastName = newTrainerLastName;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getNewTrainerFirstName() {
        return newTrainerFirstName;
    }

    public String getNewTrainerLastName() {
        return newTrainerLastName;
    }
}
