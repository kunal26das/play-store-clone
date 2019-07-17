package com.emre1s.playstore.apps_installed_list;

import android.graphics.drawable.Drawable;

public class InstalledApp {

    private String mPackageName;
    private String mTitle;
    private String mVersion;
    private Drawable mIconUrl;

    public InstalledApp(String mPackageName, String appTitle, String appStatus, Drawable appIcon) {
        this.mTitle = appTitle;
        this.mVersion = appStatus;
        this.mIconUrl = appIcon;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public String getAppTitle() {
        return mTitle;
    }

    public String getVersion() {
        return mVersion;
    }

    public Drawable getAppIcon() {
        return mIconUrl;
    }
}
