package com.emre1s.playstore;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.emre1s.playstore.app_details.AppDetailsEntity;

import static com.emre1s.playstore.GlobalConstants.TABLE_APP_DETAILS;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_APP_ID;

@Dao
public interface SingletonDao {

    @Query("SELECT * FROM " + TABLE_APP_DETAILS + " WHERE " + KEY_APP_ID + " = :appId")
    LiveData<AppDetailsEntity> getAppDetails(String appId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAppDetails(AppDetailsEntity appDetails);
}
