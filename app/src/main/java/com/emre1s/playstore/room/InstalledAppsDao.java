package com.emre1s.playstore.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.emre1s.playstore.apps_installed_list.InstalledApp;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface InstalledAppsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(InstalledApp installedApp);

    @Query("SELECT * from installed_apps ORDER BY mTitle ASC")
    Observable<List<InstalledApp>> getInstalledApps();
}
