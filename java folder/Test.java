package com.example.maha;
import java.util.Date;

    public class Test {

        private int Tid;
        private int StudentId;
        private String Tresult;
        private Date Pdate;

        public Test(int Tid, int StudentId, String Tresult, Date Pdate) {
            super();
            this.Tid = Tid;
            this.StudentId = StudentId;
            this.Tresult = Tresult;
            this.Pdate = Pdate;
        }

        public int getTid() {
            return Tid;
        }

        public void setTid(int tid) {
            Tid = tid;
        }

        public int getStudentId() {
            return StudentId;
        }

        public void setStudentId(int studentId) {
            StudentId = studentId;
        }

        public String getTresult() {
            return Tresult;
        }

        public void setTresult(String tresult) {
            Tresult = tresult;
        }

        public Date getPdate() {
            return Pdate;
        }

        public void setPdate(Date pdate) {
            Pdate = pdate;
        }
    }




