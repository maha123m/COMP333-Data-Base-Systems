package com.example.maha;

import java.util.Date;

public class Vehicale {
    private int plateNumber;
    private int trainerID;
    private String vehicleModel;
    private int productionYear;
    private String transmissionType;
    private Date insuranceDate;
    private Date licenseDate;

    public Vehicale (int plateNumber, int trainerID, String vehicleModel, int productionYear, String transmissionType,
                   Date insuranceDate, Date licenseDate) {
        super();
        this.plateNumber = plateNumber;
        this.trainerID = trainerID;
        this.vehicleModel = vehicleModel;
        this.productionYear = productionYear;
        this.transmissionType = transmissionType;
        this.insuranceDate = insuranceDate;
        this.licenseDate = licenseDate;
    }

    public int getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(int plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(Date insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public Date  getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(Date licenseDate) {
        this.licenseDate = licenseDate;
    }
}
