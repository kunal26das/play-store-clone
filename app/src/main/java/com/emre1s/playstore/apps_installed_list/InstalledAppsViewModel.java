package com.emre1s.playstore.apps_installed_list;

import android.app.Application;
import android.content.pm.ApplicationInfo;
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

    public MutableLiveData<List<InstalledApp>> getInstalledApps() {
        return mRepository.getInstalledApps();
    }

    static class InstalledAppsRepository {

        MutableLiveData<List<InstalledApp>> mInstalledApps;
        PackageManager mPackageManager;

        public InstalledAppsRepository(Application application) {
            mPackageManager = application.getPackageManager();
            mInstalledApps = new MutableLiveData<>();
        }

        private MutableLiveData<List<InstalledApp>> getInstalledApps() {
            InstalledAppsAsyncTask installedAppsAsyncTask = new InstalledAppsAsyncTask(mPackageManager);
            MutableLiveData<List<InstalledApp>> installedApps = new MutableLiveData<>();
            try {
                installedApps.setValue(installedAppsAsyncTask.execute().get());
            } catch (Exception exception) {
            }
            return installedApps;
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
                        if ((app.flags & (ApplicationInfo.FLAG_UPDATED_SYSTEM_APP | ApplicationInfo.FLAG_SYSTEM)) <= 0) {
                            apps.add(new InstalledApp(app.packageName,
                                    mPackageManager.getApplicationLabel(app).toString(),
                                    mPackageManager.getPackageInfo(app.packageName, 0).versionName,
                                    mPackageManager.getApplicationIcon(app)));
                        }

                    } catch (Exception exception) {

                    }
                }
                return apps;
            }
        }
    }
}
