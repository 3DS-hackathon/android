package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;

import com.github.dan4ik95dv.app.ui.view.LoginMvpView;
import com.github.dan4ik95dv.app.ui.view.MainMvpView;


public class MainPresenter implements Presenter<MainMvpView> {

    private MainMvpView mainMvpView;

    public MainPresenter(Context context) {
        attachView((MainMvpView) context);
    }


    @Override
    public void attachView(MainMvpView view) {
        this.mainMvpView = view;
    }

    @Override
    public void detachView() {
        this.mainMvpView = null;
    }

    public void init() {

    }

}
