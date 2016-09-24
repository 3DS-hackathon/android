package com.github.dan4ik95dv.app.model.achievement;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;

public class AchievementsResponse {
    @SerializedName("count")
    Integer count;

    @SerializedName("results")
    RealmList<Achievement> achievements;

    public RealmList<Achievement> getAchievements() {
        return achievements;
    }
}
