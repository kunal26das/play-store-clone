package com.emre1s.playstore.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.emre1s.playstore.apps_installed_list.InstalledApp;

@Database(entities = {InstalledApp.class}, version = 1, exportSchema = false)
public abstract class InstalledAppsDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "installed_apps_database";
    private static volatile InstalledAppsDatabase INSTANCE;

    public static InstalledAppsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InstalledAppsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InstalledAppsDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public abstract InstalledAppsDao installedAppsDao();
}
