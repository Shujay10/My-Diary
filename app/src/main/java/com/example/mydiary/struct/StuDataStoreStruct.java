package com.example.mydiary.struct;

public class StuDataStoreStruct {

    String name;
    String phonePrimary;
    String phoneSecondary;
    String parentName;
    String email;
    String regNo;
    String grade;

    public StuDataStoreStruct() {
    }

    public StuDataStoreStruct(String name, String phonePrimary, String phoneSecondary, String parentName,
                              String email, String regNo, String grade) {
        this.name = name;
        this.phonePrimary = phonePrimary;
        this.phoneSecondary = phoneSecondary;
        this.parentName = parentName;
        this.email = email;
        this.regNo = regNo;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonePrimary() {
        return phonePrimary;
    }

    public void setPhonePrimary(String phonePrimary) {
        this.phonePrimary = phonePrimary;
    }

    public String getPhoneSecondary() {
        return phoneSecondary;
    }

    public void setPhoneSecondary(String phoneSecondary) {
        this.phoneSecondary = phoneSecondary;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
