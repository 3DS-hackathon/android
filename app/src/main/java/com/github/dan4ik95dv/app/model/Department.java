package com.github.dan4ik95dv.app.model;

import com.github.dan4ik95dv.app.model.user.User;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class Department extends RealmObject {
    @Index
    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("avatar")
    String avatar;

    @SerializedName("desc")
    String desc;

    @SerializedName("name")
    String name;

    @SerializedName("rating")
    Integer rating;

    @SerializedName("users")
    private RealmList<User> users;

}
