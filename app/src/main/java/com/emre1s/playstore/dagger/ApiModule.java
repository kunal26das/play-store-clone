package com.emre1s.playstore.dagger;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.emre1s.playstore.api.RetrofitAPICalls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        long cacheSize = (5 * 1024 * 1024);
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Interceptor provideOfflineInterceptor(Application application) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!isNetworkActive(application)) {
                    int maxStale = 60 * 60 * 24 * 30;
                    request = request.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }

    @Provides
    @Singleton
    Interceptor provideOnlineInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                int maxAge = 60;
                return response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build();
            }
        };
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, Application application) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(provideOfflineInterceptor(application))
                .addInterceptor(provideOfflineInterceptor(application))
                .cache(cache)
                .build();

        return okHttpClient;
    }

    @Provides
    @Singleton
    boolean isNetworkActive(Application application) {
        boolean isConnected = false;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null  && networkInfo.isConnected()) {
            isConnected = true;
        }
        return isConnected;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            Log.d(ApiModule.class.getSimpleName(), "FUCK IT  WAS NULL?!");
        }
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://13.234.73.3/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    RetrofitAPICalls provideMovieApiService(Retrofit retrofit) {
        return retrofit.create(RetrofitAPICalls.class);
    }

}