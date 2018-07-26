package com.nyasa.mctteacher.Pojo;

import com.google.gson.annotations.SerializedName;

public class ParentPojoAddLocation extends CommonParentPojo {


    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
