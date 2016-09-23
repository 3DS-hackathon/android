package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.activity.DaggerSplashComponent;
import com.github.dan4ik95dv.app.di.module.activity.SplashModule;
import com.github.dan4ik95dv.app.ui.presenter.SplashPresenter;
import com.github.dan4ik95dv.app.ui.view.SplashMvpView;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerSplashComponent.builder().splashModule(new SplashModule(this)).build().inject(this);
        presenter.init();
        setContentView(R.layout.activity_splash_screen);
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

    @Override
    public void startOffline(Bundle bundle) {
        Toast.makeText(this, R.string.offline_mode, Toast.LENGTH_LONG).show();
        nextToMainActivity(bundle);
    }

    @Override
    public void startApp(Bundle bundle) {
        nextToMainActivity(bundle);
    }

}

