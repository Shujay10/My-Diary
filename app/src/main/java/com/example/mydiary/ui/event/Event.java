package com.example.mydiary.ui.event;


import androidx.annotation.RequiresApi;

import android.os.Build;
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
import android.widget.Toast;

import com.example.mydiary.adapters.EventRvAdapter;
import com.example.mydiary.struct.EventStruct;
import com.example.mydiary.R;
import com.example.mydiary.databinding.FragmentEventBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Event extends Fragment {

    private FragmentEventBinding binding;

    SwipeRefreshLayout refreshLayout;
    RecyclerView announcement;
    EventRvAdapter adapter;
    ArrayList<EventStruct> list;

    FirebaseDatabase mDatabase;
    final String url = "https://my-diary-fb6a1-default-rtdb.asia-southeast1.firebasedatabase.app/";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentEventBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mDatabase  = FirebaseDatabase.getInstance(url);
        announcement = root.findViewById(R.id.eventRview);
        refreshLayout = root.findViewById(R.id.reload);
        list = new ArrayList<>();

        setAdapter();
        fetchData();

        checkIf_empty();

        try {
            remEvent();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                fetchData();
                checkIf_empty();
                try {
                    remEvent();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

        return root;
    }

    private void checkIf_empty() {
        if(list.isEmpty())
            Toast.makeText(getContext(), " No Events ", Toast.LENGTH_LONG).show();
    }

    private void fetchData(){

        list.clear();

        DatabaseReference reference = mDatabase.getReference("Shemford").child("Events");

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


    private void remEvent() throws ParseException {

        //System.out.println("******Hi*******");
        DatabaseReference reference = mDatabase.getReference("Shemford").child("Events");

        Date current = new Date();


        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    EventStruct sub = snapshot1.getValue(EventStruct.class);

                    Date ending_time = null;
                    String dt = sub.getEnd();

                    try {
                        ending_time = format.parse(dt);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(current.after(ending_time) || ending_time.equals(current)){
                        System.out.println("After");
                        //reference.child(sub.getTitle()).removeValue();
                        //adapter.notifyDataSetChanged();
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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