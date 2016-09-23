package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.os.Handler;

import com.github.dan4ik95dv.app.ui.view.LoginMvpView;
import com.github.dan4ik95dv.app.ui.view.SplashMvpView;
import com.github.dan4ik95dv.app.util.Constants;


public class LoginPresenter implements Presenter<LoginMvpView> {

    private LoginMvpView loginMvpView;

    public LoginPresenter(Context context) {
        attachView((LoginMvpView) context);
    }


    @Override
    public void attachView(LoginMvpView view) {
        this.loginMvpView = view;
    }

    @Override
    public void detachView() {
        this.loginMvpView = null;
    }

    public void init() {

    }

}
