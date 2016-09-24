package com.github.dan4ik95dv.app.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.github.dan4ik95dv.app.R;
import com.github.dan4ik95dv.app.di.component.activity.DaggerLoginComponent;
import com.github.dan4ik95dv.app.di.module.activity.LoginModule;
import com.github.dan4ik95dv.app.ui.presenter.LoginPresenter;
import com.github.dan4ik95dv.app.ui.view.LoginMvpView;
import com.github.dan4ik95dv.app.util.AndroidUtils;
import com.github.dan4ik95dv.app.util.Progress;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginPresenter presenter;

    @Inject
    Progress progress;

    @BindView(R.id.emailEditText)
    AppCompatEditText emailEditText;

    @BindView(R.id.passwordEditText)
    AppCompatEditText passwordEditText;

    @BindView(R.id.loginButton)
    Button loginButton;


    @OnClick(R.id.loginButton)
    public void login() {
        if (!TextUtils.isEmpty(emailEditText.getText().toString()) &&
                !TextUtils.isEmpty(passwordEditText.getText().toString())
                && passwordEditText.length() >= 5
                && emailEditText.length() >= 5) {
            progress.show();
            AndroidUtils.hideKeyboard(this);
            presenter.login(emailEditText.getText().toString(), passwordEditText.getText().toString());
        } else {
            Toast.makeText(this, R.string.fill_fileds_msg, Toast.LENGTH_SHORT).show();
        }
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
        progress.close();
        showErrorInternetDialog(this);
    }

    @Override
    public void tokenError() {
        progress.close();
        Toast.makeText(this, R.string.incorrect_login_or_password, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void tokenResponse() {
        progress.close();
    }


    @Override
    protected void onResume() {
        super.onResume();
        emailEditText.requestFocus();
        AndroidUtils.showKeyboard(this, emailEditText);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AndroidUtils.hideKeyboard(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


}

