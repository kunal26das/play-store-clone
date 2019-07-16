package com.emre1s.playstore.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.TopChartsTabAdapter;
import com.emre1s.playstore.ui.main.PageViewModel;
import com.google.android.material.tabs.TabLayout;


public class TopChartsTabFragment extends Fragment {

    private PageViewModel pageViewModel;

    public TopChartsTabFragment() {
    }

    public static TopChartsTabFragment newInstance(int position) {
        TopChartsTabFragment topChartsTabFragment = new TopChartsTabFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        topChartsTabFragment.setArguments(bundle);
        return topChartsTabFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int position = 1;
        if (getArguments() != null) {
            position = getArguments().getInt("position");
            Log.d(TopChartsTabFragment.class.getSimpleName(), "TabList position at tabsfragment: " + position);
        }
        pageViewModel.getTabPosition().setValue(position);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.top_charts_tab_fragment, container, false);
        TabLayout topChartsTab = root.findViewById(R.id.top_charts_tab);
        ViewPager topChartsViewPager = root.findViewById(R.id.top_charts_viewpager);

        topChartsTab.setupWithViewPager(topChartsViewPager);
        topChartsTab.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));

        pageViewModel.getTabPosition().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer tabPosition) {
                TopChartsTabAdapter topChartsTabAdapter = new TopChartsTabAdapter(getContext(),
                        getChildFragmentManager(), tabPosition);
                topChartsViewPager.setAdapter(topChartsTabAdapter);
            }
        });


        return root;
    }
}