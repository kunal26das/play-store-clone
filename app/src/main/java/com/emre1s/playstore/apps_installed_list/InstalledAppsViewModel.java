package com.emre1s.playstore.apps_installed_list;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class InstalledAppsViewModel extends AndroidViewModel {

    private InstalledAppsRepository mRepository;

    public InstalledAppsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new InstalledAppsRepository(application);
    }

    public MutableLiveData<List<String>> getInstalledApps() {
        return mRepository.getInstalledApps();
    }

    static class InstalledAppsRepository {

        MutableLiveData<List<InstalledApp>> mInstalledApps;
        PackageManager mPackageManager;

        public InstalledAppsRepository(Application application) {
            mPackageManager = application.getPackageManager();
            mInstalledApps = new MutableLiveData<>();
        }

        private MutableLiveData<List<String>> getInstalledApps() {
            InstalledAppsAsyncTask installedAppsAsyncTask = new InstalledAppsAsyncTask(mPackageManager);
            MutableLiveData<List<String>> installedApps = new MutableLiveData<>();
            try {
                installedApps.setValue(installedAppsAsyncTask.execute().get());
            } catch (Exception exception) {
            }
            return installedApps;
        }

        static class InstalledAppsAsyncTask extends AsyncTask<Void, Void, List<String>> {

            private PackageManager mPackageManager;

            public InstalledAppsAsyncTask(PackageManager packageManager) {
                mPackageManager = packageManager;
            }

            @Override
            protected List<String> doInBackground(final Void... params) {
                List<String> packages = new ArrayList<>();
                //List<InstalledApp> installedApps = new ArrayList<>();
                List<PackageInfo> packageInfos = mPackageManager.getInstalledPackages(0);
                //List<ApplicationInfo> applicationInfos = mPackageManager.getInstalledApplications(0);
                /*for (ApplicationInfo app : applicationInfos) {
                    installedApps.add(new InstalledApp(mPackageManager.getApplicationLabel(app).toString(),
                            "",
                            mPackageManager.getApplicationIcon(app)));
                }*/
                for (PackageInfo packageInfo : packageInfos) {
                    packages.add(packageInfo.packageName);
                }
                return packages;
            }
        }
    }
}
