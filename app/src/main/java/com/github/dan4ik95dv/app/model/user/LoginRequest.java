package com.github.dan4ik95dv.app.model.user;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;
}
