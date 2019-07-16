package com.emre1s.playstore.apps_installed_list;

public class InstalledApp {

    private String appTitle;
    private String appStatus;
    private String appIcon;

    public InstalledApp(String appTitle, String appStatus, String appIcon) {
        this.appTitle = appTitle;
        this.appStatus = appStatus;
        this.appIcon = appIcon;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public String getAppIcon() {
        return appIcon;
    }
}
