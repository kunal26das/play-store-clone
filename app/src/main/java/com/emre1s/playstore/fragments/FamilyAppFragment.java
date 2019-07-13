package com.emre1s.playstore.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.FamilyTopChartsAdapter;
import com.emre1s.playstore.adapters.ForYouAdapter;
import com.emre1s.playstore.adapters.TopCategoryAdapter;
import com.emre1s.playstore.ui.main.PageViewModel;
import com.google.android.material.tabs.TabLayout;

public class FamilyAppFragment extends Fragment {
    private PageViewModel pageViewModel;
    public FamilyAppFragment() {

    }

    public static FamilyAppFragment newInstance() {
        FamilyAppFragment familyAppFragment = new FamilyAppFragment();
        return familyAppFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_family_app, container, false);

        int[] categoryIcons = new int[]{R.drawable.camera, R.drawable.star, R.drawable.music, R.drawable.domain, R.drawable.access_point_network, R.drawable.brush};
        RecyclerView familyCategories = view.findViewById(R.id.family_categories_rv);

        TopCategoryAdapter topCategoryAdapter = new TopCategoryAdapter(getResources().getStringArray(R.array.family_categories), categoryIcons);
        familyCategories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        familyCategories.setAdapter(topCategoryAdapter);

        LinearSnapHelper pagerSnapHelper = new LinearSnapHelper();
        pagerSnapHelper.attachToRecyclerView(familyCategories);

        TabLayout topChartsFamilyTab = view.findViewById(R.id.top_charts_family_tab);
        ViewPager topChartsFamilyViewPager = view.findViewById(R.id.top_charts_family_viewpager);

        topChartsFamilyTab.setupWithViewPager(topChartsFamilyViewPager);
        topChartsFamilyTab.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));

        FamilyTopChartsAdapter familyTopChartsAdapter = new FamilyTopChartsAdapter(getContext(), getChildFragmentManager());
        topChartsFamilyViewPager.setAdapter(familyTopChartsAdapter);

        Button seeMore = view.findViewById(R.id.see_more_button);
        seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView familyRecycler = view.findViewById(R.id.family_app_card_rv);
        final ForYouAdapter familyAdapter = new ForYouAdapter(getContext(), pageViewModel);

        familyRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        familyRecycler.setAdapter(familyAdapter);
        Log.d("Ruchika-family",pageViewModel.getFamilyCategoryList().getCategoryList().size()+"");
        familyAdapter.setCategoryNames(pageViewModel.getFamilyCategoryList());

        return view;
    }
}