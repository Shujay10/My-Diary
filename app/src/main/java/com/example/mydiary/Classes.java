package com.example.mydiary;

import java.util.ArrayList;

public class Classes {

    static ArrayList<TT_ClassesStruct> fetchedData = new ArrayList<>();

    public Classes() {
    }

    public static ArrayList<TT_ClassesStruct> getFetchedData() {
        return fetchedData;
    }

    public static void setFetchedData(ArrayList<TT_ClassesStruct> fetchedData) {
        Classes.fetchedData = fetchedData;
    }
}
