package com.emre1s.playstore.api;

import com.emre1s.playstore.models.App;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface RetrofitAPICalls {

    @GET(" ")
    Single<List<App>> getAppsByCategoryResponse(
            @Query("category") String category);

    @GET(" ")
    Single<List<App>> getAppsByCollectionResponse(
            @Query("collection") String collection);

    @GET("apps/similar/")
    Single<List<App>> getSimilarApps(
            @Query("id") String packageName);

//    @GET("suggestions/")
//    Call<>

    @GET(" ")
    Single<List<App>> getAppsByCollectionCategory(
            @Query("collection") String collection,
            @Query("category") String category
    );

}