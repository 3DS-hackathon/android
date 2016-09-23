package com.github.dan4ik95dv.app.model.attachment;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class Attachment extends RealmObject {
    @Index
    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("file_name")
    String fileName;

    @SerializedName("mime_type")
    String mimeType;

    @SerializedName("path")
    String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
