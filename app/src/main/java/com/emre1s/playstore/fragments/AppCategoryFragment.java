package com.emre1s.playstore.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.emre1s.playstore.listeners.OnCategoryChanged;
import com.emre1s.playstore.models.CategoryList;
import com.emre1s.playstore.ui.MoreAppsActivity;
import com.emre1s.playstore.ui.main.PageViewModel;

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

        PageViewModel pageViewModel = ViewModelProviders.of(this, null)
                .get(PageViewModel.class);
        RecyclerView topCategories = view.findViewById(R.id.rv_top_categories);

        List<CategoryList.Category> categoryList;
        if (position == CATEGORY_APPS) {
            categoryList = pageViewModel.getAppsTopCategoryList().getCategoryList();
        } else {
            categoryList = pageViewModel.getGamesTopCategoryList().getCategoryList();
        }
        TopCategoryAdapter topCategoryAdapter =
                new TopCategoryAdapter(getContext(), new OnCategoryChanged() {
                    @Override
                    public void changeCategory(CategoryList.Category category) {
                        Log.d(ForYouFragment.class.getSimpleName(), "Category received: "
                                + category.getName());
                        Intent intent = new Intent(getContext(), MoreAppsActivity.class);
                        intent.putExtra(MoreAppsActivity.CATEGORY_KEY, category);
                        startActivity(intent);
                    }
                });

        topCategoryAdapter.setCategoryList(categoryList);
        topCategories.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        topCategories.setAdapter(topCategoryAdapter);

        LinearSnapHelper pagerSnapHelper = new LinearSnapHelper();
        pagerSnapHelper.attachToRecyclerView(topCategories);


        AllCategoriesAdapter allCategoriesAdapter = new AllCategoriesAdapter(getContext(), new OnCategoryChanged() {
            @Override
            public void changeCategory(CategoryList.Category category) {
                Log.d(ForYouFragment.class.getSimpleName(), "Category received: " + category.getName());
                Intent intent = new Intent(getContext(), MoreAppsActivity.class);
                intent.putExtra(MoreAppsActivity.CATEGORY_KEY, category);
                startActivity(intent);
            }
        });

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
