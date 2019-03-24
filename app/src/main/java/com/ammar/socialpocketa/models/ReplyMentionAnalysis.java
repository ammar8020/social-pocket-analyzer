package com.ammar.socialpocketa.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReplyMentionAnalysis {

    @SerializedName("RepliesCount")
    @Expose
    private RepliesCount repliesCount;
    @SerializedName("MentionsCount")
    @Expose
    private MentionsCount mentionsCount;

    public RepliesCount getRepliesCount() {
        return repliesCount;
    }

    public void setRepliesCount(RepliesCount repliesCount) {
        this.repliesCount = repliesCount;
    }

    public MentionsCount getMentionsCount() {
        return mentionsCount;
    }

    public void setMentionsCount(MentionsCount mentionsCount) {
        this.mentionsCount = mentionsCount;
    }

}