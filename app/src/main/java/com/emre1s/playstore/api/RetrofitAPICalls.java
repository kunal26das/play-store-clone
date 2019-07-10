package com.emre1s.playstore.api;

import com.emre1s.playstore.models.AppByCategoryApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface RetrofitAPICalls {

    @GET(" ")
    Call<AppByCategoryApiResponse[]> getAppByCategoryResponse(
            @Query("category") String category
    );
}
