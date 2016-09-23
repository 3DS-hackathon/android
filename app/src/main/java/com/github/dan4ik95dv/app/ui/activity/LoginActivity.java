package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.activity.DaggerLoginComponent;
import com.github.dan4ik95dv.app.di.module.activity.LoginModule;
import com.github.dan4ik95dv.app.ui.presenter.LoginPresenter;
import com.github.dan4ik95dv.app.ui.view.LoginMvpView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginPresenter presenter;

    @BindView(R.id.emailEditText)
    EditText emailEditText;

    @BindView(R.id.passwordEditText)
    EditText passwordEditText;

    @BindView(R.id.loginButton)
    Button loginButton;


    @OnClick(R.id.loginButton)
    public void login() {
        presenter.login(emailEditText.getText().toString(), passwordEditText.getText().toString());
    }

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
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


}

