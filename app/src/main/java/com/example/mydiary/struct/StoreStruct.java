package com.example.mydiary.struct;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class StoreStruct {

    String name;
    String grade;
    String email;
    String phoNoP;
    String phoNoS;
    String birthday;
    String ParentsName;

    String regNo;
    String school;

    ArrayList<String> subject;
    ArrayList<String> classes;

    @NonNull
    @Override
    public String toString() {

        String print1 = subject +"   "+classes;
        String print2 = regNo + "    " + school;
        String print3 = regNo + "    " + school + "     " + subject +"   "+classes;

        return print3;
    }

    public StoreStruct(String name, String grade, String email, String phoNoP, String phoNoS,
                       String birthday, String parentsName, String regNo, String school) {
        this.name = name;
        this.grade = grade;
        this.email = email;
        this.phoNoP = phoNoP;
        this.phoNoS = phoNoS;
        this.birthday = birthday;
        this.ParentsName = parentsName;
        this.regNo = regNo;
        this.school = school;
    }

    public StoreStruct(String regNo, String school, ArrayList<String> subjects, ArrayList<String> classes) {
        this.regNo = regNo;
        this.school = school;
        this.subject = subjects;
        this.classes = classes;
    }

    public StoreStruct(String regNo, String school) {
        this.regNo = regNo;
        this.school = school;
    }

    public StoreStruct(ArrayList<String> subjects, ArrayList<String> classes) {
        this.subject = subjects;
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoNoP() {
        return phoNoP;
    }

    public void setPhoNoP(String phoNoP) {
        this.phoNoP = phoNoP;
    }

    public String getPhoNoS() {
        return phoNoS;
    }

    public void setPhoNoS(String phoNoS) {
        this.phoNoS = phoNoS;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getParentsName() {
        return ParentsName;
    }

    public void setParentsName(String parentsName) {
        ParentsName = parentsName;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public ArrayList<String> getSubject() {
        return subject;
    }

    public void setSubject(ArrayList<String> subject) {
        this.subject = subject;
    }

    public ArrayList<String> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<String> classes) {
        this.classes = classes;
    }
}
