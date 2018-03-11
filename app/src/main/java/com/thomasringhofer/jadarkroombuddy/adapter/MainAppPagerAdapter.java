package com.thomasringhofer.jadarkroombuddy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.thomasringhofer.jadarkroombuddy.RecentDevelopmentProcessesFragment;
import com.thomasringhofer.jadarkroombuddy.WorkingSolutionsFragment;

/**
 * Created by Thomas on 27.02.2018.
 */

public class MainAppPagerAdapter extends FragmentPagerAdapter {

    static final int NUM_OF_ELEMENTS = 2;

    public MainAppPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_OF_ELEMENTS;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return RecentDevelopmentProcessesFragment.newInstance();
            case 1:
                return WorkingSolutionsFragment.newInstance("","");
        }
        return null;
    }

}
