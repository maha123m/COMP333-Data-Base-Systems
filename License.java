package com.example.maha;
import java.util.Date;
public class License {

        private int studentId;
        private int licenseId;
        private String licensetype;


        public License(int studentId, int licenseId, String licensetype) {
            this.studentId = studentId;
            this.licenseId = licenseId;
            this.licensetype = licensetype;

        }

        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        public int getLicenseId() {
            return licenseId;
        }

        public void setLicenseId(int licenseId) {
            this.licenseId = licenseId;
        }

        public String getLicensetype() {
            return licensetype;
        }

        public void setLicensetype(String licensetype) {
            this.licensetype = licensetype;
        }

    }


