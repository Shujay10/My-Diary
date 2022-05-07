package com.example.mydiary.ui.event;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydiary.EventRvAdapter;
import com.example.mydiary.EventStruct;
import com.example.mydiary.HomeWorkStruct;
import com.example.mydiary.HomeworkRvAdapter;
import com.example.mydiary.R;
import com.example.mydiary.Student;
import com.example.mydiary.databinding.FragmentEventBinding;
import com.example.mydiary.databinding.FragmentHomeBinding;
import com.example.mydiary.ui.home.HomeViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event extends Fragment {

    private FragmentEventBinding binding;

    SwipeRefreshLayout refreshLayout;
    RecyclerView announcement;
    EventRvAdapter adapter;
    ArrayList<EventStruct> list;

    FirebaseDatabase mDatabase;
    final String url = "https://my-diary-fb6a1-default-rtdb.asia-southeast1.firebasedatabase.app/";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentEventBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mDatabase  = FirebaseDatabase.getInstance(url);
        announcement = root.findViewById(R.id.eventRview);
        refreshLayout = root.findViewById(R.id.reload);
        list = new ArrayList<EventStruct>();

        setAdapter();

        fetchData();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                fetchData();
                remEvent();
            }
        });

        //storeStatic();

        return root;
    }

    private void checkIf_empty() {

        if(list.isEmpty()){
            list.add(new EventStruct("No Event",".","____","____"));
        }else {
            list.clear();
        }

        adapter.notifyDataSetChanged();
        //store();
    }

    private void fetchData(){

        list.clear();

        DatabaseReference reference = mDatabase.getReference("Shemford").child("Events");
                //.child("Class_"+ Student.getGrade());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    EventStruct sub = snapshot1.getValue(EventStruct.class);
                    list.add(sub);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @SuppressLint("SimpleDateFormat")
    private void remEvent() {

        DatabaseReference reference = mDatabase.getReference("Shemford").child("Events");

        Date date = new Date();

        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy_hh:mm:ss");
        String strDate= formatter.format(date);

        String[] split_time = strDate.split("_");
        String[] time = split_time[1].split(":");

        int sys_hrs = Integer.parseInt(time[0]);
        int sys_min = Integer.parseInt(time[1]);

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    EventStruct sub = snapshot1.getValue(EventStruct.class);

                    String dt = sub.getEnd();

                    String[] gt_time = dt.split("_");
                    String[] note_time = gt_time[1].split(":");

                    int not_hrs = Integer.parseInt(note_time[0]);
                    int not_min = Integer.parseInt(note_time[1]);

                    if(gt_time[0].equals(split_time[0])){
                        if(not_min >= sys_min || not_hrs>=sys_hrs){
                            if(not_hrs>=sys_hrs ){
                                reference.child(sub.getTitle()).removeValue();
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //fetchData();
        //checkIf_empty();

    }

    private void storeStatic(){
        String txt = "Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.";
        String txt2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Gravida cum sociis natoque penatibus et magnis. Nibh cras pulvinar mattis nunc sed. Justo laoreet sit amet cursus sit amet dictum sit amet. Egestas maecenas pharetra convallis posuere morbi leo urna molestie at. Ullamcorper a lacus vestibulum sed. Sit amet aliquam id diam maecenas ultricies mi. Arcu vitae elementum curabitur vitae nunc " +
                "sed velit dignissim sodales. Tristique senectus et netus et. Dictumst quisque sagittis purus sit amet volutpat.";
        list.add(new EventStruct("Kuruksastra",txt,"20/02/2022","28/02/2022"));
        list.add(new EventStruct("Test case Big",txt2,"20/02/2022","28/02/2022"));
    }

    private void setAdapter() {

        adapter = new EventRvAdapter(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        announcement.setItemAnimator(new DefaultItemAnimator());
        announcement.setLayoutManager(layoutManager);
        announcement.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}