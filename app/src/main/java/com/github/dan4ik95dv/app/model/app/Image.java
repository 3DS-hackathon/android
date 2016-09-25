package com.github.dan4ik95dv.app.model.app;

import io.realm.RealmObject;


public class Image extends RealmObject {

    private String original;

    private Integer photoId;

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }


}

