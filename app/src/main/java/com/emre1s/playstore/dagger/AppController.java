package com.emre1s.playstore.dagger;

import android.app.Activity;
import android.app.Application;

import com.emre1s.playstore.api.RetrofitApiFactory;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

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
