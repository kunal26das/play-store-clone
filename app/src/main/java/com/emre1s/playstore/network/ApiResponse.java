package com.emre1s.playstore.network;

import com.emre1s.playstore.model.TopChartsApp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiResponse {
    String BASE_URL="https://playstore-api.herokuapp.com/?collection=topselling_free";

    @GET(BASE_URL)
    Call<List<TopChartsApp>> getCollection();
}
