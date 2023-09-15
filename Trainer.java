package com.example.maha;

public class Trainer {


	private String trainerFirstName;
	private String trainerLastName;
	private String cityAddress;
	private String streetAddress;
	private int numberOfVehiclesOwns;
	private int phone1;
	private int phone2;
	private int trainerID;


	public Trainer(int id ,String firstName, String lastName, int noOfVehicles,  String cityAddress, String streetAddress,
				   int phone1, int phone2) {
		super();
		this.trainerFirstName = firstName;
		this.trainerLastName = lastName;
		this.streetAddress = streetAddress;
		this.cityAddress = cityAddress;
		this.numberOfVehiclesOwns = noOfVehicles;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.trainerID=id;
	}


	public String getTrainerFirstName() {
		return trainerFirstName;
	}


	public void setTrainerFirstName(String trainerFirstName) {
		this.trainerFirstName = trainerFirstName;
	}


	public String getTrainerLastName() {
		return trainerLastName;
	}


	public void setTrainerLastName(String trainerLastName) {
		this.trainerLastName = trainerLastName;
	}


	public String getCityAddress() {
		return cityAddress;
	}


	public void setCityAddress(String cityAddress) {
		this.cityAddress = cityAddress;
	}


	public String getStreetAddress() {
		return streetAddress;
	}


	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}


	public int getNumberOfVehiclesOwns() {
		return numberOfVehiclesOwns;
	}


	public void setNumberOfVehiclesOwns(int numberOfVehiclesOwns) {
		this.numberOfVehiclesOwns = numberOfVehiclesOwns;
	}


	public int getPhone1() {
		return phone1;
	}


	public void setPhone1(int phone1) {
		this.phone1 = phone1;
	}


	public int getPhone2() {
		return phone2;
	}


	public void setPhone2(int phone2) {
		this.phone2 = phone2;
	}


	public int getTrainerID() {
		return trainerID;
	}


	public void setTrainerID(int trainerID) {
		this.trainerID = trainerID;
	}




}