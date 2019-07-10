package com.emre1s.playstore.apps;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.emre1s.playstore.GlobalConstants.TABLE_APP_DETAILS;
import static com.emre1s.playstore.apps.AppDetailsKeys.KEY_APP_ID;
import static com.emre1s.playstore.apps.AppDetailsKeys.KEY_GENRE;
import static com.emre1s.playstore.apps.AppDetailsKeys.KEY_TITLE;

@Entity(tableName = TABLE_APP_DETAILS)
public class AppDetailsEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = KEY_APP_ID)
    private String mAppId;

    @NonNull
    @ColumnInfo(name = KEY_TITLE)
    private String mTitle;

    @NonNull
    @ColumnInfo(name = KEY_GENRE)
    private String mGenre;

    public AppDetailsEntity(@NonNull String mAppId,
                            @NonNull String mTitle,
                            @NonNull String mGenre) {
        this.mAppId = mAppId;
        this.mTitle = mTitle;
        this.mGenre = mGenre;
    }

    public AppDetailsEntity(@NonNull AppDetails appDetails) {
        this.mAppId = appDetails.getmAppId();
        this.mTitle = appDetails.getmTitle();
        this.mGenre = appDetails.getmGenre();
    }

    @NonNull
    public String getAppId() {
        return mAppId;
    }

    public void setAppId(@NonNull String mAppId) {
        this.mAppId = mAppId;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(@NonNull String mTitle) {
        this.mTitle = mTitle;
    }

    @NonNull
    public String getGenre() {
        return mGenre;
    }

    public void setGenre(@NonNull String mGenre) {
        this.mGenre = mGenre;
    }
}
