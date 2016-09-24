package com.github.dan4ik95dv.app.model.task;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskRequest {
    @SerializedName("task_id")
    Integer taskId;
    @SerializedName("attachments")
    List<Integer> attachments;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public List<Integer> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Integer> attachments) {
        this.attachments = attachments;
    }
}
