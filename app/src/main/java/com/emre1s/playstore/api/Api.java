package com.emre1s.playstore.api;

public interface Api {

    String QUERY_APP_PACKAGE_ID = "id";

    String ADDRESS_APPS_DETAILS = "apps/details";

    /*@GET(ADDRESS_APPS_DETAILS)
    Call<Pojo> getAppDetails(@Query(QUERY_APP_PACKAGE_ID) String appPackageId);*/
}
