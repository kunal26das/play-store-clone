package com.emre1s.playstore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppByCategoryApiResponse {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("appId")
    @Expose
    private String appId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("developer")
    @Expose
    private String developer;
    @SerializedName("developerId")
    @Expose
    private String developerId;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("score")
    @Expose
    private double score;
    @SerializedName("scoreText")
    @Expose
    private String scoreText;
    @SerializedName("priceText")
    @Expose
    private String priceText;
    @SerializedName("free")
    @Expose
    private boolean free;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getScoreText() {
        return scoreText;
    }

    public void setScoreText(String scoreText) {
        this.scoreText = scoreText;
    }

    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

}