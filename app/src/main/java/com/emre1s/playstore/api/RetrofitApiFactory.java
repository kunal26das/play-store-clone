package com.emre1s.playstore.api;

import android.util.Log;

import com.emre1s.playstore.models.App;
import com.emre1s.playstore.models.CategoryList;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiFactory {

    private static RetrofitApiFactory retrofitApiFactory;
    private final RetrofitAPICalls retrofitAPICalls;

    private  static CategoryList gameCategories;
    private static CategoryList familyCategories;
    private static CategoryList appCategories;

    public RetrofitApiFactory(RetrofitAPICalls retrofitAPICalls) {
        this.retrofitAPICalls = retrofitAPICalls;
    }

    public static RetrofitApiFactory getInstance() {
        if (retrofitApiFactory == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://13.234.73.3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            retrofitApiFactory = new RetrofitApiFactory(retrofit.create(RetrofitAPICalls.class));
        }
        return retrofitApiFactory;
    }

    public void appsByCategoryApiCall(final ApiResponseCallback apiResponseCallback, String category) {
        retrofitAPICalls.getAppsByCategoryResponse(category).enqueue(new Callback<App[]>() {
            @Override
            public void onResponse(Call<App[]> call, Response<App[]> response) {

                if (response.isSuccessful()) {
                    Log.d("Emre1s", "Successful response");
                    App[] appByCategoryApiResponse = response.body();
                    if (appByCategoryApiResponse != null) {
                        apiResponseCallback.onSuccess(appByCategoryApiResponse);
                    } else {
                        apiResponseCallback.onFailure();
                        Log.d("Emre1s", "it's empty");
                    }
                } else {

                    try {
                        Log.d("Emre1s", "Response not successful"
                                + response.message() + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<App[]> call, Throwable t) {
                Log.d("Emre1s", "Entire failure. Oops.");
            }
        });
    }

    public void appsByCollectionApiCall(final ApiResponseCallback apiResponseCallback,
                                        String collection) {
        retrofitAPICalls.getAppsByCollectionResponse(collection).enqueue(new Callback<App[]>() {
            @Override
            public void onResponse(Call<App[]> call, Response<App[]> response) {

                if (response.isSuccessful()) {
                    Log.d("Emre1s", "Successful collection response");
                    App[] appsByCollectionApiResponse = response.body();
                    if (appsByCollectionApiResponse != null) {
                        apiResponseCallback.onSuccess(appsByCollectionApiResponse);
                    } else {
                        apiResponseCallback.onFailure();
                        Log.d("Emre1s", "Collection response is empty");
                    }
                } else {
                    Log.d("Emre1s", "Response not successful collection API");
                }
            }

            @Override
            public void onFailure(Call<App[]> call, Throwable t) {
                Log.d("Emre1s", "Collection response entire failure. Damn");
            }
        });
    }

    public void similarAppsApiCall(final ApiResponseCallback apiResponseCallback,
                                        String packageName) {
        retrofitAPICalls.getSimilarApps(packageName).enqueue(new Callback<App[]>() {
            @Override
            public void onResponse(Call<App[]> call, Response<App[]> response) {

                if (response.isSuccessful()) {
                    Log.d("Emre1s", "Successful similar apps response");
                    App[] appsByCollectionApiResponse = response.body();
                    if (appsByCollectionApiResponse != null) {
                        apiResponseCallback.onSuccess(appsByCollectionApiResponse);
                    } else {
                        apiResponseCallback.onFailure();
                        Log.d("Emre1s", "similar apps response is empty");
                    }
                } else {
                    Log.d("Emre1s", "Response not successful similar apps API");
                }
            }

            @Override
            public void onFailure(Call<App[]> call, Throwable t) {
                Log.d("Emre1s", "similar apps response entire failure. Damn");
            }
        });
    }

    public static CategoryList getGameCategories() {
        return gameCategories;
    }

    public static void setGameCategories(CategoryList gameCategories) {
        RetrofitApiFactory.gameCategories = gameCategories;
    }

    public static CategoryList getFamilyCategories() {
        return familyCategories;
    }

    public static void setFamilyCategories(CategoryList familyCategories) {
        RetrofitApiFactory.familyCategories = familyCategories;
    }

    public static CategoryList getAppCategories() {
        return appCategories;
    }

    public static void setAppCategories(CategoryList appCategories) {
        RetrofitApiFactory.appCategories = appCategories;
    }

    public void appsByCollectionCategoryApiCall(String collection, String category,
                                                final ApiResponseCallback apiResponseCallback){
        retrofitAPICalls.getAppsByCollectionCategory(collection,category).enqueue(new Callback<App[]>() {
            @Override
            public void onResponse(Call<App[]> call, Response<App[]> response) {

                if (response.isSuccessful()) {
                    Log.d("Ruchika", "Successful collection response");
                    App[] appsByCollectionCategoryApiResponse = response.body();
                    if (appsByCollectionCategoryApiResponse != null) {
                        apiResponseCallback.onSuccess(appsByCollectionCategoryApiResponse);
                    } else {
                        apiResponseCallback.onFailure();
                        Log.d("Ruchika", "Collection response is empty");
                    }
                } else {
                    Log.d("Ruchika", "Response not successful collection API");
                }
            }

            @Override
            public void onFailure(Call<App[]> call, Throwable t) {
                Log.d("Ruchika", "Collection response entire failure");
            }
        });

    }


}
