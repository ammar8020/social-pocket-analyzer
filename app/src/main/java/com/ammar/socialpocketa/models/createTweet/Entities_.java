
package com.ammar.socialpocketa.models.createTweet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entities_ {

    @SerializedName("description")
    @Expose
    private Description description;

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

}
