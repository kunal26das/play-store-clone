package com.emre1s.playstore.app_details;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppDetails implements Parcelable {

    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DESCRIPTION_HTML = "descriptionHTML";
    public static final String KEY_SUMMARY = "summary";
    public static final String KEY_INSTALLS = "installs";
    public static final String KEY_MIN_INSTALLS = "minInstalls";
    public static final String KEY_SCORE = "score";
    public static final String KEY_SCORE_TEXT = "scoreText";
    public static final String KEY_RATINGS = "ratings";
    public static final String KEY_REVIEWS = "reviews";
    public static final String KEY_HISTOGRAM = "histogram";
    public static final String KEY_PRICE = "price";
    public static final String KEY_FREE = "free";
    public static final String KEY_CURRENCY = "currency";
    public static final String KEY_PRICE_TEXT = "priceText";
    public static final String KEY_OFFERS_IAP = "offersIAP";
    public static final String KEY_SIZE = "size";
    public static final String KEY_ANDROID_VERSION = "androidVersion";
    public static final String KEY_ANDROID_VERSION_TEXT = "androidVersionText";
    public static final String KEY_DEVELOPER = "developer";
    public static final String KEY_DEVELOPER_ID = "developerId";
    public static final String KEY_DEVELOPER_EMAIL = "developerEmail";
    public static final String KEY_DEVELOPER_WEBSITE = "developerWebsite";
    public static final String KEY_DEVELOPER_ADDRESS = "developerAddress";
    public static final String KEY_PRIVACY_POLICY = "privacyPolicy";
    public static final String KEY_DEVELOPER_INTERNAL_ID = "developerInternalID";
    public static final String KEY_GENRE = "genre";
    public static final String KEY_GENRE_ID = "genreId";
    public static final String KEY_ICON = "icon";
    public static final String KEY_HEADER_IMAGE = "headerImage";
    public static final String KEY_SCREENSHOTS = "screenshots";
    public static final String KEY_VIDEO = "video";
    public static final String KEY_VIDEO_IMAGE = "videoImage";
    public static final String KEY_CONTENT_RATING = "contentRating";
    public static final String KEY_AD_SUPPORTED = "adSupported";
    public static final String KEY_RELEASED = "released";
    public static final String KEY_UPDATED = "updated";
    public static final String KEY_VERSION = "version";
    public static final String KEY_RECENT_CHANGES = "recentChanges";
    public static final String KEY_COMMENTS = "comments";
    public static final String KEY_APP_ID = "appId";
    public static final String KEY_URL = "url";

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

    public void setmHistograms(Histogram mHistograms) {
        this.mHistograms = mHistograms;
    }

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

    public AppDetails() {
    }


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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeString(this.mDescription);
        dest.writeString(this.mDescriptionHtml);
        dest.writeString(this.mSummary);
        dest.writeString(this.mInstalls);
        dest.writeValue(this.mMinInstalls);
        dest.writeValue(this.mScore);
        dest.writeString(this.mScoreText);
        dest.writeValue(this.mRatings);
        dest.writeValue(this.mReviews);
        dest.writeValue(this.mPrice);
        dest.writeValue(this.mFree);
        dest.writeString(this.mCurrency);
        dest.writeString(this.mPriceText);
        dest.writeValue(this.mOffersIap);
        dest.writeString(this.mSize);
        dest.writeString(this.mAndroidVersion);
        dest.writeString(this.mAndroidVersionText);
        dest.writeString(this.mDeveloper);
        dest.writeString(this.mDeveloperId);
        dest.writeString(this.mDeveloperEmail);
        dest.writeString(this.mDeveloperWebsite);
        dest.writeString(this.mDeveloperAddress);
        dest.writeString(this.mPrivacyPolicy);
        dest.writeString(this.mDeveloperInternalId);
        dest.writeString(this.mGenre);
        dest.writeString(this.mGenreId);
        dest.writeString(this.mIcon);
        dest.writeString(this.mHeaderImage);
        dest.writeStringList(this.mScreenshots);
        dest.writeString(this.mVideo);
        dest.writeString(this.mVideoImage);
        dest.writeString(this.mContentRating);
        dest.writeValue(this.mAdSupported);
        dest.writeString(this.mReleased);
        dest.writeValue(this.mUpdated);
        dest.writeString(this.mVersion);
        dest.writeString(this.mRecentChanges);
        dest.writeStringList(this.mComments);
        dest.writeString(this.mAppId);
        dest.writeString(this.mUrl);
    }

    protected AppDetails(Parcel in) {
        this.mTitle = in.readString();
        this.mDescription = in.readString();
        this.mDescriptionHtml = in.readString();
        this.mSummary = in.readString();
        this.mInstalls = in.readString();
        this.mMinInstalls = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mScore = (Float) in.readValue(Float.class.getClassLoader());
        this.mScoreText = in.readString();
        this.mRatings = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mReviews = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mPrice = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mFree = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mCurrency = in.readString();
        this.mPriceText = in.readString();
        this.mOffersIap = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mSize = in.readString();
        this.mAndroidVersion = in.readString();
        this.mAndroidVersionText = in.readString();
        this.mDeveloper = in.readString();
        this.mDeveloperId = in.readString();
        this.mDeveloperEmail = in.readString();
        this.mDeveloperWebsite = in.readString();
        this.mDeveloperAddress = in.readString();
        this.mPrivacyPolicy = in.readString();
        this.mDeveloperInternalId = in.readString();
        this.mGenre = in.readString();
        this.mGenreId = in.readString();
        this.mIcon = in.readString();
        this.mHeaderImage = in.readString();
        this.mScreenshots = in.createStringArrayList();
        this.mVideo = in.readString();
        this.mVideoImage = in.readString();
        this.mContentRating = in.readString();
        this.mAdSupported = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mReleased = in.readString();
        this.mUpdated = (Long) in.readValue(Long.class.getClassLoader());
        this.mVersion = in.readString();
        this.mRecentChanges = in.readString();
        this.mComments = in.createStringArrayList();
        this.mAppId = in.readString();
        this.mUrl = in.readString();
    }

    public static final Parcelable.Creator<AppDetails> CREATOR = new Parcelable.Creator<AppDetails>() {
        @Override
        public AppDetails createFromParcel(Parcel source) {
            return new AppDetails(source);
        }

        @Override
        public AppDetails[] newArray(int size) {
            return new AppDetails[size];
        }
    };
}
