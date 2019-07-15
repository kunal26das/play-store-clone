package com.emre1s.playstore.dagger;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class AppController extends Application {

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        appComponent= DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .build();
    }
}
