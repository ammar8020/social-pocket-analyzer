package com.ammar.socialpocketa.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hashtag {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_str")
    @Expose
    private String idStr;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("screen_name")
    @Expose
    private String screenName;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("retweeted")
    @Expose
    private Boolean retweeted;
    @SerializedName("created_at")
    @Expose
    private Long createdAt;
    @SerializedName("profile_image_url")
    @Expose
    private String profileImageUrl;
    @SerializedName("retweet_count")
    @Expose
    private Integer retweetCount;
    @SerializedName("favorite_count")
    @Expose
    private Integer favoriteCount;
    @SerializedName("favorited")
    @Expose
    private Boolean favorited;
    @SerializedName("sentimentAnalysisLogreg")
    @Expose
    private String sentimentAnalysisLogreg;
    @SerializedName("sentimentAnalysisNaiveBayes")
    @Expose
    private String sentimentAnalysisNaiveBayes;
    @SerializedName("sentimentAnalysisRnn")
    @Expose
    private String sentimentAnalysisRnn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean getRetweeted() {
        return retweeted;
    }

    public void setRetweeted(Boolean retweeted) {
        this.retweeted = retweeted;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Integer getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(Integer retweetCount) {
        this.retweetCount = retweetCount;
    }

    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public String getSentimentAnalysisLogreg() {
        return sentimentAnalysisLogreg;
    }

    public void setSentimentAnalysisLogreg(String sentimentAnalysisLogreg) {
        this.sentimentAnalysisLogreg = sentimentAnalysisLogreg;
    }

    public String getSentimentAnalysisNaiveBayes() {
        return sentimentAnalysisNaiveBayes;
    }

    public void setSentimentAnalysisNaiveBayes(String sentimentAnalysisNaiveBayes) {
        this.sentimentAnalysisNaiveBayes = sentimentAnalysisNaiveBayes;
    }

    public String getSentimentAnalysisRnn() {
        return sentimentAnalysisRnn;
    }

    public void setSentimentAnalysisRnn(String sentimentAnalysisRnn) {
        this.sentimentAnalysisRnn = sentimentAnalysisRnn;
    }

}