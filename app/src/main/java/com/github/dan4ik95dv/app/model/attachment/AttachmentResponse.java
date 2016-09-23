package com.github.dan4ik95dv.app.model.attachment;

import com.github.dan4ik95dv.app.di.module.BodyResponse;
import com.google.gson.annotations.SerializedName;


public class AttachmentResponse extends BodyResponse {
    @SerializedName("data")
    UploadAttachment attachment;
}
