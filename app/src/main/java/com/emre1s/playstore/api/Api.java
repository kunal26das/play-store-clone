package com.emre1s.playstore.api;

import com.emre1s.playstore.apps.AppDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String QUERY_APP_PACKAGE_ID = "id";

    String ADDRESS_APPS_DETAILS = "apps/details";

    @GET(ADDRESS_APPS_DETAILS)
    Call<AppDetails> getAppDetails(@Query(QUERY_APP_PACKAGE_ID) String appPackageId);
}
