package com.github.dan4ik95dv.app.ui.view;

import com.github.dan4ik95dv.app.model.task.Task;

public interface MoreTaskMvpView extends MvpView {
    void showError();

    void fillTask(Task task);
}
