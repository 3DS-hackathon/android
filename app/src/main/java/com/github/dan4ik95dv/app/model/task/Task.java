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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCurrentCoint() {
        return currentCoint;
    }

    public void setCurrentCoint(Integer currentCoint) {
        this.currentCoint = currentCoint;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RealmList<User> getUsers() {
        return users;
    }

    public void setUsers(RealmList<User> users) {
        this.users = users;
    }
}
