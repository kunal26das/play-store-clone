package com.emre1s.playstore.listeners;

import com.emre1s.playstore.models.AppByCategoryApiResponse;

public interface OnAppClickedListener {
    void showAppDetail(AppByCategoryApiResponse appByCategoryApiResponse);
}
