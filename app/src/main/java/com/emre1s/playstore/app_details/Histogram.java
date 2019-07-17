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
    public int mOne;

    @SerializedName(KEY_TWO)
    public int mTwo;

    @SerializedName(KEY_THREE)
    public int mThree;

    @SerializedName(KEY_FOUR)
    public int mFour;

    @SerializedName(KEY_FIVE)
    private int mFive;

    public Histogram() {

    }

    public Histogram(@NonNull int mOne,
                     @NonNull int mTwo,
                     @NonNull int mThree,
                     @NonNull int mFour,
                     @NonNull int mFive) {
        this.mOne = mOne;
        this.mTwo = mTwo;
        this.mThree = mThree;
        this.mFour = mFour;
        this.mFive = mFive;
    }

    public int getmOne() {
        return mOne;
    }

    public int getmTwo() {
        return mTwo;
    }

    public int getmThree() {
        return mThree;
    }

    public int getmFour() {
        return mFour;
    }

    public int getmFive() {
        return mFive;
    }
}