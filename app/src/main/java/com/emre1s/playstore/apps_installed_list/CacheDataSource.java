package com.emre1s.playstore.apps_installed_list;

import java.util.List;

import io.reactivex.Observable;

public class CacheDataSource {
    private static CacheDataSource cacheDataSource;
    public String source;
    private List<InstalledApp> installedApps;

    public static CacheDataSource getInstance() {
        if (cacheDataSource == null) {
            cacheDataSource = new CacheDataSource();
        }
        return cacheDataSource;
    }

    public List<InstalledApp> getInstalledApps() {
        return installedApps;
    }

    public void setInstalledApps(List<InstalledApp> installedApps) {
        this.installedApps = installedApps;
        source = "cache";
    }

    public Observable<List<InstalledApp>> getCacheObserver() {
        return Observable.create(emitter -> {
            if (emitter != null) {
                emitter.onNext(installedApps);
            }
            emitter.onComplete();
        });
    }

}
