package com.example.mydiary.ui.timetable;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mydiary.R;
import com.example.mydiary.TabPager;
import com.example.mydiary.databinding.FragmentTimeTableBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TimeTable extends Fragment implements TabLayout.OnTabSelectedListener {

    private FragmentTimeTableBinding binding;

    TabLayout tabLayout;
    ViewPager viewPager;
    TabPager tabPager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentTimeTableBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        init(root);

        return root;
    }


    private void init(View root){

        tabLayout = root.findViewById(R.id.tab);
        tabLayout.addTab(tabLayout.newTab().setText("Mon"));
        tabLayout.addTab(tabLayout.newTab().setText("Tue"));
        tabLayout.addTab(tabLayout.newTab().setText("Wed"));
        tabLayout.addTab(tabLayout.newTab().setText("Thu"));
        tabLayout.addTab(tabLayout.newTab().setText("Fri"));
        tabLayout.addTab(tabLayout.newTab().setText("Sat"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = root.findViewById(R.id.pager);
        tabPager = new TabPager(getChildFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(tabPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}