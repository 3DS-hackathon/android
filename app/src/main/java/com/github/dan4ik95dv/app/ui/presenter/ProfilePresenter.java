package com.github.dan4ik95dv.app.ui.presenter;

import android.content.Context;

import com.github.dan4ik95dv.app.application.App;
import com.github.dan4ik95dv.app.ui.view.ProfileMvpView;


public class ProfilePresenter implements Presenter<ProfileMvpView> {

    private ProfileMvpView profileMvpView;

    public ProfilePresenter(Context context) {
        App.getInstance().getClientComponent().inject(this);
        attachView((ProfileMvpView) context);

    }


    @Override
    public void attachView(ProfileMvpView view) {
        this.profileMvpView = view;
    }

    @Override
    public void detachView() {
        this.profileMvpView = null;
    }

    public void init() {

    }

}