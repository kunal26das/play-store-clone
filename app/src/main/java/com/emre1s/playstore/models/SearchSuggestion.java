package com.emre1s.playstore.models;

import android.os.Parcel;

public class SearchSuggestion implements com.arlib.floatingsearchview.suggestions.model.SearchSuggestion {

    public static final Creator<SearchSuggestion> CREATOR = new Creator<SearchSuggestion>() {
        @Override
        public SearchSuggestion createFromParcel(Parcel source) {
            return new SearchSuggestion(source);
        }

        @Override
        public SearchSuggestion[] newArray(int size) {
            return new SearchSuggestion[size];
        }
    };
    private String suggestion;
    private boolean mIsHistory = false;

    public SearchSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    protected SearchSuggestion(Parcel in) {
        this.suggestion = in.readString();
        this.mIsHistory = in.readByte() != 0;
    }

    public boolean ismIsHistory() {
        return mIsHistory;
    }

    public void setmIsHistory(boolean mIsHistory) {
        this.mIsHistory = mIsHistory;
    }

    @Override
    public String getBody() {
        return suggestion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.suggestion);
        dest.writeByte(this.mIsHistory ? (byte) 1 : (byte) 0);
    }
}
