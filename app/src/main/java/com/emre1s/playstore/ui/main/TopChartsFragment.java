package com.emre1s.playstore.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.emre1s.playstore.R;
import com.emre1s.playstore.adapters.TopChartsAdapter;
import com.emre1s.playstore.model.TopChartsApp;
import com.emre1s.playstore.network.ApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TopChartsFragment extends Fragment {
    public TopChartsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_top_charts, container, false);
        getResponseCollection(root);
        return root;
    }

    private void getResponseCollection(final View rootView) {
        final RecyclerView appList = rootView.findViewById(R.id.app_list);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiResponse.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiResponse api = retrofit.create(ApiResponse.class);

        @NonNull
        Call<List<TopChartsApp>> call = api.getCollection();

        call.enqueue(new Callback<List<TopChartsApp>>() {
            @Override
            public void onResponse(@NonNull Call<List<TopChartsApp>> call, @NonNull Response<List<TopChartsApp>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("onSuccess", response.body().toString());

                        List<TopChartsApp> topAppList = response.body();
                        Log.d("TopCharts", topAppList.size()+"");
                        appList.setLayoutManager(new LinearLayoutManager(getContext()));

                        TopChartsAdapter topChartsAdapter = new TopChartsAdapter(topAppList);
                        appList.setAdapter(topChartsAdapter);

                    } else {
                        Log.d("onEmptyR" +
                                "esponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<TopChartsApp>> call, @NonNull Throwable t) {
                Log.d("OnFailure", t.getLocalizedMessage() + " " + t.getMessage());
                Log.d("OnFailure", "failure occurred");
            }
        });
    }
}