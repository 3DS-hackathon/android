package com.github.dan4ik95dv.app.di.module;


import com.google.gson.annotations.SerializedName;


public class BodyResponse {

    @SerializedName("code")
    public String code;
    @SerializedName("httpCode")
    public Integer httpCode;
    @SerializedName("message")
    public String message;

    public String getCode() {
        return code;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public String getMessage() {
        return message;
    }

}
