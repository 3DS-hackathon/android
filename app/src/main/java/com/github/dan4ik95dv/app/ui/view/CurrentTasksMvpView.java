package com.github.dan4ik95dv.app.ui.view;

import android.support.v7.widget.RecyclerView;

import com.github.dan4ik95dv.app.ui.fragment.CurrentTasksFragment;

public interface CurrentTasksMvpView extends MvpView {
    void showError();

    CurrentTasksFragment getFragment();

    RecyclerView getTasksRecyclerView();

    void hideProgress();

}
