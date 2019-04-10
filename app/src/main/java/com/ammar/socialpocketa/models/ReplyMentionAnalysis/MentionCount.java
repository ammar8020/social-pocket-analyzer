package com.ammar.socialpocketa.models.ReplyMentionAnalysis;

import com.ammar.socialpocketa.models.ReplyMentionAnalysis.MentionAnalysis.MentionLogregCount;
import com.ammar.socialpocketa.models.ReplyMentionAnalysis.MentionAnalysis.MentionNaivebayesCount;
import com.ammar.socialpocketa.models.ReplyMentionAnalysis.MentionAnalysis.MentionRnnCount;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MentionCount {

    @SerializedName("logregCounts")
    @Expose
    private MentionLogregCount logregCounts;
    @SerializedName("naivebayesCounts")
    @Expose
    private MentionNaivebayesCount naivebayesCounts;
    @SerializedName("rnnCounts")
    @Expose
    private MentionRnnCount rnnCounts;

    public MentionLogregCount getLogregCounts() {
        return logregCounts;
    }

    public void setLogregCounts(MentionLogregCount logregCounts) {
        this.logregCounts = logregCounts;
    }

    public MentionNaivebayesCount getNaivebayesCounts() {
        return naivebayesCounts;
    }

    public void setNaivebayesCounts(MentionNaivebayesCount naivebayesCounts) {
        this.naivebayesCounts = naivebayesCounts;
    }

    public MentionRnnCount getRnnCounts() {
        return rnnCounts;
    }

    public void setRnnCounts(MentionRnnCount rnnCounts) {
        this.rnnCounts = rnnCounts;
    }

}
