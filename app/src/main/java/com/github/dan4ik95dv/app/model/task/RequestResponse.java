package com.github.dan4ik95dv.app.model.task;

import com.github.dan4ik95dv.app.di.module.BodyResponse;
import com.google.gson.annotations.SerializedName;


public class RequestResponse extends BodyResponse {
    @SerializedName("data")
    RequestTask requestTask;
}
