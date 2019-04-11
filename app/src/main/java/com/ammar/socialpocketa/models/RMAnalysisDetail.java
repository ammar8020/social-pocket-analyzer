
package com.ammar.socialpocketa.models;

import com.ammar.socialpocketa.models.ReplyMentionAnalysis.MentionsCount;
import com.ammar.socialpocketa.models.ReplyMentionAnalysis.RepliesCount;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RMAnalysisDetail {

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
