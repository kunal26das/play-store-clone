package com.emre1s.playstore.dagger;

import android.app.Application;

public class AppController extends Application {

    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent= DaggerAppComponent.builder()
                .application(this)
                .apiModule(new ApiModule())
                .build();
    }
}
