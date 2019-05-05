package com.ammar.socialpocketa.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReplyToTweet {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("id")
    @Expose
    private String id1;
    @SerializedName("id_str")
    @Expose
    private String idStr;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("Screen_Name")
    @Expose
    private String screenName;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("in_reply_to_status_id")
    @Expose
    private String inReplyToStatusId;
    @SerializedName("sentimentAnalysisLogreg")
    @Expose
    private String sentimentAnalysisLogreg;
    @SerializedName("sentimentAnalysisNaiveBayes")
    @Expose
    private String sentimentAnalysisNaiveBayes;
    @SerializedName("sentimentAnalysisRnn")
    @Expose
    private String sentimentAnalysisRnn;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("retweeted")
    @Expose
    private Boolean retweeted;
    @SerializedName("profile_image_url")
    @Expose
    private String profileImageUrl;
    @SerializedName("retweet_count")
    @Expose
    private String retweetCount;
    @SerializedName("favorite_count")
    @Expose
    private String favoriteCount;
    @SerializedName("favorited")
    @Expose
    private Boolean favorited;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    public void setInReplyToStatusId(String inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getRetweeted() {
        return retweeted;
    }

    public void setRetweeted(Boolean retweeted) {
        this.retweeted = retweeted;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(String retweetCount) {
        this.retweetCount = retweetCount;
    }

    public String getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(String favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}