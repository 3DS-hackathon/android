package com.github.dan4ik95dv.app.model.task;

import com.google.gson.annotations.SerializedName;

public class TaskStatus {
    @SerializedName("progress")
    Integer progress;
    @SerializedName("task")
    Task task;
}
