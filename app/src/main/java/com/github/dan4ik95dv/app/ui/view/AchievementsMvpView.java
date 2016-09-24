package com.github.dan4ik95dv.app.ui.view;

import android.support.v7.widget.RecyclerView;

import com.github.dan4ik95dv.app.model.achievement.Achievement;
import com.github.dan4ik95dv.app.ui.fragment.AchievementsFragment;
import com.github.dan4ik95dv.app.ui.fragment.TasksFragment;

public interface AchievementsMvpView extends MvpView {
    void showError();

    AchievementsFragment getFragment();

    RecyclerView getAchievementsRecyclerView();

    void hideProgress();

}
