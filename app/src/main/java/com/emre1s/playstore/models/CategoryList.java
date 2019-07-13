package com.emre1s.playstore.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryList {

    @SerializedName("categories")
    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    static public class Category implements Parcelable {
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
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("id")
        @Expose
        private String id;

        public Category() {
        }

        protected Category(Parcel in) {
            this.name = in.readString();
            this.id = in.readString();
        }

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
    }


}
