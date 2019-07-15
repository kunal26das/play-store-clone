package com.emre1s.playstore.dagger;

import com.emre1s.playstore.api.RetrofitAPICalls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://13.234.73.3/")
                .build();
    }

    @Provides
    @Singleton
    RetrofitAPICalls provideMovieApiService(Retrofit retrofit) {
        return retrofit.create(RetrofitAPICalls.class);
    }
}