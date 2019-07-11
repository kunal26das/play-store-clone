package com.emre1s.playstore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.AllCategoriesAdapter;
import com.emre1s.playstore.adapters.TopCategoryAdapter;
import com.emre1s.playstore.ui.main.PageViewModel;

import java.util.Arrays;

public class AppCategoryFragment extends Fragment {

    public AppCategoryFragment() {

    }

    public static AppCategoryFragment newInstance() {
        AppCategoryFragment appCategoryFragment = new AppCategoryFragment();
        return appCategoryFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_categories, container, false);

        int[] categoryIcons = new int[]{R.drawable.camera,R.drawable.star,R.drawable.music, R.drawable.domain, R.drawable.access_point_network, R.drawable.brush, R.drawable.book_open, R.drawable.forum};
        RecyclerView topCategories = view.findViewById(R.id.rv_top_categories);

        TopCategoryAdapter topCategoryAdapter = new TopCategoryAdapter(getResources().getStringArray(R.array.top_categories), categoryIcons);
        topCategories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        topCategories.setAdapter(topCategoryAdapter);

        LinearSnapHelper pagerSnapHelper = new LinearSnapHelper();
        pagerSnapHelper.attachToRecyclerView(topCategories);

        PageViewModel pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        String[] allCategories = pageViewModel.getAllCategories();
        Arrays.sort(allCategories);

        RecyclerView allCategoriesRecyclerView = view.findViewById(R.id.rv_all_categories);
        allCategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        allCategoriesRecyclerView.setAdapter(new AllCategoriesAdapter(allCategories));

        return view;
    }
}
