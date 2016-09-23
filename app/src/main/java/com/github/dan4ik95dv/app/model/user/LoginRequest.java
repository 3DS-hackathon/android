package com.github.dan4ik95dv.app.model.user;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
