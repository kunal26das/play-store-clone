package com.emre1s.playstore.app_details;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_AD_SUPPORTED;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_ANDROID_VERSION;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_ANDROID_VERSION_TEXT;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_APP_ID;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_COMMENTS;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_CONTENT_RATING;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_CURRENCY;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_DESCRIPTION;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_DESCRIPTION_HTML;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_DEVELOPER;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_DEVELOPER_ADDRESS;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_DEVELOPER_EMAIL;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_DEVELOPER_ID;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_DEVELOPER_INTERNAL_ID;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_DEVELOPER_WEBSITE;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_FREE;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_GENRE;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_GENRE_ID;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_HEADER_IMAGE;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_HISTOGRAM;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_ICON;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_INSTALLS;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_MIN_INSTALLS;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_OFFERS_IAP;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_PRICE;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_PRICE_TEXT;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_PRIVACY_POLICY;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_RATINGS;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_RECENT_CHANGES;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_RELEASED;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_REVIEWS;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_SCORE;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_SCORE_TEXT;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_SCREENSHOTS;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_SIZE;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_SUMMARY;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_TITLE;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_UPDATED;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_URL;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_VERSION;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_VIDEO;
import static com.emre1s.playstore.app_details.AppDetailsKeys.KEY_VIDEO_IMAGE;

public class AppDetails {

    @SerializedName(KEY_TITLE)
    private String mTitle;

    @SerializedName(KEY_DESCRIPTION)
    private String mDescription;

    @SerializedName(KEY_DESCRIPTION_HTML)
    private String mDescriptionHtml;

    @SerializedName(KEY_SUMMARY)
    private String mSummary;

    @SerializedName(KEY_INSTALLS)
    private String mInstalls;

    @SerializedName(KEY_MIN_INSTALLS)
    private Integer mMinInstalls;

    @SerializedName(KEY_SCORE)
    private Float mScore;

    @SerializedName(KEY_SCORE_TEXT)
    private String mScoreText;

    @SerializedName(KEY_RATINGS)
    private Integer mRatings;

    @SerializedName(KEY_REVIEWS)
    private Integer mReviews;

    @SerializedName(KEY_HISTOGRAM)
    private Histogram mHistograms;

    @SerializedName(KEY_PRICE)
    private Integer mPrice;

    @SerializedName(KEY_FREE)
    private Boolean mFree;

    @SerializedName(KEY_CURRENCY)
    private String mCurrency;

    @SerializedName(KEY_PRICE_TEXT)
    private String mPriceText;

    @SerializedName(KEY_OFFERS_IAP)
    private Boolean mOffersIap;

    @SerializedName(KEY_SIZE)
    private String mSize;

    @SerializedName(KEY_ANDROID_VERSION)
    private String mAndroidVersion;

    @SerializedName(KEY_ANDROID_VERSION_TEXT)
    private String mAndroidVersionText;

    @SerializedName(KEY_DEVELOPER)
    private String mDeveloper;

    @SerializedName(KEY_DEVELOPER_ID)
    private String mDeveloperId;

    @SerializedName(KEY_DEVELOPER_EMAIL)
    private String mDeveloperEmail;

    @SerializedName(KEY_DEVELOPER_WEBSITE)
    private String mDeveloperWebsite;

    @SerializedName(KEY_DEVELOPER_ADDRESS)
    private String mDeveloperAddress;

    @SerializedName(KEY_PRIVACY_POLICY)
    private String mPrivacyPolicy;

    @SerializedName(KEY_DEVELOPER_INTERNAL_ID)
    private String mDeveloperInternalId;

    @SerializedName(KEY_GENRE)
    private String mGenre;

    @SerializedName(KEY_GENRE_ID)
    private String mGenreId;

    @SerializedName(KEY_ICON)
    private String mIcon;

    @SerializedName(KEY_HEADER_IMAGE)
    private String mHeaderImage;

    @SerializedName(KEY_SCREENSHOTS)
    private List<String> mScreenshots;

    @SerializedName(KEY_VIDEO)
    private String mVideo;

    @SerializedName(KEY_VIDEO_IMAGE)
    private String mVideoImage;

    @SerializedName(KEY_CONTENT_RATING)
    private String mContentRating;

    @SerializedName(KEY_AD_SUPPORTED)
    private Boolean mAdSupported;

    @SerializedName(KEY_RELEASED)
    private String mReleased;

    @SerializedName(KEY_UPDATED)
    private Long mUpdated;

    @SerializedName(KEY_VERSION)
    private String mVersion;

    @SerializedName(KEY_RECENT_CHANGES)
    private String mRecentChanges;

    @SerializedName(KEY_COMMENTS)
    private List<String> mComments;

    @SerializedName(KEY_APP_ID)
    private String mAppId;

    @SerializedName(KEY_URL)
    private String mUrl;

    public AppDetails(@NonNull String mTitle,
                      @NonNull String mDescription,
                      @NonNull String mDescriptionHtml,
                      @NonNull String mSummary,
                      @NonNull String mInstalls,
                      @NonNull Integer mMinInstalls,
                      @NonNull Float mScore,
                      @NonNull String mScoreText,
                      @NonNull Integer mRatings,
                      @NonNull Integer mReviews,
                      @NonNull Histogram mHistograms,
                      @NonNull Integer mPrice,
                      @NonNull Boolean mFree,
                      @NonNull String mCurrency,
                      @NonNull String mPriceText,
                      @NonNull Boolean mOffersIap,
                      @NonNull String mSize,
                      @NonNull String mAndroidVersion,
                      @NonNull String mAndroidVersionText,
                      @NonNull String mDeveloper,
                      @NonNull String mDeveloperId,
                      @NonNull String mDeveloperEmail,
                      @NonNull String mDeveloperWebsite,
                      @NonNull String mDeveloperAddress,
                      @NonNull String mPrivacyPolicy,
                      @NonNull String mDeveloperInternalId,
                      @NonNull String mGenre,
                      @NonNull String mGenreId,
                      @NonNull String mIcon,
                      @NonNull String mHeaderImage,
                      @NonNull List<String> mScreenshots,
                      @Nullable String mVideo,
                      @Nullable String mVideoImage,
                      @NonNull String mContentRating,
                      @NonNull Boolean mAdSupported,
                      @NonNull String mReleased,
                      @NonNull Long mUpdated,
                      @NonNull String mVersion,
                      @NonNull String mRecentChanges,
                      @NonNull List<String> mComments,
                      @NonNull String mAppId,
                      @NonNull String mUrl) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mDescriptionHtml = mDescriptionHtml;
        this.mSummary = mSummary;
        this.mInstalls = mInstalls;
        this.mMinInstalls = mMinInstalls;
        this.mScore = mScore;
        this.mScoreText = mScoreText;
        this.mRatings = mRatings;
        this.mReviews = mReviews;
        this.mHistograms = mHistograms;
        this.mPrice = mPrice;
        this.mFree = mFree;
        this.mCurrency = mCurrency;
        this.mPriceText = mPriceText;
        this.mOffersIap = mOffersIap;
        this.mSize = mSize;
        this.mAndroidVersion = mAndroidVersion;
        this.mAndroidVersionText = mAndroidVersionText;
        this.mDeveloper = mDeveloper;
        this.mDeveloperId = mDeveloperId;
        this.mDeveloperEmail = mDeveloperEmail;
        this.mDeveloperWebsite = mDeveloperWebsite;
        this.mDeveloperAddress = mDeveloperAddress;
        this.mPrivacyPolicy = mPrivacyPolicy;
        this.mDeveloperInternalId = mDeveloperInternalId;
        this.mGenre = mGenre;
        this.mGenreId = mGenreId;
        this.mIcon = mIcon;
        this.mHeaderImage = mHeaderImage;
        this.mScreenshots = mScreenshots;
        this.mVideo = mVideo;
        this.mVideoImage = mVideoImage;
        this.mContentRating = mContentRating;
        this.mAdSupported = mAdSupported;
        this.mReleased = mReleased;
        this.mUpdated = mUpdated;
        this.mVersion = mVersion;
        this.mRecentChanges = mRecentChanges;
        this.mComments = mComments;
        this.mAppId = mAppId;
        this.mUrl = mUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmDescriptionHtml() {
        return mDescriptionHtml;
    }

    public String getmSummary() {
        return mSummary;
    }

    public String getmInstalls() {
        return mInstalls;
    }

    public Integer getmMinInstalls() {
        return mMinInstalls;
    }

    public Float getmScore() {
        return mScore;
    }

    public String getmScoreText() {
        return mScoreText;
    }

    public Integer getmRatings() {
        return mRatings;
    }

    public Integer getmReviews() {
        return mReviews;
    }

    public Histogram getmHistograms() {
        return mHistograms;
    }

    public Integer getmPrice() {
        return mPrice;
    }

    public Boolean getmFree() {
        return mFree;
    }

    public String getmCurrency() {
        return mCurrency;
    }

    public String getmPriceText() {
        return mPriceText;
    }

    public Boolean hasInAppPurchases() {
        return mOffersIap;
    }

    public String getmSize() {
        return mSize;
    }

    public String getmAndroidVersion() {
        return mAndroidVersion;
    }

    public String getmAndroidVersionText() {
        return mAndroidVersionText;
    }

    public String getmDeveloper() {
        return mDeveloper;
    }

    public String getmDeveloperId() {
        return mDeveloperId;
    }

    public String getmDeveloperEmail() {
        return mDeveloperEmail;
    }

    public String getmDeveloperWebsite() {
        return mDeveloperWebsite;
    }

    public String getmDeveloperAddress() {
        return mDeveloperAddress;
    }

    public String getmPrivacyPolicy() {
        return mPrivacyPolicy;
    }

    public String getmDeveloperInternalId() {
        return mDeveloperInternalId;
    }

    public String getmGenre() {
        return mGenre;
    }

    public String getmGenreId() {
        return mGenreId;
    }

    public String getmIcon() {
        return mIcon;
    }

    public String getmHeaderImage() {
        return mHeaderImage;
    }

    public List<String> getmScreenshots() {
        return mScreenshots;
    }

    public String getmVideo() {
        return mVideo;
    }

    public String getmVideoImage() {
        return mVideoImage;
    }

    public String getmContentRating() {
        return mContentRating;
    }

    public Boolean hasAdSupport() {
        return mAdSupported;
    }

    public String getmReleased() {
        return mReleased;
    }

    public Long getmUpdated() {
        return mUpdated;
    }

    public String getmVersion() {
        return mVersion;
    }

    public String getmRecentChanges() {
        return mRecentChanges;
    }

    public List<String> getmComments() {
        return mComments;
    }

    public String getmAppId() {
        return mAppId;
    }

    public String getmUrl() {
        return mUrl;
    }
}
