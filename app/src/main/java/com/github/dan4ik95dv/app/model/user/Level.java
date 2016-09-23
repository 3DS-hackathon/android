package com.github.dan4ik95dv.app.model.user;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class Level extends RealmObject {
    @Index
    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("level")
    private Integer level;

    @SerializedName("name")
    private String name;

    @SerializedName("start_count")
    private String startCount;

    @SerializedName("end_count")
    private String endCount;
}
