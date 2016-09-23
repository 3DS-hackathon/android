package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.module.activity.LoginModule;
import com.github.dan4ik95dv.app.di.module.activity.ProfileModule;
import com.github.dan4ik95dv.app.ui.presenter.LoginPresenter;
import com.github.dan4ik95dv.app.ui.presenter.ProfilePresenter;
import com.github.dan4ik95dv.app.ui.view.LoginMvpView;
import com.github.dan4ik95dv.app.ui.view.ProfileMvpView;

import javax.inject.Inject;

public class ProfileActivity extends BaseActivity implements ProfileMvpView {

    @Inject
    ProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerProfileComponent.builder().loginModule(new ProfileModule(this)).build().inject(this);
        presenter.init();
        setContentView(R.layout.activity_profile);
    }


    @Override
    public void showError() {
        showErrorInternetDialog(this);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


}

