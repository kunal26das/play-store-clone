package com.emre1s.playstore.adapters;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.emre1s.playstore.R;
import com.emre1s.playstore.fragments.TopChartsFragment;

public class TopChartsTabAdapter extends FragmentPagerAdapter {
    private static int ITEM_NUM = 4;
    String category;
    Context mContext;
    Integer tabPosition;
    @StringRes
    private static final int[] topChartsTabItemNames =new int[] {R.string.topFree,R.string.topGrossing,R.string.trending,R.string.topPaid};

    public TopChartsTabAdapter(Context context, FragmentManager fragmentManager, Integer tabPosition) {
        super(fragmentManager);
        mContext=context;
        Log.d(TopChartsTabAdapter.class.getSimpleName(), "tab position at adapter: " + tabPosition);
        this.tabPosition = tabPosition;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                category = "topselling_free";
                return TopChartsFragment.getInstance(category, tabPosition);
            }
            case 1: {
                category = "topgrossing";
                return TopChartsFragment.getInstance(category, tabPosition);
            }
            case 2: {
                category = "movers_shakers";
                return TopChartsFragment.getInstance(category, tabPosition);
            }
            case 3: {
                category="topselling_paid";
                return TopChartsFragment.getInstance(category, tabPosition);
            }
            default:{
                category="topselling_free";
                return TopChartsFragment.getInstance(category, tabPosition);
            }
        }

    }

    @Override
    public int getCount() {
        return ITEM_NUM;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(topChartsTabItemNames[position]);
    }
}
