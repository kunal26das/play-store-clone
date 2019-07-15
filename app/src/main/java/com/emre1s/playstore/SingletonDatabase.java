package com.emre1s.playstore;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.emre1s.playstore.api.DatabaseCallback;
import com.emre1s.playstore.api.RetrofitApiFactory;
import com.emre1s.playstore.app_details.AppDetails;
import com.emre1s.playstore.app_details.AppDetailsEntity;

import static com.emre1s.playstore.GlobalConstants.DATABASE;

@Database(entities = {AppDetailsEntity.class}, version = 1, exportSchema = false)
public abstract class SingletonDatabase extends RoomDatabase {

    private static volatile SingletonDatabase databaseInstance;

    public static SingletonDatabase getDatabaseInstance(final Context context) {
        if (databaseInstance == null) {
            synchronized (SingletonDatabase.class) {
                if (databaseInstance == null) {
                    databaseInstance = Room.databaseBuilder(context.getApplicationContext(), SingletonDatabase.class, DATABASE)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return databaseInstance;
    }

    public abstract SingletonDao getDao();

    public void fetchAppDetails(String appId) {
        new FetchAppDetailsTask(databaseInstance).execute(appId);
    }

    private static class FetchAppDetailsTask extends AsyncTask<String, Void, Void> {

        private final SingletonDao mDao;

        FetchAppDetailsTask(SingletonDatabase database) {
            mDao = database.getDao();
        }

        @Override
        protected Void doInBackground(String... strings) {
            final String appId = strings[0];
            RetrofitApiFactory retrofitApiFactory = RetrofitApiFactory.getInstance();
            retrofitApiFactory.getAppDetails(new DatabaseCallback() {
                @Override
                public void onSuccess(AppDetails appDetails) {
                    mDao.insertAppDetails(new AppDetailsEntity(appDetails));
                }

                @Override
                public void onFailure() {

                }
            }, appId);

            //Api api = ApiClient.getApiClient().create(Api.class);
            //Call<AppDetails> appDetailsResponseCall = api.getAppDetails(appId);
           /* appDetailsResponseCall.enqueue(new retrofit2.Callback<AppDetails>() {

                @Override
                public void onResponse(@NonNull Call<AppDetails> call, @NonNull Response<AppDetails> response) {
                    AppDetails appDetails = response.body();
                    if (appDetails != null) {
                        mDao.insertAppDetails(new AppDetailsEntity(appDetails));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<AppDetails> call, @NonNull Throwable t) {
                    Log.e("Network Error", t.toString());
                }
            });*/
            return null;
        }
    }

}
