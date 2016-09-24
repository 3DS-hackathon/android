package com.github.dan4ik95dv.app.ui.view;

import android.support.v7.widget.RecyclerView;

import com.github.dan4ik95dv.app.ui.fragment.TasksFragment;

public interface TasksMvpView extends MvpView {
    void showError();

    TasksFragment getFragment();

    RecyclerView getTasksRecyclerView();

    void hideProgress();

}
