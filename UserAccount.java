package com.example.maha;
public class UserAccount {
    private int idUserAccounts;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private int trainerID;
    private int studentID;
    private String className;
    
    public UserAccount(int idUserAccounts, String firstname, String lastname, String username, String password,
                       int trainerID, int studentID, String className) {
        this.idUserAccounts = idUserAccounts;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.trainerID = trainerID;
        this.studentID = studentID;
        this.className = className;
    }
    
    public int getIdUserAccounts() {
        return idUserAccounts;
    }
    
    public void setIdUserAccounts(int idUserAccounts) {
        this.idUserAccounts = idUserAccounts;
    }
    
    public String getFirstname() {
        return firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getTrainerID() {
        return trainerID;
    }
    
    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }
    
    public int getStudentID() {
        return studentID;
    }
    
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
}
