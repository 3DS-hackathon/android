package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;
import android.os.Handler;

import com.github.dan4ik95dv.app.ui.view.SplashMvpView;
import com.github.dan4ik95dv.app.util.Constants;


public class SplashPresenter implements Presenter<SplashMvpView> {

    private SplashMvpView splashMvpView;

    public SplashPresenter(Context context) {
        attachView((SplashMvpView) context);
    }

    @Override
    public void attachView(SplashMvpView view) {
        this.splashMvpView = view;
    }

    @Override
    public void detachView() {
        this.splashMvpView = null;
    }

    public void init() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                splashMvpView.startLogin();
            }
        }, Constants.SPLASH_DELAY);
    }

}
