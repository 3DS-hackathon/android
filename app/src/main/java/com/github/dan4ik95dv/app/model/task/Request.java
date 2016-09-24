package com.github.dan4ik95dv.app.model.task;

import com.github.dan4ik95dv.app.model.attachment.Attachment;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class Request extends RealmObject {
    @SerializedName("delta_balance")
    Integer deltaBalance;
    @SerializedName("status")
    String status;
    @SerializedName("type")
    String type;

    @SerializedName("task")
    Task task;
    @SerializedName("attachments")
    RealmList<Attachment> attachments;
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

    public Integer getDeltaBalance() {
        return deltaBalance;
    }

    public void setDeltaBalance(Integer deltaBalance) {
        this.deltaBalance = deltaBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RealmList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(RealmList<Attachment> attachments) {
        this.attachments = attachments;
    }
}
