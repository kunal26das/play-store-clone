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


public class TopChartsFragment extends Fragment {


    private PageViewModel pageViewModel;

    public TopChartsFragment() {

    }

    public static Fragment getInstance(String category) {
        TopChartsFragment topChartsFragment = new TopChartsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        topChartsFragment.setArguments(bundle);
        return topChartsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        pageViewModel = ViewModelProviders.of(this,null).get(PageViewModel.class);
        super.onCreate(savedInstanceState);
        String category = "topselling_free";
        if (getArguments() != null) {
            category = getArguments().getString("category");
        }
        pageViewModel.getAppTopChartsCategory().setValue(category);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_top_charts, container, false);
        RecyclerView appList = root.findViewById(R.id.app_list);
        final TopChartsAdapter topChartsAdapter = new TopChartsAdapter();
        appList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        appList.setAdapter(topChartsAdapter);

        pageViewModel.getAppTopChartsCategory().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String category) {
                pageViewModel.makeCollectionApiCall(category, new ApiResponseCallback() {
                    @Override
                    public void onSuccess(List<App> popularApp) {
                        Log.d("Success", popularApp.size() + "");
                        topChartsAdapter.setmList(popularApp);
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