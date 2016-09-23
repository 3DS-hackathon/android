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
}
