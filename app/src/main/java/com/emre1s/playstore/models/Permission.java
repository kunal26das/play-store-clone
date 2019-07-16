package com.emre1s.playstore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Permission {

    @SerializedName("permission")
    @Expose
    private String permission;
    @SerializedName("description")
    @Expose
    private String description;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}