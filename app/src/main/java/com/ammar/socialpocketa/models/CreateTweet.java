package com.ammar.socialpocketa.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.ammar.socialpocketa.models.createTweet.Text;

public class CreateTweet {

//    @SerializedName("text")
//    @Expose
//    private Text text;
//
//    public Text getText() {
//        return text;
//    }
//
//    public void setText(Text text) {
//        this.text = text;
//    }



    @SerializedName("success")
    @Expose
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }



}
