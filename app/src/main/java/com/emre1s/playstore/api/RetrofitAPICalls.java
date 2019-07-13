package com.emre1s.playstore.api;

import com.emre1s.playstore.models.App;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface RetrofitAPICalls {

    @GET(" ")
    Call<App[]> getAppsByCategoryResponse(
            @Query("category") String category);

    @GET(" ")
    Call<App[]> getAppsByCollectionResponse(
            @Query("collection") String collection);

    @GET("apps/similar/")
    Call<App[]> getSimilarApps(
            @Query("id") String packageName);

//    @GET("suggestions/")
//    Call<>

    @GET(" ")
    Call<App[]> getAppsByCollectionCategory(
            @Query("collection") String collection,
            @Query("category") String category
    );

}