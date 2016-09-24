package com.github.dan4ik95dv.app.model.app;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Image extends RealmObject {
    @SerializedName("id")
    @PrimaryKey
    private Integer imageId;
    @SerializedName("original")
    private String original;


    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }


}

