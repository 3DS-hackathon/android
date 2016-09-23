package com.github.dan4ik95dv.app.model.balancelog;

import com.github.dan4ik95dv.app.model.task.Request;
import com.github.dan4ik95dv.app.model.task.Task;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class BalanceLog extends RealmObject {
    @Index
    @PrimaryKey
    @SerializedName("id")
    private int id;

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
}
