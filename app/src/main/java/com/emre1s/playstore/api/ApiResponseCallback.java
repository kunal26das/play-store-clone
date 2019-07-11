package com.emre1s.playstore.api;

import com.emre1s.playstore.models.App;

public interface ApiResponseCallback {
    void onSuccess(App[] popularApp);

    void onFailure();
}
