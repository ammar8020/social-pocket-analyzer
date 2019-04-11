
package com.ammar.socialpocketa.models.ReplyMentionAnalysis.MentionAnalysis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RnnCounts_ {

    @SerializedName("Appreciated")
    @Expose
    private Integer appreciated;
    @SerializedName("Abusive")
    @Expose
    private Integer abusive;
    @SerializedName("Disappointed")
    @Expose
    private Integer disappointed;
    @SerializedName("SeriousConcern")
    @Expose
    private Integer seriousConcern;
    @SerializedName("Suggestion")
    @Expose
    private Integer suggestion;

    public Integer getAppreciated() {
        return appreciated;
    }

    public void setAppreciated(Integer appreciated) {
        this.appreciated = appreciated;
    }

    public Integer getAbusive() {
        return abusive;
    }

    public void setAbusive(Integer abusive) {
        this.abusive = abusive;
    }

    public Integer getDisappointed() {
        return disappointed;
    }

    public void setDisappointed(Integer disappointed) {
        this.disappointed = disappointed;
    }

    public Integer getSeriousConcern() {
        return seriousConcern;
    }

    public void setSeriousConcern(Integer seriousConcern) {
        this.seriousConcern = seriousConcern;
    }

    public Integer getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Integer suggestion) {
        this.suggestion = suggestion;
    }

}
