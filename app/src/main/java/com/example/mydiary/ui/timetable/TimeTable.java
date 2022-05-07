package com.example.mydiary.ui.timetable;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.mydiary.EventRvAdapter;
import com.example.mydiary.EventStruct;
import com.example.mydiary.R;
import com.example.mydiary.TT_DaysRvAdapter;
import com.example.mydiary.databinding.FragmentEventBinding;
import com.example.mydiary.databinding.FragmentTimeTableBinding;

import java.util.ArrayList;

public class TimeTable extends Fragment {

    private FragmentTimeTableBinding binding;

    RecyclerView days;
    RecyclerView classes;
    ArrayList<String> s_days;
    ArrayList<String> s_classes;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentTimeTableBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        days = root.findViewById(R.id.recycler_day);
        classes = root.findViewById(R.id.recycler_class);
        s_days = new ArrayList<String>();
        s_classes = new ArrayList<String>();

        storeDays();
        setAdapterDays();

        return root;
    }

    private void storeDays() {
        s_days.add("Monday");
        s_days.add("Tuesday");
        s_days.add("Wednesday");
        s_days.add("Thursday");
        s_days.add("Friday");
        s_days.add("Saturday");
        s_days.add("Clear");

    }

    private void setAdapterDays() {

        TT_DaysRvAdapter adapter = new TT_DaysRvAdapter(s_days,getContext(),classes);
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        days.setItemAnimator(new DefaultItemAnimator());
        days.setLayoutManager(layoutManager);
        days.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}