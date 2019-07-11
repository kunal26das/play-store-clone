package com.emre1s.playstore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.TopChartsTabAdapter;
import com.emre1s.playstore.ui.main.PageViewModel;
import com.google.android.material.tabs.TabLayout;


public class TopChartsTabFragment extends Fragment {

    public TopChartsTabFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.top_charts_tab_fragment,container,false);
        TabLayout topChartsTab= root.findViewById(R.id.top_charts_tab);

        ViewPager topChartsViewPager= root.findViewById(R.id.top_charts_viewpager);
        topChartsTab.setupWithViewPager(topChartsViewPager);
        topChartsTab.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));

        TopChartsTabAdapter topChartsTabAdapter=new TopChartsTabAdapter(getContext(), getChildFragmentManager());
        topChartsViewPager.setAdapter(topChartsTabAdapter);

        return root;
    }


}
