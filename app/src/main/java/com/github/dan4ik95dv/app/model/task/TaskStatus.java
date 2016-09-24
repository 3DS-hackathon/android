package com.github.dan4ik95dv.app.model.task;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskStatus {
    @SerializedName("progress")
    Integer progress;
    @SerializedName("task")
    Task task;
    @SerializedName("attachments")
    List<Integer> attachments;

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Integer> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Integer> attachments) {
        this.attachments = attachments;
    }
}
