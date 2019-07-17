package com.emre1s.playstore.apps_installed_list;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class CacheDataSource {
    private List<InstalledApp> installedApps;
    private static CacheDataSource cacheDataSource;
    public String source;

    public static CacheDataSource getInstance() {
        if (cacheDataSource == null) {
            cacheDataSource = new CacheDataSource();
        }
        return cacheDataSource;
    }

    public List<InstalledApp> getInstalledApps() {
        return installedApps;
    }

    public Observable<List<InstalledApp>> getCacheObserver() {
        return Observable.create(new ObservableOnSubscribe<List<InstalledApp>>() {
            @Override
            public void subscribe(ObservableEmitter<List<InstalledApp>> emitter) throws Exception {
                if (emitter != null) {
                    emitter.onNext(installedApps);
                }
                emitter.onComplete();
            }
        });
    }

    public void setInstalledApps(List<InstalledApp> installedApps) {
        this.installedApps = installedApps;
        source = "cache";
    }

}
