package com.github.dan4ik95dv.app.model.task;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;

public class TasksResponse {
    @SerializedName("count")
    Integer count;

    @SerializedName("results")
    RealmList<Task> tasks;

    public Integer getCount() {
        return count;
    }

    public RealmList<Task> getTasks() {
        return tasks;
    }


}
