package com.github.dan4ik95dv.app.model.task;

import com.github.dan4ik95dv.app.model.attachment.Attachment;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class Request extends RealmObject {
    @Index
    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("delta_count")
    Integer deltaCount;

    @SerializedName("status")
    String status;

    @SerializedName("type")
    String type;

    @SerializedName("attachments")
    RealmList<Attachment> attachments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDeltaCount() {
        return deltaCount;
    }

    public void setDeltaCount(Integer deltaCount) {
        this.deltaCount = deltaCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
