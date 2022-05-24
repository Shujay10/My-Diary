package com.example.mydiary;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class TT_ClassesStruct {

    String day;
    ArrayList<String> periods;

    public TT_ClassesStruct() {
    }

    public TT_ClassesStruct(String day, ArrayList<String> periods) {
        this.day = day;
        this.periods = periods;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<String> getPeriods() {
        return periods;
    }

    public void setPeriods(ArrayList<String> periods) {
        this.periods = periods;
    }

    @NonNull
    @Override
    public String toString() {
        return day+" "+periods.get(0);
    }

}
