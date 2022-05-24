package com.example.mydiary.day_fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydiary.ClassStruct;
import com.example.mydiary.ClassesRvAdapter;
import com.example.mydiary.R;
import com.example.mydiary.Student;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import io.grpc.Context;


public class MondayFragment extends Fragment {

    RecyclerView mon;
    ArrayList<String> list;

    FirebaseFirestore mStore;

    ClassesRvAdapter adapter;

    @SuppressLint("SetTextI18n")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved){

        View view = inflater.inflate(R.layout.monday_fragment,container,false);

        mon = view.findViewById(R.id.recycler_monday);
        mStore = FirebaseFirestore.getInstance();
        list = new ArrayList<>();

        fetch_Data();

        return view;
    }

    private void fetch_Data(){

        mStore.collection("Shemford").document("Timetable")
                .collection("Class "+ Student.getGrade()).document("Monday").get()
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
                        mon.setLayoutManager(layoutManager);
                        mon.setAdapter(adapter);
                    }
                });

    }

}