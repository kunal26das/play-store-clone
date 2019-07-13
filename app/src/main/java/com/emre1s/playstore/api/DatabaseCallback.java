package com.emre1s.playstore.api;

import com.emre1s.playstore.app_details.AppDetails;

public interface DatabaseCallback {

    void onSuccess(AppDetails appDetails);

    void onFailure();

}
