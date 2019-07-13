package com.emre1s.playstore.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;

import java.util.List;

public class CategoryList {

    public List<Category> getCategoryList() {
        return categoryList;
    }

    @SerializedName("categories")
    private List<Category> categoryList;

    static public class Category implements Parcelable {
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("id")
        @Expose
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.id);
        }

        public Category() {
        }

        protected Category(Parcel in) {
            this.name = in.readString();
            this.id = in.readString();
        }

        public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
            @Override
            public Category createFromParcel(Parcel source) {
                return new Category(source);
            }

            @Override
            public Category[] newArray(int size) {
                return new Category[size];
            }
        };
    }


}
