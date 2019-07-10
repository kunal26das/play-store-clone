package com.emre1s.playstore.api;

import com.emre1s.playstore.models.AppByCategoryApiResponse;

import java.util.List;

public interface AppByCategoryCallback {
    void onSuccess(AppByCategoryApiResponse[] popularApp);

    void onFailure();
}
