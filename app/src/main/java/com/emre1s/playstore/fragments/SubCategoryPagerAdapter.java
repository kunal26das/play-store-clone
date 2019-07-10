package com.emre1s.playstore.fragments;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SubCategoryPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 4;

    public SubCategoryPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("Emre1s", "POSITION: " + position);
        switch (position) {
            case 0: {
                return ForYouFragment.newInstance();
            }
            case 1: {
                return AppCategoryFragment.newInstance();
            }
            case 2: {
                return ForYouFragment.newInstance();
            }
            case 3: {
                return AppCategoryFragment.newInstance();
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

}
