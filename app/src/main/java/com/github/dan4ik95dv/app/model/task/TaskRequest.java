package com.github.dan4ik95dv.app.model.task;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskRequest {
    @SerializedName("task_id")
    Integer taskId;
    @SerializedName("type")
    String type;
    @SerializedName("attachments")
    List<Integer> attachments;

}
