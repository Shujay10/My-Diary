package com.example.mydiary.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mydiary.day_fragments.FridayFragment;
import com.example.mydiary.day_fragments.MondayFragment;
import com.example.mydiary.day_fragments.SaturdayFragment;
import com.example.mydiary.day_fragments.ThursdayFragment;
import com.example.mydiary.day_fragments.TuesdayFragment;
import com.example.mydiary.day_fragments.WednedayFragment;

public class TabPager extends FragmentStatePagerAdapter {

    int tabCount;

    public TabPager(@NonNull FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:{
                MondayFragment mondayFragment = new MondayFragment();
                return mondayFragment;
            }
            case 1:{
                TuesdayFragment tuesdayFragment = new TuesdayFragment();
                return tuesdayFragment;
            }
            case 2:{
                WednedayFragment wednedayFragment = new WednedayFragment();
                return wednedayFragment;
            }
            case 3:{
                ThursdayFragment thusdayFragment = new ThursdayFragment();
                return thusdayFragment;
            }
            case 4:{
                FridayFragment fridayFragment = new FridayFragment();
                return fridayFragment;
            }
            case 5:{
                SaturdayFragment saturdayFragment = new SaturdayFragment();
                return saturdayFragment;
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
