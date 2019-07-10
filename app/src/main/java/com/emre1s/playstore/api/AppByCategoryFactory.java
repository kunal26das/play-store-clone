package com.emre1s.playstore.api;

import android.util.Log;

import com.emre1s.playstore.models.AppByCategoryApiResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppByCategoryFactory {

    private static AppByCategoryFactory appByCategoryFactory;
    private final RetrofitAPICalls retrofitAPICalls;

    public AppByCategoryFactory(RetrofitAPICalls retrofitAPICalls) {
        this.retrofitAPICalls = retrofitAPICalls;
    }

    public static AppByCategoryFactory getInstance() {
        if (appByCategoryFactory == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://13.234.73.3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            appByCategoryFactory = new AppByCategoryFactory(retrofit.create(RetrofitAPICalls.class));
        }
        return appByCategoryFactory;
    }

    public void apiCall(final AppByCategoryCallback appByCategoryCallback, String category) {
        retrofitAPICalls.getAppByCategoryResponse(category).enqueue(new Callback<AppByCategoryApiResponse[]>() {
            @Override
            public void onResponse(Call<AppByCategoryApiResponse[]> call, Response<AppByCategoryApiResponse[]> response) {

                if (response.isSuccessful()) {
                    Log.d("Emre1s", "Successful response");
                    AppByCategoryApiResponse[] appByCategoryApiResponses = response.body();
                    if (appByCategoryApiResponses != null) {
                        appByCategoryCallback.onSuccess(appByCategoryApiResponses);
                    } else {
                        appByCategoryCallback.onFailure();
                        Log.d("Emre1s", "it's empty");
                    }
                } else {

                    try {
                        Log.d("Emre1s", "Response not successful" + response.message() + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<AppByCategoryApiResponse[]> call, Throwable t) {
                Log.d("Emre1s", "Entire failure. Oops.");
            }
        });
    }
}
