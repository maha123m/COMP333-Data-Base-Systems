package com.example.maha;
import java.util.Date;

public class Student {

    private int studentId;
    private String studentFirstName;
    private String studentmiddleName;
    private String studentLastName;
    private String cityAddress;
    private String streetAddress;
    private int phone1;
    private int phone2;
    private int wallet;
    private String gender;
    private Date birthdate;

    public Student(int studentId, String studentFirstName, String studentmiddleName, String studentLastName, String cityAddress, String streetAddress, int phone1, int phone2, int wallet, String gender, Date birthdate) {
        this.studentId = studentId;
        this.studentFirstName = studentFirstName;
        this.studentmiddleName = studentmiddleName;
        this.studentLastName = studentLastName;
        this.cityAddress = cityAddress;
        this.streetAddress = streetAddress;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.wallet = wallet;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }



    public String getStudentmiddleName() {
        return studentmiddleName;
    }

    public void setStudentmiddleName(String studentmiddleName) {
        this.studentmiddleName = studentmiddleName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
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

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}






