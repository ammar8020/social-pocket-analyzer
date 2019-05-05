
package com.ammar.socialpocketa.models.replyMentionAnalysis;

import com.ammar.socialpocketa.models.replyMentionAnalysis.MentionAnalysis.LogregCounts_;
import com.ammar.socialpocketa.models.replyMentionAnalysis.MentionAnalysis.NaivebayesCounts_;
import com.ammar.socialpocketa.models.replyMentionAnalysis.MentionAnalysis.RnnCounts_;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MentionsCount {

    @SerializedName("logregCounts")
    @Expose
    private LogregCounts_ logregCounts;
    @SerializedName("naivebayesCounts")
    @Expose
    private NaivebayesCounts_ naivebayesCounts;
    @SerializedName("rnnCounts")
    @Expose
    private RnnCounts_ rnnCounts;

    public LogregCounts_ getLogregCounts() {
        return logregCounts;
    }

    public void setLogregCounts(LogregCounts_ logregCounts) {
        this.logregCounts = logregCounts;
    }

    public NaivebayesCounts_ getNaivebayesCounts() {
        return naivebayesCounts;
    }

    public void setNaivebayesCounts(NaivebayesCounts_ naivebayesCounts) {
        this.naivebayesCounts = naivebayesCounts;
    }

    public RnnCounts_ getRnnCounts() {
        return rnnCounts;
    }

    public void setRnnCounts(RnnCounts_ rnnCounts) {
        this.rnnCounts = rnnCounts;
    }

}
