package com.github.dan4ik95dv.app.model.balancelog;

import com.github.dan4ik95dv.app.model.task.Request;
import com.github.dan4ik95dv.app.model.task.Task;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class BalanceLog extends RealmObject {
    @SerializedName("action")
    String action;
    @SerializedName("delta_count")
    Integer deltaCount;
    @SerializedName("desc")
    String desc;
    @SerializedName("request")
    Request request;
    @SerializedName("task")
    Task task;
    @Index
    @PrimaryKey
    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getDeltaCount() {
        return deltaCount;
    }

    public void setDeltaCount(Integer deltaCount) {
        this.deltaCount = deltaCount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
