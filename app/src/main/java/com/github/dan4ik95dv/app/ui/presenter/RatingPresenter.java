package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;

import com.github.dan4ik95dv.app.ui.view.RatingMvpView;
import com.github.dan4ik95dv.app.ui.view.RatingMvpView;


public class RatingPresenter implements Presenter<RatingMvpView> {

    private RatingMvpView ratingMvpView;

    public RatingPresenter(Context context) {
        attachView((RatingMvpView) context);
    }

    @Override
    public void attachView(RatingMvpView view) {
        this.ratingMvpView = view;
    }



    @Override
    public void detachView() {
        this.ratingMvpView = null;
    }

    public void init() {

    }

}
