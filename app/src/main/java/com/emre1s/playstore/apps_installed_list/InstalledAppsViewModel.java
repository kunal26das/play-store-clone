package com.emre1s.playstore.apps_installed_list;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.emre1s.playstore.room.AppsRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class InstalledAppsViewModel extends AndroidViewModel {

    private InstalledAppsRepository mRepository;
    private AppsRepository appsRepository;

    public InstalledAppsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new InstalledAppsRepository(application);
        this.appsRepository = new AppsRepository(application);
    }

    public Observable<List<InstalledApp>> getInstalledApps() {
        Observable<List<InstalledApp>> memory = appsRepository.getInstalledAppsObservable();
        Observable<List<InstalledApp>> cache = CacheDataSource.getInstance().getCacheObserver();
        Observable<List<InstalledApp>> localCall = mRepository.getInstalledApps();

        return Observable.concat(memory, cache, localCall);
    }

    static class InstalledAppsRepository {

        MutableLiveData<List<InstalledApp>> mInstalledApps;
        PackageManager mPackageManager;
        static AppsRepository appsRepository;

        public InstalledAppsRepository(Application application) {
            mPackageManager = application.getPackageManager();
            mInstalledApps = new MutableLiveData<>();
            appsRepository = new AppsRepository(application);
        }

        private Observable<List<InstalledApp>> getInstalledApps() {
            InstalledAppsAsyncTask installedAppsAsyncTask = new InstalledAppsAsyncTask(mPackageManager);
           // MutableLiveData<List<InstalledApp>> installedApps = new MutableLiveData<>();
            List<InstalledApp> installedApps = new ArrayList<>();
            try {
                installedApps = installedAppsAsyncTask.execute().get();  //NETWORK CALL
            } catch (Exception exception) {
            }
            List<InstalledApp> finalInstalledApps = installedApps;
            return Observable.create(new ObservableOnSubscribe<List<InstalledApp>>() {
                @Override
                public void subscribe(ObservableEmitter<List<InstalledApp>> emitter) throws Exception {
                    if (emitter != null) {
                        emitter.onNext(finalInstalledApps);
                    }
                    emitter.onComplete();
                }
            });
        }

        static class InstalledAppsAsyncTask extends AsyncTask<Void, Void, List<InstalledApp>> {

            private PackageManager mPackageManager;

            public InstalledAppsAsyncTask(PackageManager packageManager) {
                mPackageManager = packageManager;
            }

            @Override
            protected List<InstalledApp> doInBackground(final Void... params) {
                List<InstalledApp> apps = new ArrayList<>();
                List<ApplicationInfo> installedApps = mPackageManager.getInstalledApplications(0);
                for (ApplicationInfo app : installedApps) {
                    try {
                        if (!isSystemPackage(app)) {
                            apps.add(new InstalledApp(
                                    app.packageName,
                                    mPackageManager.getApplicationLabel(app).toString(),
                                    mPackageManager.getPackageInfo(app.packageName, 0).versionName,
                                    mPackageManager.getApplicationIcon(app)));
                        }
                    } catch (Exception exception) {

                    }
                }
                appsRepository.insertInstalledApp(apps);  //MEMORY CALL
                CacheDataSource cacheDataSource = CacheDataSource.getInstance();
                cacheDataSource.setInstalledApps(apps); // CACHE CALL
                return apps;
            }

            private boolean isSystemPackage(ApplicationInfo pkgInfo) {
                return (pkgInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
            }
        }
    }
}
