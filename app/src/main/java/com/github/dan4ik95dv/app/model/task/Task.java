package com.github.dan4ik95dv.app.model.task;

import com.github.dan4ik95dv.app.model.user.User;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class Task extends RealmObject {
    @SerializedName("progress")
    Integer progress;
    @SerializedName("progress_user")
    Integer progressUser;
    @SerializedName("total_count")
    Integer totalCount;
    @SerializedName("status")
    String status;
    @SerializedName("name")
    String name;
    @SerializedName("desc")
    String desc;
    @SerializedName("type")
    String type;
    @SerializedName("experience")
    Integer experience;
    @SerializedName("price")
    Integer price;
    @SerializedName("pic")
    String pic;
    @SerializedName("start_timestamp")
    Long startTimestamp;
    @SerializedName("end_timestamp")
    Long endTimestamp;

    @Index
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("users")
    private RealmList<User> users;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProgressUser() {
        return progressUser;
    }

    public void setProgressUser(Integer progressUser) {
        this.progressUser = progressUser;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
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

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public RealmList<User> getUsers() {
        return users;
    }

    public void setUsers(RealmList<User> users) {
        this.users = users;
    }

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }
}
