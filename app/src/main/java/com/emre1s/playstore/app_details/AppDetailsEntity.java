package com.emre1s.playstore.app_details;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

import static com.emre1s.playstore.GlobalConstants.TABLE_APP_DETAILS;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_AD_SUPPORTED;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_APP_ID;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_CONTENT_RATING;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_DEVELOPER;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_GENRE;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_ICON;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_INSTALLS;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_OFFERS_IAP;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_REVIEWS;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_SCORE_TEXT;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_SCREENSHOTS;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_SIZE;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_SUMMARY;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_TITLE;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_VIDEO;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_VIDEO_IMAGE;

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
    @ColumnInfo(name = KEY_SUMMARY)
    private String mSummary;

    @NonNull
    @ColumnInfo(name = KEY_INSTALLS)
    private String mInstalls;

    @NonNull
    @ColumnInfo(name = KEY_SCORE_TEXT)
    private String mScoreText;

    @NonNull
    @ColumnInfo(name = KEY_REVIEWS)
    private Integer mReviews;

    /*@NonNull
    @ColumnInfo(name = KEY_REVIEWS_ONE_STAR)
    private Integer mReviewsOneStar;

    @NonNull
    @ColumnInfo(name = KEY_REVIEWS_TWO_STARS)
    private Integer mReviewsTwoStars;

    @NonNull
    @ColumnInfo(name = KEY_REVIEWS_THREE_STARS)
    private Integer mReviewsThreeStars;

    @NonNull
    @ColumnInfo(name = KEY_REVIEWS_FOUR_STARS)
    private Integer mReviewsFourStars;

    @NonNull
    @ColumnInfo(name = KEY_REVIEWS_FIVE_STARS)
    private Integer mReviewsFiveStars;*/

    @NonNull
    @ColumnInfo(name = KEY_OFFERS_IAP)
    private Boolean mOffersIap;

    @NonNull
    @ColumnInfo(name = KEY_SIZE)
    private String mSize;

    @NonNull
    @ColumnInfo(name = KEY_DEVELOPER)
    private String mDeveloper;

    @NonNull
    @ColumnInfo(name = KEY_GENRE)
    private String mGenre;

    @NonNull
    @ColumnInfo(name = KEY_ICON)
    private String mIcon;

    @NonNull
    @ColumnInfo(name = KEY_SCREENSHOTS)
    private String mScreenshots;

    @Nullable
    @ColumnInfo(name = KEY_VIDEO)
    private String mVideo;

    @Nullable
    @ColumnInfo(name = KEY_VIDEO_IMAGE)
    private String mVideoImage;

    @NonNull
    @ColumnInfo(name = KEY_AD_SUPPORTED)
    private Boolean mAdSupported;

    @NonNull
    @ColumnInfo(name = KEY_CONTENT_RATING)
    private String mContentRating;

    public AppDetailsEntity(@NonNull String mAppId,
                            @NonNull String mTitle,
                            @NonNull String mSummary,
                            @NonNull String mInstalls,
                            @NonNull String mScoreText,
                            @NonNull Integer mReviews,
                            /*@NonNull Integer mReviewsOneStar,
                            @NonNull Integer mReviewsTwoStars,
                            @NonNull Integer mReviewsThreeStars,
                            @NonNull Integer mReviewsFourStars,
                            @NonNull Integer mReviewsFiveStars,*/
                            @NonNull Boolean mOffersIap,
                            @NonNull String mSize,
                            @NonNull String mDeveloper,
                            @NonNull String mGenre,
                            @NonNull String mIcon,
                            @NonNull String mScreenshots,
                            @Nullable String mVideo,
                            @Nullable String mVideoImage,
                            @NonNull Boolean mAdSupported,
                            @NonNull String mContentRating) {
        this.mAppId = mAppId;
        this.mTitle = mTitle;
        this.mSummary = mSummary;
        this.mInstalls = mInstalls;
        this.mScoreText = mScoreText;
        this.mReviews = mReviews;
        /*this.mReviewsOneStar = mReviewsOneStar;
        this.mReviewsTwoStar = mReviewsTwoStars;
        this.mReviewsThreeStar = mReviewsThreeStars;
        this.mReviewsFourStar = mReviewsFourStars;
        this.mReviewsFiveStar = mReviewsFiveStars;*/
        this.mOffersIap = mOffersIap;
        this.mSize = mSize;
        this.mDeveloper = mDeveloper;
        this.mGenre = mGenre;
        this.mIcon = mIcon;
        this.mScreenshots = mScreenshots;
        this.mVideo = mVideo;
        this.mVideoImage = mVideoImage;
        this.mAdSupported = mAdSupported;
        this.mContentRating = mContentRating;
    }

    public AppDetailsEntity(@NonNull AppDetails appDetails) {
        this.mAppId = appDetails.getmAppId();
        this.mTitle = appDetails.getmTitle();
        this.mSummary = appDetails.getmSummary();
        this.mInstalls = appDetails.getmInstalls();
        this.mScoreText = appDetails.getmScoreText();
        this.mReviews = appDetails.getmReviews();
        //this.mReviewsOneStar = appDetails.getmHistograms().getmOneStar();
        //this.mReviewsTwoStars = appDetails.getmHistograms().getmTwoStars();
        //this.mReviewsThreeStars = appDetails.getmHistograms().getmThreeStars();
        //this.mReviewsFourStars = appDetails.getmHistograms().getmFour();
        //this.mReviewsFiveStars = appDetails.getmHistograms().getmFive();
        this.mOffersIap = appDetails.hasInAppPurchases();
        this.mSize = appDetails.getmSize();
        this.mDeveloper = appDetails.getmDeveloper();
        this.mGenre = appDetails.getmGenre();
        this.mIcon = appDetails.getmIcon();
        this.mScreenshots = getString(appDetails.getmScreenshots());
        this.mVideo = appDetails.getmVideo();
        this.mVideoImage = appDetails.getmVideoImage();
        this.mAdSupported = appDetails.hasAdSupport();
        this.mContentRating = appDetails.getmContentRating();
    }

    private String getString(List<String> array) {
        StringBuilder stringBuilder = new StringBuilder();
        if (array != null) {
            for (int i = 0; i < array.size(); i++) {
                stringBuilder.append(array.get(i));
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
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
    public String getSummary() {
        return mSummary;
    }

    public void setSummary(@NonNull String mSummary) {
        this.mSummary = mSummary;
    }

    @NonNull
    public String getInstalls() {
        return mInstalls;
    }

    public void setInstalls(@NonNull String mInstalls) {
        this.mInstalls = mInstalls;
    }

    @NonNull
    public String getScoreText() {
        return mScoreText;
    }

    public void setScoreText(@NonNull String mScoreText) {
        this.mScoreText = mScoreText;
    }

    @NonNull
    public Integer getReviews() {
        return mReviews;
    }

    public void setmReviews(@NonNull Integer mReviews) {
        this.mReviews = mReviews;
    }

    @NonNull
    public Boolean getOffersIap() {
        return mOffersIap;
    }

    public void setOffersIap(@NonNull Boolean mOffersIap) {
        this.mOffersIap = mOffersIap;
    }

    @NonNull
    public String getSize() {
        return mSize;
    }

    public void setSize(@NonNull String mSize) {
        this.mSize = mSize;
    }

    @NonNull
    public String getDeveloper() {
        return mDeveloper;
    }

    public void setDeveloper(@NonNull String mDeveloper) {
        this.mDeveloper = mDeveloper;
    }

    @NonNull
    public String getGenre() {
        return mGenre;
    }

    public void setGenre(@NonNull String mGenre) {
        this.mGenre = mGenre;
    }

    @NonNull
    public String getIcon() {
        return mIcon;
    }

    public void setIcon(@NonNull String mIcon) {
        this.mIcon = mIcon;
    }

    @NonNull
    public String getScreenshots() {
        return mScreenshots;
    }

    public void setScreenshots(@NonNull String mScreenshots) {
        this.mScreenshots = mScreenshots;
    }

    @Nullable
    public String getVideo() {
        return mVideo;
    }

    public void setVideo(@Nullable String mVideo) {
        this.mVideo = mVideo;
    }

    @Nullable
    public String getVideoImage() {
        return mVideoImage;
    }

    public void setVideoImage(@Nullable String mVideoImage) {
        this.mVideoImage = mVideoImage;
    }

    @NonNull
    public Boolean getAdSupported() {
        return mAdSupported;
    }

    public void setAdSupported(@NonNull Boolean mAdSupported) {
        this.mAdSupported = mAdSupported;
    }

    @NonNull
    public String getContentRating() {
        return mContentRating;
    }

    public void setContentRating(@NonNull String mContentRating) {
        this.mContentRating = mContentRating;
    }
}
