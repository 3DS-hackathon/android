package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;

import com.github.dan4ik95dv.app.ui.view.MoreTaskMvpView;


public class MoreTaskPresenter implements Presenter<MoreTaskMvpView> {

    private MoreTaskMvpView moreTaskMvpView;

    public MoreTaskPresenter(Context context) {
        attachView((MoreTaskMvpView) context);
    }

    @Override
    public void attachView(MoreTaskMvpView view) {
        this.moreTaskMvpView = view;
    }

    @Override
    public void detachView() {
        this.moreTaskMvpView = null;
    }

    public void init() {

    }

}
