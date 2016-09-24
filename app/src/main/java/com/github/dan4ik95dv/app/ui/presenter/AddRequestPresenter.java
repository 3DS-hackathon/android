package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;

import com.github.dan4ik95dv.app.ui.view.AddRequestMvpView;


public class AddRequestPresenter implements Presenter<AddRequestMvpView> {

    private AddRequestMvpView addRequestMvpView;

    public AddRequestPresenter(Context context) {
        attachView((AddRequestMvpView) context);
    }

    @Override
    public void attachView(AddRequestMvpView view) {
        this.addRequestMvpView = view;
    }

    @Override
    public void detachView() {
        this.addRequestMvpView = null;
    }

    public void init() {

    }

}
