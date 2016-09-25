package com.github.dan4ik95dv.app.ui.view;

import android.support.v7.widget.RecyclerView;

import com.github.dan4ik95dv.app.ui.fragment.AchievementsDepartmentsFragment;

public interface AchievementsDepartmentsMvpView extends MvpView {
    void showError();

    AchievementsDepartmentsFragment getFragment();

    RecyclerView getAchievementsRecyclerView();

    void hideProgress();

}
