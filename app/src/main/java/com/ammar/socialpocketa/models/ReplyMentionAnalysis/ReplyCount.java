package com.ammar.socialpocketa.models.ReplyMentionAnalysis;


import com.ammar.socialpocketa.models.ReplyMentionAnalysis.ReplyAnalysis.ReplyLogregCount;
import com.ammar.socialpocketa.models.ReplyMentionAnalysis.ReplyAnalysis.ReplyNaivebayesCount;
import com.ammar.socialpocketa.models.ReplyMentionAnalysis.ReplyAnalysis.ReplyRnnCount;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReplyCount {

    @SerializedName("replyLogregCount")
    @Expose
    private ReplyLogregCount replyLogregCount;
    @SerializedName("replyNaivebayesCount")
    @Expose
    private ReplyNaivebayesCount replyNaivebayesCount;
    @SerializedName("replyRnnCount")
    @Expose
    private ReplyRnnCount replyRnnCount;

    public ReplyLogregCount getReplyLogregCount() {
        return replyLogregCount;
    }

    public void setReplyLogregCount(ReplyLogregCount replyLogregCount) {
        this.replyLogregCount = replyLogregCount;
    }

    public ReplyNaivebayesCount getReplyNaivebayesCount() {
        return replyNaivebayesCount;
    }

    public void setReplyNaivebayesCount(ReplyNaivebayesCount replyNaivebayesCount) {
        this.replyNaivebayesCount = replyNaivebayesCount;
    }

    public ReplyRnnCount getReplyRnnCount() {
        return replyRnnCount;
    }

    public void setReplyRnnCount(ReplyRnnCount replyRnnCount) {
        this.replyRnnCount = replyRnnCount;
    }

}
