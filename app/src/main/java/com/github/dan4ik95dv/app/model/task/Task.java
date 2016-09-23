package com.github.dan4ik95dv.app.model.task;

import com.github.dan4ik95dv.app.model.user.User;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class Task extends RealmObject {
    @Index
    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("current_count")
    Integer currentCoint;

    @SerializedName("total_count")
    Integer totalCount;

    @SerializedName("name")
    String name;

    @SerializedName("desc")
    String desc;

    @SerializedName("type")
    String type;

    @SerializedName("users")
    private RealmList<User> users;

}
