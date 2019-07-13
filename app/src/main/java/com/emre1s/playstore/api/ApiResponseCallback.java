package com.emre1s.playstore.api;

import com.emre1s.playstore.models.App;

import java.util.List;

public interface ApiResponseCallback {
    void onSuccess(List<App> popularApp);

    void onFailure();
}
