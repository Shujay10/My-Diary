package com.example.mydiary.day_fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydiary.struct.ClassStruct;
import com.example.mydiary.adapters.ClassesRvAdapter;
import com.example.mydiary.R;
import com.example.mydiary.Student;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class FridayFragment extends Fragment {

    RecyclerView fri;
    ArrayList<String> list;

    FirebaseFirestore mStore;

    ClassesRvAdapter adapter;

    @SuppressLint("SetTextI18n")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved){

        View view = inflater.inflate(R.layout.friday_fragment,container,false);

        fri= view.findViewById(R.id.recycler_friday);


        mStore = FirebaseFirestore.getInstance();
        list = new ArrayList<>();

        fetch_Data();

        return view;
    }

    private void fetch_Data(){

        mStore.collection(Student.getSchool()).document("Timetable")
                .collection("Class "+ Student.getGrade()).document("Friday").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        ClassStruct temp = documentSnapshot.toObject(ClassStruct.class);
                        if (temp != null) {
                            list = temp.getPeriods();
                        }

                        adapter = new ClassesRvAdapter(list);
                        RecyclerView.LayoutManager layoutManager;
                        layoutManager = new LinearLayoutManager(getActivity());
                        fri.setLayoutManager(layoutManager);
                        fri.setAdapter(adapter);
                    }
                });

    }

}