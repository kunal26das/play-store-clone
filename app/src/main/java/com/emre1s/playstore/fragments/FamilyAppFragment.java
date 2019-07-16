package com.emre1s.playstore.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.FamilyTopChartsAdapter;
import com.emre1s.playstore.adapters.ForYouAdapter;
import com.emre1s.playstore.adapters.TopCategoryAdapter;
import com.emre1s.playstore.listeners.OnCategoryChanged;
import com.emre1s.playstore.listeners.OnShowAllClickedListener;
import com.emre1s.playstore.models.CategoryList;
import com.emre1s.playstore.ui.MoreAppsActivity;
import com.emre1s.playstore.ui.main.PageViewModel;
import com.google.android.material.tabs.TabLayout;

public class FamilyAppFragment extends Fragment {

    OnShowAllClickedListener showAllClickedListener;
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
        pageViewModel = ViewModelProviders.of(this,null).get(PageViewModel.class);
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

        TabLayout topChartsFamilyTab = view.findViewById(R.id.top_charts_family_tab);
        ViewPager topChartsFamilyViewPager = view.findViewById(R.id.top_charts_family_viewpager);

        topChartsFamilyTab.setupWithViewPager(topChartsFamilyViewPager);
        topChartsFamilyTab.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));

        FamilyTopChartsAdapter familyTopChartsAdapter = new FamilyTopChartsAdapter(getContext(), getChildFragmentManager(), 3);
        topChartsFamilyViewPager.setAdapter(familyTopChartsAdapter);

        Button seeMore = view.findViewById(R.id.see_more_button);
        seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllClickedListener.onShowAllClicked();
            }
        });

        RecyclerView familyRecycler = view.findViewById(R.id.family_app_card_rv);
        familyRecycler.setHasFixedSize(true);
        familyRecycler.setItemViewCacheSize(20);
        final ForYouAdapter familyAdapter = new ForYouAdapter(getContext(), pageViewModel, new OnCategoryChanged() {
            @Override
            public void changeCategory(CategoryList.Category category) {
                Log.d(ForYouFragment.class.getSimpleName(), "Category received: " + category.getName());
                Intent intent = new Intent(getContext(), MoreAppsActivity.class);
                intent.putExtra(MoreAppsActivity.CATEGORY_KEY, category);
                startActivity(intent);
            }
        }, null);

        familyRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        familyRecycler.setAdapter(familyAdapter);
        //Log.d("Ruchika-family", pageViewModel.getFamilyCategoryList().getCategoryList().size() + "");
        familyAdapter.setCategoryNames(pageViewModel.getFamilyCategoryList());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            showAllClickedListener = (OnShowAllClickedListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

    }
}