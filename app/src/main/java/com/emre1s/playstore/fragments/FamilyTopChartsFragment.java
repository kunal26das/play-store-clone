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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.TopChartsAdapter;
import com.emre1s.playstore.api.ApiResponseCallback;
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.ui.main.PageViewModel;

import java.util.List;

public class FamilyTopChartsFragment extends Fragment {


    private PageViewModel pageViewModel;
    private int limit;

    public FamilyTopChartsFragment() {
    }

    public static Fragment getInstance(String category, int limit) {
        FamilyTopChartsFragment topChartsFragment = new FamilyTopChartsFragment();
        Bundle bundle = new Bundle();
        topChartsFragment.limit = limit;
        bundle.putString("category", category);
        topChartsFragment.setArguments(bundle);
        return topChartsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this,null).get(PageViewModel.class);
        String category = "topselling_free";
        if (getArguments() != null) {
            category = getArguments().getString("category");
        }
        pageViewModel.getFamilyTopChartsCategory().setValue(category);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_top_charts, container, false);
        RecyclerView appList = root.findViewById(R.id.app_list);
        final TopChartsAdapter topChartsAdapter = new TopChartsAdapter();
        appList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        appList.setAdapter(topChartsAdapter);
        appList.setHasFixedSize(true);
        PageViewModel pageViewModel = ViewModelProviders.of(this,null).get(PageViewModel.class);

        pageViewModel.getFamilyTopChartsCategory().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String collection) {

                pageViewModel.makeCategoryCollectionApiCall(collection, "FAMILY", new ApiResponseCallback() {
                    @Override
                    public void onSuccess(List<App> popularApp) {
                        Log.d("Success", popularApp.size() + "");
                        if (limit != 3) {
                            topChartsAdapter.setmList(popularApp);
                        } else {
                            topChartsAdapter.setmList(popularApp.subList(0, 3));
                        }
                    }

                    @Override
                    public void onFailure() {
                        Log.d("onEmptyResponse", "Returned empty response");
                    }
                });
            }
        });

        return root;
    }
}