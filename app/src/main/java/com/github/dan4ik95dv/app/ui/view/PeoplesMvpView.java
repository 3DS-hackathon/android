package com.github.dan4ik95dv.app.ui.view;

import android.support.v7.widget.RecyclerView;

import com.github.dan4ik95dv.app.ui.fragment.PeoplesFragment;

public interface PeoplesMvpView extends MvpView {
    void showError();

    PeoplesFragment getFragment();

    RecyclerView getPeoplesRecyclerView();

    void hideProgress();

}
