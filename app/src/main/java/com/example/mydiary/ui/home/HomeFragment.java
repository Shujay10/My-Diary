package com.example.mydiary.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydiary.struct.HomeWorkStruct;
import com.example.mydiary.adapters.HomeworkRvAdapter;
import com.example.mydiary.R;


import com.example.mydiary.Student;
import com.example.mydiary.databinding.FragmentHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    RecyclerView homeWork;
    HomeworkRvAdapter adapter;
    ArrayList<HomeWorkStruct> list;

    FirebaseDatabase mDatabase;

    final String url = "https://my-diary-fb6a1-default-rtdb.asia-southeast1.firebasedatabase.app/";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mDatabase  = FirebaseDatabase.getInstance(url);
        homeWork = root.findViewById(R.id.home);
        list = new ArrayList<HomeWorkStruct>();

        setAdapter();
        store();

        return root;
    }

    private void store(){

        //list.add(new HomeWorkStruct("Math","Complete Ex no 14 from page no : 142"));

        list.clear();

        DatabaseReference reference = mDatabase.getReference("Shemford").child("Homework")
                .child("Class_"+Student.getGrade());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    HomeWorkStruct sub = snapshot1.getValue(HomeWorkStruct.class);
                    list.add(sub);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setAdapter() {

        adapter = new HomeworkRvAdapter(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        homeWork.setItemAnimator(new DefaultItemAnimator());
        homeWork.setLayoutManager(layoutManager);
        homeWork.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}