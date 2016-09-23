package com.github.dan4ik95dv.app.model.user;

import com.github.dan4ik95dv.app.di.module.BodyResponse;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends BodyResponse {
    @SerializedName("data")
    public Token token;
}
