package com.ammar.socialpocketa.models;

import com.ammar.socialpocketa.models.ReplyMentionAnalysis.MentionCount;
import com.ammar.socialpocketa.models.ReplyMentionAnalysis.ReplyCount;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReplyMentionAnalysisDetail {

    @SerializedName("ReplyCount")
    @Expose
    private ReplyCount replyCount;
    @SerializedName("MentionCount")
    @Expose
    private MentionCount mentionCount;

    public ReplyCount getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(ReplyCount replyCount) {
        this.replyCount = replyCount;
    }

    public MentionCount getMentionCount() {
        return mentionCount;
    }

    public void setMentionCount(MentionCount mentionCount) {
        this.mentionCount = mentionCount;
    }

}