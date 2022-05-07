package com.example.mydiary;

public class HomeWorkStruct {

    String subject;
    //Boolean isDone;
    String work;

    public HomeWorkStruct() {

    }

    public HomeWorkStruct(String subject, String work) {
        this.subject = subject;
        this.work = work;
    }
/*
    public HomeWorkStruct(String subject, Boolean isDone, String work) {
        this.subject = subject;
        this.isDone = isDone;
        this.work = work;
    }
*/
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
/*
    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
*/
    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
