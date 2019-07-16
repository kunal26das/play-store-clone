package com.emre1s.playstore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.AllCategoriesAdapter;
import com.emre1s.playstore.adapters.TopCategoryAdapter;
import com.emre1s.playstore.models.CategoryList;
import com.emre1s.playstore.ui.main.PageViewModel;

import java.util.ArrayList;
import java.util.List;

public class AppCategoryFragment extends Fragment {

    public static final int CATEGORY_APPS = 1;
    private int position;

    private PageViewModel pageViewModel;

    public AppCategoryFragment() {

    }

    public static AppCategoryFragment newInstance(int position) {
        AppCategoryFragment appCategoryFragment = new AppCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        appCategoryFragment.setArguments(bundle);
        return appCategoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this,null).get(PageViewModel.class);
        position = CATEGORY_APPS;
        if (getArguments() != null) {
            position = getArguments().getInt("position");
        }
        pageViewModel.getTabPosition().setValue(position);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_categories, container, false);

        PageViewModel pageViewModel = ViewModelProviders.of(this,null)
                .get(PageViewModel.class);

        int[] categoryIcons = new int[]{R.drawable.camera, R.drawable.star, R.drawable.music,
                R.drawable.domain, R.drawable.access_point_network, R.drawable.brush,
                R.drawable.book_open, R.drawable.forum};
        RecyclerView topCategories = view.findViewById(R.id.rv_top_categories);

        List<CategoryList.Category> categoryList;
        if (position == CATEGORY_APPS) {
            categoryList = pageViewModel.getAppsTopCategoryList().getCategoryList();
        } else {
            categoryList = pageViewModel.getGamesTopCategoryList().getCategoryList();
        }
        TopCategoryAdapter topCategoryAdapter =
                new TopCategoryAdapter(getContext());
        topCategoryAdapter.setCategoryList(categoryList);

        topCategories.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,false));
        topCategories.setAdapter(topCategoryAdapter);

        LinearSnapHelper pagerSnapHelper = new LinearSnapHelper();
        pagerSnapHelper.attachToRecyclerView(topCategories);




        AllCategoriesAdapter allCategoriesAdapter = new AllCategoriesAdapter(getContext());
        RecyclerView allCategoriesRecyclerView = view.findViewById(R.id.rv_all_categories);
        allCategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        allCategoriesRecyclerView.setAdapter(allCategoriesAdapter);

        if (position == CATEGORY_APPS) {
            allCategoriesAdapter.setCategories(pageViewModel.getAppCategoryList().getCategoryList());
        } else {
            allCategoriesAdapter.setCategories(pageViewModel.getGamesCategoryList().getCategoryList());
        }

        return view;
    }
}
