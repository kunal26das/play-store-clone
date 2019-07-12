package com.emre1s.playstore.fragments;

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
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.TopChartsAdapter;
import com.emre1s.playstore.api.ApiResponseCallback;
import com.emre1s.playstore.models.App;
import com.emre1s.playstore.ui.main.PageViewModel;

import java.util.Arrays;

public class FamilyTopChartsFragment extends Fragment {
    private String collection;
    public FamilyTopChartsFragment(String collection){
        this.collection=collection;
    }

    public FamilyTopChartsFragment(){
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
        PageViewModel pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);

        pageViewModel.makeCategoryCollectionApiCall(collection, "FAMILY", new ApiResponseCallback() {
            @Override
            public void onSuccess(App[] popularApp) {
                Log.d("Success",popularApp.length+"");
                topChartsAdapter.setmList(Arrays.asList(popularApp).subList(0,3));
            }

            @Override
            public void onFailure() {
                Log.d("onEmptyResponse", "Returned empty response");
            }
        });
        return root;
    }
}
