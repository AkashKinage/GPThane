package com.example.gpthane.Teacher;

public class modelStudentDisplay {

    String name, email, enrollmentno, phone, year;

    modelStudentDisplay(){

    }

    public modelStudentDisplay(String name, String email, String enrollmentno, String phone, String year) {
        this.name = name;
        this.email = email;
        this.enrollmentno = enrollmentno;
        this.phone = phone;
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnrollmentno() {
        return enrollmentno;
    }

    public void setEnrollmentno(String enrollmentno) {
        this.enrollmentno = enrollmentno;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
