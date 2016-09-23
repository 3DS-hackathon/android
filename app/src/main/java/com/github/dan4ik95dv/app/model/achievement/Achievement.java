package com.github.dan4ik95dv.app.model.achievement;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class Achievement extends RealmObject {
    @SerializedName("desc")
    String desc;
    @SerializedName("name")
    String name;
    @SerializedName("pic")
    String pic;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
