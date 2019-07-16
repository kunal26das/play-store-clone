package com.emre1s.playstore.dagger;

import android.app.Application;

import com.emre1s.playstore.api.RetrofitApiFactory;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {
        ApiModule.class})
@Singleton
public interface AppComponent {

    @Component.Builder
    interface Builder {

//        @BindsInstance
//        Builder application(Application application);

        AppComponent build();


        @BindsInstance
        Builder apiModule(ApiModule apiModule);

        @BindsInstance
        Builder application(Application application);

    }


    /*
     * This is our custom Application class
     * */
    void inject(RetrofitApiFactory retrofitApiFactory);
}
