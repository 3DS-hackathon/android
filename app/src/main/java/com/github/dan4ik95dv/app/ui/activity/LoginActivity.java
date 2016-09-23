package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.module.activity.LoginModule;
import com.github.dan4ik95dv.app.ui.presenter.LoginPresenter;
import com.github.dan4ik95dv.app.ui.view.LoginMvpView;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build().inject(this);
        presenter.init();
        setContentView(R.layout.activity_login);
    }


    @Override
    public void showError() {
        showErrorInternetDialog(this);
    }

    @Override
    public void startMain() {

        nextToMainActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


}

