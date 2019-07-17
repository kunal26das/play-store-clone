package com.emre1s.playstore.apps_installed_list;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "installed_apps")
public class InstalledApp {

    @PrimaryKey
    @NonNull
    private String mPackageName;

    private String mTitle;

    private String mVersion;

    @Ignore
    private Drawable mIconUrl;

    public InstalledApp() {

    }

    public InstalledApp(String mPackageName, String appTitle, String appStatus, Drawable appIcon) {
        this.mPackageName = mPackageName;
        this.mTitle = appTitle;
        this.mVersion = appStatus;
        this.mIconUrl = appIcon;
    }

    public String getMPackageName() {
        return mPackageName;
    }

    public void setMPackageName(String mPackageName) {
        this.mPackageName = mPackageName;
    }

    public String getMTitle() {
        return mTitle;
    }

    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getMVersion() {
        return mVersion;
    }

    public void setMVersion(String mVersion) {
        this.mVersion = mVersion;
    }

    public Drawable getmIconUrl() {
        return mIconUrl;
    }

    public void setMIconUrl(Drawable mIconUrl) {
        this.mIconUrl = mIconUrl;
    }

}
