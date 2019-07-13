package com.emre1s.playstore.fragments;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SubCategoryPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 4;
    private int tabPosition;

    public SubCategoryPagerAdapter(FragmentManager fragmentManager, int tabPosition) {
        super(fragmentManager);
        this.tabPosition = tabPosition;
       // Log.d(SubCategoryPagerAdapter.class.getSimpleName(), "Tab position: " + tabPosition);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(SubCategoryPagerAdapter.class.getSimpleName(), "POSITION: " + tabPosition);
        switch (position) {
            case 0: {
                return ForYouFragment.newInstance(tabPosition);
            }
            case 1: {
                return new TopChartsTabFragment();
            }
            case 2: {
                return AppCategoryFragment.newInstance(tabPosition);
            }
            case 3: {
                return FamilyAppFragment.newInstance();
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Log.d(SubCategoryPagerAdapter.class.getSimpleName(), "I was called");
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

}
