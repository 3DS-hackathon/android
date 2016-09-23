package com.github.dan4ik95dv.app.model;

import com.github.dan4ik95dv.app.model.user.User;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class Department extends RealmObject {
    @SerializedName("avatar")
    String avatar;
    @SerializedName("desc")
    String desc;
    @SerializedName("name")
    String name;
    @SerializedName("rating")
    Integer rating;
    @Index
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("users")
    private RealmList<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public RealmList<User> getUsers() {
        return users;
    }

    public void setUsers(RealmList<User> users) {
        this.users = users;
    }
}
