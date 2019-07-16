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
    public Integer mOneStar;

    @SerializedName(KEY_TWO)
    public Integer mTwoStars;

    @SerializedName(KEY_THREE)
    public Integer mThreeStars;

    @SerializedName(KEY_FOUR)
    public Integer mFourStars;

    @SerializedName(KEY_FIVE)
    private Integer mFiveStars;

    public Histogram(@NonNull Integer mOne,
                     @NonNull Integer mTwoStars,
                     @NonNull Integer mThreeStars,
                     @NonNull Integer mFour,
                     @NonNull Integer mFive) {
        this.mOneStar = mOne;
        this.mTwoStars = mTwoStars;
        this.mThreeStars = mThreeStars;
        this.mFourStars = mFour;
        this.mFiveStars = mFive;
    }

    public Integer getmOneStar() {
        return mOneStar;
    }

    public Integer getmTwoStars() {
        return mTwoStars;
    }

    public Integer getmThreeStars() {
        return mThreeStars;
    }

    public Integer getmFour() {
        return mFourStars;
    }

    public Integer getmFive() {
        return mFiveStars;
    }
}
