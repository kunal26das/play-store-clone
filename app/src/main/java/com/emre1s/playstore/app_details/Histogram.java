package com.emre1s.playstore.app_details;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Histogram {

    public static final String KEY_ONE = "1";
    public static final String KEY_TWO = "2";
    public static final String KEY_THREE = "3";
    public static final String KEY_FOUR = "4";
    public static final String KEY_FIVE = "5";

    @SerializedName(KEY_ONE)
    public String mOne;

    @SerializedName(KEY_TWO)
    public String mTwo;

    @SerializedName(KEY_THREE)
    public String mThree;

    @SerializedName(KEY_FOUR)
    public String mFour;

    @SerializedName(KEY_FIVE)
    private String mFive;

    public Histogram(@NonNull String mOne,
                     @NonNull String mTwo,
                     @NonNull String mThree,
                     @NonNull String mFour,
                     @NonNull String mFive) {
        this.mOne = mOne;
        this.mTwo = mTwo;
        this.mThree = mThree;
        this.mFour = mFour;
        this.mFive = mFive;
    }

    public String getmOne() {
        return mOne;
    }

    public String getmTwo() {
        return mTwo;
    }

    public String getmThree() {
        return mThree;
    }

    public String getmFour() {
        return mFour;
    }

    public String getmFive() {
        return mFive;
    }
}
