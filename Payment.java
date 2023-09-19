package com.example.maha;

import java.util.Date;

public class Payment {

    private int Pid;
    private int StudentId;
    private int amount;
    private String Pmethood ;
    private String  Pstatus;
    private Date Pdate;




    public Payment(int Pid ,int StudentId, int amount , String Pmethood ,  String Pstatus, Date Pdate) {
        super();

        this.Pid = Pid;
        this.StudentId = StudentId;
        this.amount = amount;
        this.Pmethood = Pmethood;
        this.Pstatus = Pstatus;
        this.Pdate = Pdate;

    }


    public String getPmethood() {
        return Pmethood;
    }

    public void setPmethood(String pmethood) {
        Pmethood = pmethood;
    }


    public String getPstatus() {
        return Pstatus;
    }

    public void setPstatus(String pstatus) {
        Pstatus = pstatus;
    }

    public Date getPdate() {
        return Pdate;
    }

    public void setPdate(Date pdate) {
        Pdate = pdate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public int getPid() {
        return Pid;
    }

    public void setPid(int pid) {
        Pid = pid;
    }
}
