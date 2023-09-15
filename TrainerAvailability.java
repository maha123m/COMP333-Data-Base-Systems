package com.example.maha;

public class TrainerAvailability {
    private int trainerID;

    private String avalableDay;
    private String avalableTime;

    public TrainerAvailability(int trainerID, String avalableDay, String avalableTime) {
        this.trainerID = trainerID;
        this.avalableDay = avalableDay;
        this.avalableTime = avalableTime;
    }

    public TrainerAvailability(String avalableDay, String avalableTime) {
    }

    // Generate getters and setters for the properties

    public int getTrainerID() {
        return trainerID;
    }

    public String getAvalableDay() {
        return avalableDay;
    }

    public String getAvalableTime() {
        return avalableTime;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public void setAvalableDay(String avalableDay) {

        this.avalableDay = avalableDay;
    }

    public void setAvalableTime(String avalableTime) {
        this.avalableTime = avalableTime;
    }

}
