package com.ammar.socialpocketa.models;

import java.util.List;

import com.ammar.socialpocketa.models.Replies.Reply;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("Tweet")
    @Expose
    private String tweet;
    @SerializedName("id_str")
    @Expose
    private String idStr;
    @SerializedName("screen_name")
    @Expose
    private String screenName;
    @SerializedName("Replies")
    @Expose
    private List<Reply> replies = null;

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

}
