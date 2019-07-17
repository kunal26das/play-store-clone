package com.emre1s.playstore.room;

import android.app.Application;
import android.util.Log;

import com.emre1s.playstore.apps_installed_list.CacheDataSource;
import com.emre1s.playstore.apps_installed_list.InstalledApp;

import java.util.List;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AppsRepository {
    private final InstalledAppsDao installedAppsDao;

    public AppsRepository(Application application) {
        InstalledAppsDatabase installedAppsDatabase = InstalledAppsDatabase.getDatabase(application);
        installedAppsDao = installedAppsDatabase.installedAppsDao();
    }

    public Observable<List<InstalledApp>> getInstalledAppsObservable() {
        Observable<List<InstalledApp>> observable = installedAppsDao.getInstalledApps();
        observable.subscribe(new Observer<List<InstalledApp>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<InstalledApp> installedApps) {
                CacheDataSource cacheDataSource = CacheDataSource.getInstance();
                cacheDataSource.setInstalledApps(installedApps);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(AppsRepository.class.getSimpleName(), "AppsRepository error" + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Log.d(AppsRepository.class.getSimpleName(), "AppsRepository Complete");
            }
        }); //cache storage
        return observable;
    }

    public void insertInstalledApp(List<InstalledApp> installedApp) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                for (InstalledApp app : installedApp) {
                    installedAppsDao.insert(app);
                }
            }
        });
    }
}
