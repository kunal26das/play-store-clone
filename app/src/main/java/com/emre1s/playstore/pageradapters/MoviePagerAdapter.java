package com.emre1s.playstore.pageradapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.emre1s.playstore.fragments.AppCategoryFragment;
import com.emre1s.playstore.fragments.FamilyAppFragment;
import com.emre1s.playstore.fragments.ForYouFragment;
import com.emre1s.playstore.fragments.TopChartsTabFragment;

public class MoviePagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 4;
    private int tabPosition;

    public MoviePagerAdapter (FragmentManager fragmentManager, int tabPosition) {
        super(fragmentManager);
        this.tabPosition = tabPosition;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(MoviePagerAdapter.class.getSimpleName(), "POSITION: " + tabPosition);
        switch (position) {
            case 0: {
                return ForYouFragment.newInstance(tabPosition);
            }
            case 1: {
                return TopChartsTabFragment.newInstance(tabPosition);
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
        Log.d(MoviePagerAdapter.class.getSimpleName(), "I was called");
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

}
