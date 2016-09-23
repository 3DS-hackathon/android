package com.github.dan4ik95dv.app.model.achievement;

import com.github.dan4ik95dv.app.di.module.BodyResponse;
import com.github.dan4ik95dv.app.model.attachment.UploadAttachment;
import com.google.gson.annotations.SerializedName;


public class AchievementResponse extends BodyResponse {
    @SerializedName("data")
    Achievements achievements;
}
