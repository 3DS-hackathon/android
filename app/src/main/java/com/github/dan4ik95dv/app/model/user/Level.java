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
    private Integer startCount;

    @SerializedName("end_count")
    private Integer endCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartCount() {
        return startCount;
    }

    public void setStartCount(Integer startCount) {
        this.startCount = startCount;
    }

    public Integer getEndCount() {
        return endCount;
    }

    public void setEndCount(Integer endCount) {
        this.endCount = endCount;
    }
}
