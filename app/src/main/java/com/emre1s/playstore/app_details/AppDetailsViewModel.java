package com.emre1s.playstore.app_details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.emre1s.playstore.SingletonDao;
import com.emre1s.playstore.SingletonDatabase;

public class AppDetailsViewModel extends AndroidViewModel {

    private AppDetailsRepository mRepository;

    public AppDetailsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AppDetailsRepository(application);
    }

    public LiveData<AppDetailsEntity> getAppDetails(String appId) {
        return mRepository.getAppDetails(appId);
    }

    static class AppDetailsRepository {

        private static SingletonDao mDao;
        private SingletonDatabase mDatabase;

        AppDetailsRepository(Application application) {
            mDatabase = SingletonDatabase.getDatabaseInstance(application);
            mDao = mDatabase.getDao();
        }

        LiveData<AppDetailsEntity> getAppDetails(String appId) {
            mDatabase.fetchAppDetails(appId);
            return mDao.getAppDetails(appId);
        }

    }
}
