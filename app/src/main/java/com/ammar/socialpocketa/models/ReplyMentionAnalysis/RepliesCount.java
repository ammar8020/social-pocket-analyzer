
package com.ammar.socialpocketa.models.replyMentionAnalysis;

import com.ammar.socialpocketa.models.replyMentionAnalysis.ReplyAnalysis.LogregCounts;
import com.ammar.socialpocketa.models.replyMentionAnalysis.ReplyAnalysis.NaivebayesCounts;
import com.ammar.socialpocketa.models.replyMentionAnalysis.ReplyAnalysis.RnnCounts;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepliesCount {

    @SerializedName("logregCounts")
    @Expose
    private LogregCounts logregCounts;
    @SerializedName("naivebayesCounts")
    @Expose
    private NaivebayesCounts naivebayesCounts;
    @SerializedName("rnnCounts")
    @Expose
    private RnnCounts rnnCounts;

    public LogregCounts getLogregCounts() {
        return logregCounts;
    }

    public void setLogregCounts(LogregCounts logregCounts) {
        this.logregCounts = logregCounts;
    }

    public NaivebayesCounts getNaivebayesCounts() {
        return naivebayesCounts;
    }

    public void setNaivebayesCounts(NaivebayesCounts naivebayesCounts) {
        this.naivebayesCounts = naivebayesCounts;
    }

    public RnnCounts getRnnCounts() {
        return rnnCounts;
    }

    public void setRnnCounts(RnnCounts rnnCounts) {
        this.rnnCounts = rnnCounts;
    }

}
