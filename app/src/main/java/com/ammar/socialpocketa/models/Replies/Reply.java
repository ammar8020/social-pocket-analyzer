package com.ammar.socialpocketa.models.Replies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reply {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("id")
    @Expose
    private String id1;
    @SerializedName("id_str")
    @Expose
    private String idStr;
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

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
