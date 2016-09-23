package com.github.dan4ik95dv.app.model.achievement;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class Achievement extends RealmObject {
    @SerializedName("desc")
    String desc;
    @SerializedName("name")
    String name;
    @SerializedName("pic")
    String pic;
}
