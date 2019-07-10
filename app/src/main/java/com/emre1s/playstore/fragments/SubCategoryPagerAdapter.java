package com.emre1s.playstore.fragments;

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
        switch (position) {
            case 0: {
                return AppCategoryFragment.newInstance();
            }
            case 1: {
                return AppCategoryFragment.newInstance();
            }
            case 2: {
                return AppCategoryFragment.newInstance();
            }
            default: {
                return AppCategoryFragment.newInstance();
            }
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page" + position;
    }
}
